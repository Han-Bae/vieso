package com.vieso.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import com.vieso.vo.*;
/**
 	이 클래스는 파일 업로드에 필요한 기능을 처리하고
 	업로드 파일의 정보를 만들어주는 기능의 유틸리티적인 클래스
 * @author 김태현
 * @since 2022.05.23
 * 				제작자 김태현
 *
 */
public class FileUtil {
	private ArrayList<FileVO> list;
	private HttpServletRequest req;
	private MultipartRequest multi;
	private String dir, path, bPath;
	
	public FileUtil() {}
	public FileUtil(HttpServletRequest req, String dir) {
		this.req = req;
		this.dir = dir;
		setMulti();
		setList();
	}
	
	// MultipartRequest 셋팅함수
	public void setMulti() {
		path = this.getClass().getResource("/").getPath();	// 시스템상 이 파일의 전체경로
		path = path.substring(0, path.lastIndexOf("/WEB-INF")) + dir;
		
		try {
			multi = new MultipartRequest(req, path, 1024 * 1024 * 10, "UTF-8", new FileRenamePolicy() {
								//	리퀘스트 경로  	최대용량(10메가) 인코딩방식 파일리네임정책
				@Override
				public File rename(File file) {
					// 업로드하는 파일 겹치는 상황에 리네임해줌
					// 우리는 파일이름_숫자.확장자로 사용할 예정
					String filename = file.getName();	// 원래 파일 이름
					String newname = filename;
					String name = filename.substring(0, filename.lastIndexOf("."));
					String ext = filename.substring(filename.lastIndexOf("."));
					int count = 0;
					File f = new File(path, newname);
					// -> 새로운 이름의 파일이 존재하는지 검사
							// 존재하면 카운트를 증가시킨 새로운 이름으로 파일을 다시 만들어서
							// 그 파일이 존재하는지 검사
					while(f.exists()) {
						// 이 경우는 파일이 있는 경우
						// 카운트를 증가시켜 파일을 다시 만든다
						++count;
						newname = name + "-" + count + ext;
						f = new File(path, newname);
					}
					return null;
				}
			});
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	// /파일 정보 리스트 세ㅇ팅
	public void setList() {
		list = new ArrayList<FileVO>();
		// 전송된 파일데이터의 키값만 뽑아온다.
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()) {
			String key = (String)en.nextElement();
			String oriname = multi.getOriginalFileName(key);
			if(oriname == null) {
				continue;
			}
			String savename = multi.getFilesystemName(key);
			File file = multi.getFile(key);
			long len = file.length();
			
			// VO만들기
			FileVO fVO = new FileVO();
			fVO.setOriname(oriname);
			fVO.setSavename(savename);
			fVO.setDir(dir);
			fVO.setLen(len);
			
			list.add(fVO);
			
			saveBackup(file);
		}
		
	}
	
	// 업로드파일 작업폴더로 저장해주는 함수
	public void saveBackup(File file) {
		bPath = this.getClass().getResource("/").getPath();
		bPath = bPath.substring(0, bPath.lastIndexOf("/source"))+
					"/git/blackpink/jennie/src/main/webapp/upload";
		bPath = bPath.replaceAll("/", "\\");
		File devFile = 	new File(bPath, file.getName());
		FileInputStream fin = null;
		PrintStream ps = null;
		try {
			byte[] buff = new byte[10240];
			fin = new FileInputStream(file);
			ps = new PrintStream(devFile);
			
			while(true) {
				int len = fin.read(buff);
				if(len== -1) break;
				ps.write(buff, 0, len);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
