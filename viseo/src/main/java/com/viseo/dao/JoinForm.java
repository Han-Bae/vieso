package com.viseo.dao;

/**
 *	DB에 지역 데이터 넣는 Dao
 *
 *
 * @author	전다빈
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 전다빈
 * 								내	용 : 지역 데이터 Dao
 * 
 */

import java.sql.*;

import com.viseo.db.*;
import com.viseo.sql.*;
import com.viseo.resources.AreaData;

public class AreaDao {
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private AreaSQL aSQL;

	public AreaDao() {
		db = new BlpDBCP();
		aSQL = new AreaSQL();
	}
	
	public void pushAreaData() {
		con = db.getCon();
		String sql = aSQL.getSQL(aSQL.INSERT_AREA);
		pstmt = db.getPSTMT(con, sql);
		
		AreaData aData = new AreaData();
		try {
			int cnt = 0;
			System.out.println("데이터 삽입 시작");
			for(String[] arr : aData.getArr()) {
				pstmt.setInt(1, Integer.parseInt(arr[0]));
				pstmt.setString(2, arr[1]);
				pstmt.setString(3, arr[2]);
				pstmt.setInt(4, Integer.parseInt(arr[3]));
				pstmt.setInt(5, Integer.parseInt(arr[4]));
				cnt += pstmt.executeUpdate();
			}
			
			// 잘들어가면 248개 콘솔에 출력되어야 함
			System.out.println("입력된 지역 데이터 수 " + cnt);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
	}

}
