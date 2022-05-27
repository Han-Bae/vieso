package com.viseo.dao;

import java.sql.*;

import com.viseo.db.BlpDBCP;
import com.viseo.sql.MainSQL;
import com.viseo.vo.MainVO;

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
 * 								내	용 : 쿼리문 변경으로 함수 합침
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
	
	public MainVO getMainDate() {
		MainVO maVO = new MainVO();
		
		con = db.getCon();
		String sql = maSQL.getSQL(maSQL.SEL_DATE);
		stmt = db.getSTMT(con);
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			maVO.setTodayDate(rs.getString("todaydate"));
			maVO.setLastdate(rs.getString("lastdate"));
			maVO.setFirstday(rs.getString("firstday"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return maVO;
	}
	
	public void getUserInfo(MainVO maVO) {
		con = db.getCon();
	}
}
