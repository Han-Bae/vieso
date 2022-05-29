package com.viseo.dao;

import java.sql.*;
import java.util.*;

import com.viseo.controller.kth.MemberVO;
import com.viseo.db.*;
import com.viseo.sql.*;
import com.viseo.vo.*;

/**
 * 이 클래스는 회원 관련 데이터베이스 작업을 전담해서 처리하는 클래스
 * @author 김태현
 * @since 2022.05.26
 * @version v.1.0
 * 		작업 이력)
 * 				2022.05.26	- 클래스 제작
 * 						담당자 김태현
 */
public class LoginDao {
/*
 	이 클래스는 이 클래스가 new 된 순간 데이터베이스 작업을 할 준비가
 	되어 있어야 한다.
 */
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private LoginSQL lSQL;
	
	public LoginDao() {
		db = new BlpDBCP();
		lSQL = new LoginSQL();
	}
	
	// 가입 가능한 이메일인지 검사
	public int getOK(String mail) {
		int cnt = 0;
		con = db.getCon();
		String sql = lSQL.getSQL(lSQL.SEL_OK_CK);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, mail);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// 로그인 데이터베이스 작업 전담 처리함수
	public int getLogin(String id, String pw) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = lSQL.getSQL(lSQL.SEL_LOGIN_CNT);
		// 명령전달도구
		pstmt = db.getPSTMT(con, sql);
		// 질의명령 완성
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			// 보내고 결과 받기
			rs = pstmt.executeQuery();
			// 결과에서 데이터꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
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
	// 로그인 아이디 찾기 정보 확인 처리 함수
	public int checkUserId(String name, String mail) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = lSQL.getSQL(lSQL.SEL_FID_CNT);
		// 명령전달도구
		pstmt = db.getPSTMT(con, sql);
		// 질의명령 완성
		try {
			pstmt.setString(1, name);
			pstmt.setString(2, mail);
			// 보내고 결과 받기
			rs = pstmt.executeQuery();
			// 결과에서 데이터꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
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
	
	// 아이디 알려주는 함수
	public String findID(String mail) {
		String id = "";
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = lSQL.getSQL(lSQL.SEL_FID_ID);
		// 명령전달도구
		pstmt = db.getPSTMT(con, sql);
		// 질의명령 완성
		try {
			pstmt.setString(1, mail);
			// 보내고 결과 받기
			rs = pstmt.executeQuery();
			// 결과에서 데이터꺼내고
			rs.next();
			id = rs.getString("id");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return id;
	}
	
	// 로그인 비밀번호 찾기 정보 확인 처리 함수
	public int checkUserPw(String id, String mail) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = lSQL.getSQL(lSQL.SEL_FPW_CNT);
		// 명령전달도구
		pstmt = db.getPSTMT(con, sql);			
		// 질의명령 완성
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, mail);
			// 보내고 결과 받기
			rs = pstmt.executeQuery();
			// 결과에서 데이터꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
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
	
	// 비밀번호 재설정 처리
	public int editPW(String id, String pw) {
		int cnt = 0;
		con = db.getCon();
		String sql = lSQL.getSQL(lSQL.EDIT_PW);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// 이메일인증 통과한 회원가입 허가
	public int joinOK(String mail) {
		int cnt = 0;
		con = db.getCon();
		String sql = lSQL.getSQL(lSQL.EDIT_ST_MAIL);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, mail);
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
		}
		return cnt;
	}
	
	// 회원 가입 인증 이메일 처리 함수
	public int addMail(String email) {
		int cnt = 0;
		con = db.getCon();
		String sql = lSQL.getSQL(lSQL.INSERT_MAIL);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, email);
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
}
