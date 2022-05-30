package com.viseo.dao;

import java.sql.*;
import java.util.ArrayList;

import com.viseo.db.*;
import com.viseo.sql.*;
import com.viseo.vo.*;

/**
 * 
 * @author  정유나
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 정유나
 * 								내	용 : 회원가입 Dao
 *
 */

public class JoinDao {

	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private JoinSQL jSQL;
	
	public JoinDao() {
		db = new BlpDBCP();
		jSQL = new JoinSQL();
	}

	
	// 회원 가입 데이터베이스 작업 전담 처리함수
	public int addMember(JoinVO jVO) {
		// 반환값 변수
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = jSQL.getSQL(jSQL.ADD_MEMBER);
		// 명령전달도구
		pstmt = db.getPSTMT(con, sql);
		try {
			// 질의명령 완성
			pstmt.setString(1, jVO.getName());
			pstmt.setString(2, jVO.getId());
			pstmt.setString(3, jVO.getPw());
			pstmt.setString(4, jVO.getMail());
			pstmt.setInt(5, jVO.getAddr());
			pstmt.setString(6, jVO.getGen());
			pstmt.setString(7, jVO.getNickname());
			pstmt.setString(8, jVO.getTel());
			pstmt.setString(9, jVO.getBirth());
			
			// 질의명령 보내고 결과받기
			cnt = pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(pstmt);
				db.close(con);
			}			
			return cnt;
			}

	
	// 아이디 중복 체크 전담 처리함수
	public int getIdCount(String id) {
		// 반환값 변수
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = jSQL.getSQL(jSQL.SEL_ID_CNT);
		// 명령전달도구 준비
		pstmt = db.getPSTMT(con, sql);
		try {
			// 질의명령 완성
			pstmt.setString(1, id);
			// 보내고 결과받고
			rs = pstmt.executeQuery();
			// 레코드포인터 한줄 내리고
			rs.next();
			
			// 데이터꺼내서 변수에 담고
			cnt = rs.getInt("cnt");
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(rs);
				db.close(pstmt);
				db.close(con);
			}
			// 데이터 반환하고
			return cnt;
			}

	
	// 닉네임 중복 체크 전담 처리함수
	public int getNcnameCount(String nickname) {
		// 반환값 변수
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = jSQL.getSQL(jSQL.SEL_NCNAME_CNT);
		// 명령전달도구 준비
		pstmt = db.getPSTMT(con, sql);
		try {
			// 질의명령 완성
			pstmt.setString(1, nickname);
			// 보내고 결과받고
			rs = pstmt.executeQuery();
			// 레코드포인터 한줄 내리고
			rs.next();
			
			// 데이터꺼내서 변수에 담고
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 데이터 반환하고
		return cnt;
	}
	
	// 지역 이름 조회 전담 처리함수
	public ArrayList<String> getAreaName() {
		ArrayList<String> list = new ArrayList<String>();
		
		// 커넥션 연결
		con = db.getCon();
		String sql = jSQL.getSQL(jSQL.SEL_AREA_NAME);
		stmt = db.getSTMT(con);
		
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString("areaname"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return list;
	}
	
	// 지역 도시 조회 전담 처리함수
	// area 테이블의 areano에 따른 city 조회
	public ArrayList<JoinVO> getCityList(String areaname){
		ArrayList<JoinVO> list = new ArrayList<JoinVO>();
		
		// 커넥션 연결
		con = db.getCon();
		String sql = jSQL.getSQL(jSQL.SEL_AREA_CITY);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			// areaname을 파라미터로
			pstmt.setString(1, areaname);
			
			rs = pstmt.executeQuery();
			
			// 반복문으로 배열에 값 넣기
			while(rs.next()) {
				JoinVO jVO = new JoinVO();
				
				jVO.setAddr(rs.getInt("areano"));
				jVO.setCity(rs.getString("city"));
				
				list.add(jVO);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}
	
	// 메일 중복 체크 전담 처리함수
	public int getMailCount(String mail) {
		// 반환값 변수
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = jSQL.getSQL(jSQL.SEL_MAIL_CNT);
		// 명령전달도구 준비
		pstmt = db.getPSTMT(con, sql);
		try {
			// 질의명령 완성
			pstmt.setString(1, mail);
			// 보내고 결과받고
			rs = pstmt.executeQuery();
			// 레코드포인터 한줄 내리고
			rs.next();
			
			// 데이터꺼내서 변수에 담고
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 데이터 반환하고
		return cnt;
	}
}