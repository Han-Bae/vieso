package com.viseo.dao;

import java.sql.*;
import java.util.*;

import com.viseo.db.*;
import com.viseo.sql.*;
import com.viseo.vo.*;

/**
 * 이 클래스는 todo 입력 관련 데이터베이스 작업을 전담해서 처리하는 클래스
 * @author 박형근
 * @since 2022.05.12
 * @version v.1.0
 * 		작업 이력)
 * 				2022.05.24	- 클래스 제작
 * 						담당자 박형근
 */
public class WriteDao {
/*
 	이 클래스는 이 클래스가 new 된 순간 데이터베이스 작업을 할 준비가
 	되어 있어야 한다.
 */
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private WriteSQL wSQL;
	
	public WriteDao() {
		db = new BlpDBCP();
		wSQL = new WriteSQL();
	}

	//할일 저장
	public int writeInsert(WriteVO wVO) {
		// 반환값 변수
		int cnt = 0;
		// 할일
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = wSQL.getSQL(wSQL.ADD_TODO);
		// 명령전달도구
		pstmt = db.getPSTMT(con, sql);
		try {
			
			System.out.println(wVO.getId());
			System.out.println(wVO.getTitle());
			System.out.println(wVO.getChcekDate());
			System.out.println(wVO.getChcekTime());
			System.out.println(wVO.getMemo());
			System.out.println(wVO.getAlarmRepeat());
			System.out.println(wVO.getCategory());
			System.out.println(wVO.getArea());
			
			
			// 질의명령 완성하고
			pstmt.setString(1, wVO.getId());	//ID
			pstmt.setString(2, wVO.getTitle());	//TITLE
			pstmt.setString(3, wVO.getChcekDate()); //TODODATE
			pstmt.setString(4, wVO.getChcekTime());	//TODOTIME
			pstmt.setString(5, wVO.getMemo()); //MEMO
			pstmt.setString(6, wVO.getAlarmRepeat()); 
			pstmt.setString(7, wVO.getCategory());
			pstmt.setString(8, wVO.getArea());
			
			// 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
			
			/* System.out.println("cnt=============="+cnt); */
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	// 할일 조회 페이지 
	public WriteVO writeSelect(String id, String chcekDate) {

		WriteVO wVO = new WriteVO();
		// 할일
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = wSQL.getSQL(wSQL.SELECT_READ);
		// 명령전달도구
		pstmt = db.getPSTMT(con, sql);
		try {
			
			System.out.println("WriteDao [id]========"+id);
			System.out.println("WriteDao [chcekDate]========"+chcekDate);
			
			// 질의명령 완성하고
			pstmt.setString(1, id);
			pstmt.setString(2, chcekDate);
			
			rs = pstmt.executeQuery();
			// get Data
			rs.next();
			
			// DB에서 조회한 값을 가져왔는지 확인해보자
			System.out.println("카테고리:"+rs.getString("TODOCATEGORY"));
			System.out.println("제목:"+rs.getString("TITLE"));
			System.out.println("지역:"+rs.getString("AREA"));
			System.out.println("시간:"+rs.getString("TODOTIME"));
			
			// 실제 DB에서 가져온 값을 wVO에 담자
			wVO.setCategory(rs.getString("TODOCATEGORY"));
			wVO.setTitle(rs.getString("TITLE"));
			wVO.setArea(rs.getString("AREA"));
			wVO.setChcekDate(rs.getString("TODODATE"));
			wVO.setChcekTime(rs.getString("TODOTIME"));
			wVO.setAlarmRepeat(rs.getString("ALARMREPEAT"));
			wVO.setMemo(rs.getString("MEMO"));
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return wVO;
	}
	
	//기존할일 건수조회
	public int writeCnt(WriteVO wVO) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = wSQL.getSQL(wSQL.SELECT_READ_CNT);
		// 명령전달도구
		pstmt = db.getPSTMT(con, sql);			
		// 질의명령 완성
		try {
			pstmt.setString(1, wVO.getId());	//ID
			pstmt.setString(2, wVO.getChcekDate()); //TODODATE
			// 보내고 결과 받기
			rs = pstmt.executeQuery();
			// 결과에서 데이터꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
			System.out.println("기존할일 건수는 몇건--------"+cnt);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		// 데이터 내보내기
		return cnt;
	}
	
	//할일 수정
	public int writeUpdate(WriteVO wVO) {
		// 반환값 변수
		int cnt = 0;
		// 할일
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = wSQL.getSQL(wSQL.UPDATE_TODO);
		// 명령전달도구
		pstmt = db.getPSTMT(con, sql);
		try {
			
			System.out.println("수정[ID]====="+wVO.getId());
			System.out.println("수정[타이틀]====="+wVO.getTitle());
			System.out.println("수정[날짜]====="+wVO.getChcekDate());
			System.out.println("수정[시간]====="+wVO.getChcekTime());
			System.out.println("수정[메모]====="+wVO.getMemo());
			System.out.println("수정[알람]====="+wVO.getAlarmRepeat());
			System.out.println("수정[카테고리]====="+wVO.getCategory());
			
			
			// 질의명령 완성하고
			pstmt.setString(1, wVO.getTitle());			//TITLE
			pstmt.setString(2, wVO.getChcekTime());		//TODOTIME
			pstmt.setString(3, wVO.getMemo()); 			//MEMO
			pstmt.setString(4, wVO.getAlarmRepeat()); 	//ALARMREPEAT
			pstmt.setString(5, wVO.getCategory());		//TODOCATEGORY
			pstmt.setString(6, wVO.getId());			//ID
			pstmt.setString(7, wVO.getChcekDate());		//TODODATE
			
			// 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
			
			System.out.println("[수정]cnt=============="+cnt);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

}