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
	
	public String getMainDateNTime() {
		String mt = "";
		
		con = db.getCon();
		String sql = maSQL.getSQL(maSQL.SEL_SYSDATE);
		stmt = db.getSTMT(con);
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			mt = rs.getString("mt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return mt;
	}
}
