package com.viseo.db;

import javax.sql.*;

import java.sql.*;

import javax.naming.*;
/**
 *  이 클래스는 커넥션 폴에 있는 커넥션을 이용해서
 *  데이터베이스 작업에 필용한 자원을 만들어주는 유틸리티적인 클래스이다.
 * @author 김태현
 * @since 2022.05.12
 * @version v.1.0
 * @see
 */
public class BlpDBCP {
	// 커넥션 풀을 관리할 변수를 준비한다
	public DataSource ds;
	/*
	 	이 클래스를 누군가 new 시키면
	 	context.xml 파일에 등록된 자원을 가지고 오도록 해야한다.
	 	이처럼 context.xml파일에 등록된 자원을 가지고 오는 기법을
	 	JNDI(Java Naming and Directory Interface) 기법이라고 한다.
	 */
	public BlpDBCP() {
		try {
			// 1. context.xml 파일에 등록된 자원을 알아낸다.
			InitialContext context = new InitialContext();
			// 2. 그 중에서 필요한 자원을 얻어온다.
			ds = (DataSource)context.lookup("java:/comp/env/jdbc/hanbae");
			/*
			 	찾을 이름을 정하는 규칙
			 		java:/comp/env/찾을이름
			 	우리의 경우
			 		java:/comp/env/jdbc/TestDB
			 	
			 	-> 서버의 환경 중에서 jdbc/TestDB라는 이름의 자원을 찾아주세요
			 		-> 커넥션 풀을 찾아주세요.
			 	
			 	-> 이 작업이 무사히 완료되면
			 		커넥션 풀을 찾아서 기억해둔 것이다.(ds 변수에)
			 */
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 		필요한 순간에 접속을 해야할 경우
	 		이 때 접속은 직접하는 것이 아니고
	 		커넥션 풀이 가지고 있는 커넥션을 하나 꺼내오는 것이 된다.
	 */
	public Connection getCon() {
		// 반환값 변수
		Connection con = null;
		try {
			// -> 커넥션 풀을 관리하는 DataSource에서 하나 꺼내온다.
			con = ds.getConnection();			
		} catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
	
	public Statement getSTMT(Connection con) {
		// 반환값 변수
		Statement stmt = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch(Exception e) {}
		return stmt;
	}
	
	// PreparedStatement 가 필요하면 만들어주는 함수
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch(Exception e) {}
		return pstmt;
	}
	
	// 다 사용한 자원을 반환해주는 함수
	public void close(Object o) {
		try {
			if(o instanceof Connection) {
				((Connection)o).close();
			}else if(o instanceof Statement) {
				((Statement)o).close();
			}else if(o instanceof PreparedStatement) {
				((PreparedStatement)o).close();
			}else if(o instanceof ResultSet) {
				((ResultSet)o).close();
			}
		} catch(Exception e) {}
		
	}
}
