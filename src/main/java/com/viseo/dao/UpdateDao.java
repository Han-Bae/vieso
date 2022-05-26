package com.viseo.dao;

import java.sql.*;
import java.util.*;

import com.viseo.db.*;
import com.viseo.sql.*;
import com.viseo.vo.*;

/**
 * 이 클래스는 회원 관련 데이터베이스 작업을 전담해서 처리하는 클래스
 * @author 한서라
 * @since 2022.05.26
 * @version v.1.0
 * 		작업 이력)
 * 				2022.05.26	- 클래스 제작
 * 						담당자 한서라
 */
public class UpdateDao {
/*
 	이 클래스는 이 클래스가 new 된 순간 데이터베이스 작업을 할 준비가
 	되어 있어야 한다.
 */
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private UpdateSQL uSQL;
	
	public UpdateDao() {
		db = new BlpDBCP();
		uSQL = new UpdateSQL();
	}
	
	// 아이디로 회원 정보조회 전담 처리함수
			public FileVO getIdInfo(String id) {
				// 반환값 변수
				FileVO fVO = new FileVO();
				// 커넥션
				con = db.getCon();
				// 질의명령
				String sql = uSQL.getSQL(uSQL.SEL_MEMBER_INFO);
				// 명령전달도구
				pstmt = db.getPSTMT(con, sql);
				try {
					// 질의명령 완성
					pstmt.setString(1, id);
					// 질의명령 보내고 결과받고
					rs = pstmt.executeQuery();
					// 꺼내서 VO에 담고
					rs.next();
					// vo setting
					fVO.setMno(rs.getInt("mno"));
					fVO.setName(rs.getString("name"));
					fVO.setId(rs.getString("id"));
					fVO.setMail(rs.getString("mail"));
					fVO.setAddr(rs.getString("addr"));
					fVO.setGen(rs.getString("gen"));
					fVO.setNickname(rs.getString("nickname"));
					fVO.setTel(rs.getString("tel"));
					fVO.setJoindate(rs.getDate("joindate"));
					
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					db.close(rs);
					db.close(pstmt);
					db.close(con);
				}
				
				// 데이터 반환해주고
				return fVO;
			}
		
		// 회원탈퇴 데이터베이스작업 전담 처리함수
			public int delMember(int mno) {
				// 할일
				// 반환값 변수
				int cnt = 0;
				// 커넥션
				con = db.getCon();
				// 질의명령
				String sql = uSQL.getSQL(uSQL.DEL_MEMBER);
				// 명령전달도구
				pstmt = db.getPSTMT(con, sql);
				try{
					// 질의명령 완성
					pstmt.setInt(1, mno);
					// 질의명령 보내고 결과받고..
					cnt = pstmt.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					db.close(pstmt);
					db.close(con);
				}
				// 결과 반환하고
				return cnt;
			}
			
			// 회원정보수정 데이터베이스 작업 전담 처리함수
			public int editMyInfo(int mno, String psql) {
				// 반환값 변수
				int cnt = 0;
				// 커넥션
				con = db.getCon();
				// 질의명령
				String sql = uSQL.getSQL(uSQL.EDIT_MEMBER);
				// 질의명령 수정하고
				sql = sql.replace("###", psql);
				
				// 명령 전달도구
				pstmt = db.getPSTMT(con, sql);
				try {
					// 질의명령 완성하고
					pstmt.setInt(1, mno);
					// 질의명령 보내고 결과받고
					cnt = pstmt.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					db.close(pstmt);
					db.close(con);
				}
				
				// 결과 반환하고
				return cnt;
			}
		}