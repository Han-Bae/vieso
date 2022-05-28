package com.viseo.dao;

import java.sql.*;
import java.util.*;

import com.viseo.db.*;
import com.viseo.sql.*;
import com.viseo.vo.*;

/**
 * 
 * @author	한서라
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.24	-	담당자 : 한서라
 * 								내	용 : 클래스 제작
 */
public class UpdateDao {

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
	
		// 회원정보를 불러오기
			public UpdateVO getIdInfo(String id) {
				UpdateVO fVO = new UpdateVO();
				con = db.getCon();
				String sql = uSQL.getSQL(uSQL.SEL_MEMBER_INFO);
				pstmt = db.getPSTMT(con, sql);
				try {
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
					rs.next();
					fVO.setMno(rs.getInt("mno"));
					fVO.setName(rs.getString("name"));
					fVO.setId(rs.getString("id"));
					fVO.setMail(rs.getString("mail"));
					fVO.setAddr(rs.getString("addr"));
					fVO.setGen(rs.getString("gen"));
					fVO.setNickname(rs.getString("nickname"));
					fVO.setTel(rs.getString("tel"));
					fVO.setBirth(rs.getString("birth"));
					fVO.setJoindate(rs.getDate("joindate"));
					fVO.setAreaname(rs.getString("areaname"));
					
					
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					db.close(rs);
					db.close(pstmt);
					db.close(con);
				}
				return fVO;
			}
		
			
			//회원탈퇴를 처리하는 메서드 선언
			public boolean deleteId(String id, String password) {

				boolean result = false;
				String dbpw="";

				try {
					con = db.getCon();
					String sql = uSQL.getSQL(uSQL.DEL_MEMBER2);
					pstmt = db.getPSTMT(con, sql);
					pstmt.setString(1, id);
					rs  = pstmt.executeQuery();
				if(rs.next()) {
					dbpw = rs.getString("pw");
					if(dbpw.equals(password)) {
						String delsql = uSQL.getSQL(uSQL.DEL_MEMBER);
						pstmt = con.prepareStatement(delsql);
						pstmt.setString(1, id);
						pstmt.executeUpdate();
						result = true;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.close(pstmt);
					db.close(con);
				}
				return result;
			}
		
			//  회원정보 수정
			public int editMyInfo(String id, String psql) {
				int cnt = 0;
				con = db.getCon();
				String sql = uSQL.getSQL(uSQL.EDIT_MEMBER);
				sql = sql.replace("###", psql);
				
				pstmt = db.getPSTMT(con, sql);
				try {
					pstmt.setString(1, id);
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