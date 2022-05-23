package com.viseo.dao;

import java.sql.*;
import java.util.*;

import com.viseo.sql.*;
import com.viseo.db.*;
import com.viseo.vo.*;

/**
 * 이 클래스는 회원 관련 데이터베이스 작업을 전담해서 처리하는 클래스
 * @author 김태현
 * @since 2022.05.12
 * @version v.1.0
 * 		작업 이력)
 * 				2022.05.12	- 클래스 제작
 * 						담당자 김태현
 */
public class MemberDao {
/*
 	이 클래스는 이 클래스가 new 된 순간 데이터베이스 작업을 할 준비가
 	되어 있어야 한다.
 */
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberSQL mSQL;
	
	public MemberDao() {
		db = new BlpDBCP();
		mSQL = new MemberSQL();
	}
	
	// 로그인 데이터베이스 작업 전담 처리함수
	public int getLogin(String id, String pw) {
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN_CNT);
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
	
	// 회원 가입 데이터베이스 작업 전담 처리함수
	public int addMember(EmpVO evo) {
		// 반환값 변수
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.ADD_MEMBER);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1,  evo.getName());
			pstmt.setString(2,  evo.getId());
			pstmt.setString(3,  evo.getPw());
			pstmt.setString(4,  evo.getMail());
			pstmt.setString(5,  evo.getTel());
			pstmt.setInt(6,  evo.getAno());
			pstmt.setString(7,  evo.getGen());
			
			cnt = pstmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// 아바타 리스트 조회 전담 처리함수
	public ArrayList<EmpVO> getAvtList(){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_ALL_AVT);
		stmt = db.getSTMT(con);
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				EmpVO eVO = new EmpVO();
				eVO.setAno(rs.getInt("ano"));
				eVO.setSavename(rs.getString("savename"));
				eVO.setGen(rs.getString("gen"));				
				list.add(eVO);
			}
			
		}catch(Exception e) {
			
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return list;
	}
	
	// 아이디 카운트 조회 전담 처리함수
	public int getIdCount(String id) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_ID_CNT);
		
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, id);
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
	
	// 회원 목록 조회 전담 처리함수
	public ArrayList<EmpVO> getMemberList(){
		ArrayList<EmpVO> list= new ArrayList<EmpVO>();
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MEMBER_LIST);
		stmt = db.getSTMT(con);
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				EmpVO mVO = new EmpVO();
				int mno = rs.getInt("mno");
				String name = rs.getString("name");
				
				mVO.setMno(mno);
				mVO.setName(name);
				
				list.add(mVO);
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
	
	// 회원번호로 회원 정보 조회 전담 처리함수
		public EmpVO getMnoInfo(int mno) {
			/*
				이함수가 데이터베이스에서 꺼내올 데이터는
				회원 한명의 데이터이기 때문에
				VO를 만들고 채워서 반환해주면 작업이 끝난다.
			 */
			EmpVO mVO = new EmpVO();
			// con
			con = db.getCon();
			// sql
			String sql = mSQL.getSQL(mSQL.SEL_MNO_INFO);
			// pstmt
			pstmt = db.getPSTMT(con, sql);
			try {
				// setting variable
				pstmt.setInt(1, mno);
				// send and receive
				rs = pstmt.executeQuery();
				// get Data
				rs.next();
				// vo setting
				mVO.setMno(rs.getInt("mno"));
				mVO.setName(rs.getString("name"));
				mVO.setId(rs.getString("id"));
				mVO.setMail(rs.getString("mail"));
				mVO.setTel(rs.getString("tel"));
				mVO.setGen(rs.getString("gen"));
				mVO.setAno(rs.getInt("ano"));
				mVO.setSavename(rs.getString("savename"));
				mVO.setJdate(rs.getDate("joindate"));
				mVO.setJtime(rs.getTime("joindate"));
				mVO.setSdate();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(rs);
				db.close(pstmt);
				db.close(con);
			}
			// return
			return mVO;
		}
		

		// 아이디로 회원 정보조회 전담 처리함수
		public EmpVO getIdInfo(String id) {
			// 반환값 변수
			EmpVO mVO = new EmpVO();
			// 커넥션
			con = db.getCon();
			// 질의명령
			String sql = mSQL.getSQL(mSQL.SEL_MEMBER_INFO);
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
				mVO.setMno(rs.getInt("mno"));
				mVO.setName(rs.getString("name"));
				mVO.setId(rs.getString("id"));
				mVO.setMail(rs.getString("mail"));
				mVO.setTel(rs.getString("tel"));
				mVO.setGen(rs.getString("gen"));
				mVO.setAno(rs.getInt("ano"));
				mVO.setSavename(rs.getString("savename"));
				mVO.setJdate(rs.getDate("joindate"));
				mVO.setJtime(rs.getTime("joindate"));
				mVO.setSdate();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(rs);
				db.close(pstmt);
				db.close(con);
			}
			
			// 데이터 반환해주고
			return mVO;
		}
	
	// 회원탈퇴 데이터베이스 작업 전담
	public int delMember(int mno) {
		//할일
		// 커넥션
		int cnt = 0;
		//질의명령
		con = db.getCon();
		// 명령전달도구
		String sql = mSQL.getSQL(mSQL.DEL_MEMBER);
		// 질의명령 완성
		pstmt = db.getPSTMT(con, sql);
		// 질의명령 보내고 결과받고..
		try {
			pstmt.setInt(1, mno);
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);	
		}
		return cnt;
	}
	
	// 회원 정보 수정 데이터베이스 작업 전담 처리 함수
	public int editMyInfo(int mno, String psql) {
		// 반환값 변수
		int cnt = 0;
		// 커넥션
		con = db.getCon();
		// 질의명령
		String sql = mSQL.getSQL(mSQL.EDIT_MEMBER);
		// 질의명령 수정하고
		sql = sql.replace("###", psql);
		// 명령 전달도구
		pstmt = db.getPSTMT(con, sql);
		try{// 질의명령 완성
			pstmt.setInt(1, mno);
			// 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 결과 반환하고
		return cnt;
	}
}
