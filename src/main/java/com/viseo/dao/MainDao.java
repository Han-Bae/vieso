package com.viseo.dao;

import java.sql.*;
import java.time.LocalDate;

import com.viseo.db.BlpDBCP;
import com.viseo.sql.MainSQL;
import com.viseo.vo.*;

/**
 * 
 * @author	전다빈
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 전다빈
 * 								내	용 : 클래스 제작
 * 
 * 				2022.05.27	-	담당자 : 전다빈
 * 								내	용 : 쿼리문 변경으로 매소드 합침
 * 
 * 				2022.05.28	-	담당자 : 전다빈
 * 								내	용 : 캘린더 세팅 매소드, 스케줄 세팅 매소드
 */


public class MainDao {
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	MainSQL maSQL;
	
	public MainDao() {
		db = new BlpDBCP();
		maSQL = new MainSQL();
	}
	
	
	public MainVO getMainDate(Timestamp date) {
		MainVO maVO = new MainVO();
		
		con = db.getCon();
		String sql = maSQL.getSQL(maSQL.SEL_DATE);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setTimestamp(1, date);
			pstmt.setTimestamp(2, date);
			pstmt.setTimestamp(3, date);
			
			rs = pstmt.executeQuery();
			rs.next();
			maVO.setTodayDate(rs.getString("todaydate"));
			maVO.setLastDate(rs.getString("lastdate"));
			maVO.setFirstDay(rs.getString("firstday"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return maVO;
	}
	
	
	public void getUserInfo(MainVO maVO, String id) {
		con = db.getCon();
		String sql = maSQL.getSQL(maSQL.SEL_USER_INFO);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			maVO.setMno(rs.getInt("mno"));
			maVO.setAddr(rs.getInt("areano"));
			maVO.setAreaname(rs.getString("areaname"));
			maVO.setCity(rs.getString("city"));
			maVO.setX(rs.getInt("x"));
			maVO.setY(rs.getInt("y"));
			maVO.setDir(rs.getString("dir"));
			maVO.setSavename(rs.getString("savename"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
	}
	
	public MainVO getTodoCnt(String id, String date) {
		MainVO maVO = new MainVO();
		
		con = db.getCon();
		String sql = maSQL.getSQL(maSQL.SEL_CATEGORY_CNT);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			rs.next();
			maVO.setCnt(rs.getInt("cnt"));
			maVO.setCategory(rs.getString("category"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return maVO;
	}
	
}
