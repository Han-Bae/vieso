package com.viseo.dao;

import java.sql.*;
import java.util.*;

import com.viseo.db.*;
import com.viseo.sql.*;
import com.viseo.vo.*;

/**
 * �� Ŭ������ ȸ�� ���� �����ͺ��̽� �۾��� �����ؼ� ó���ϴ� Ŭ����
 * @author �Ѽ���
 * @since 2022.05.26
 * @version v.1.0
 * 		�۾� �̷�)
 * 				2022.05.26	- Ŭ���� ����
 * 						����� �Ѽ���
 */
public class UpdateDao {
/*
 	�� Ŭ������ �� Ŭ������ new �� ���� �����ͺ��̽� �۾��� �� �غ�
 	�Ǿ� �־�� �Ѵ�.
 */
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
	
	// ���̵�� ȸ�� ������ȸ ���� ó���Լ�
			public UpdateVO getIdInfo(String id) {
				// ��ȯ�� ����
				UpdateVO fVO = new UpdateVO();
				// Ŀ�ؼ�
				con = db.getCon();
				// ���Ǹ��
				String sql = uSQL.getSQL(uSQL.SEL_MEMBER_INFO);
				// ������޵���
				pstmt = db.getPSTMT(con, sql);
				try {
					// ���Ǹ�� �ϼ�
					pstmt.setString(1, id);
					// ���Ǹ�� ������ ����ް�
					rs = pstmt.executeQuery();
					// ������ VO�� ���
					rs.next();
					// vo setting
					fVO.setMno(rs.getInt("mno"));
					fVO.setName(rs.getString("name"));
					fVO.setId(rs.getString("id"));
					fVO.setMail(rs.getString("mail"));
					fVO.setAddr(rs.getString("addr"));
					fVO.setGen(rs.getString("gen"));
					fVO.setNickname(rs.getString("nickname"));
					fVO.setTel(rs.getString("tel"));
					fVO.setJoindate(rs.getDate("joindate"));
					
					
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					db.close(rs);
					db.close(pstmt);
					db.close(con);
				}
				
				// ������ ��ȯ���ְ�
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
		
	
			// ȸ���������� �����ͺ��̽� �۾� ���� ó���Լ�
			public int editMyInfo(String id, String psql) {
				// ��ȯ�� ����
				int cnt = 0;
				// Ŀ�ؼ�
				con = db.getCon();
				// ���Ǹ��
				String sql = uSQL.getSQL(uSQL.EDIT_MEMBER);
				// ���Ǹ�� �����ϰ�
				sql = sql.replace("###", psql);
				
				// ��� ���޵���
				pstmt = db.getPSTMT(con, sql);
				try {
					// ���Ǹ�� �ϼ��ϰ�
					pstmt.setString(1, id);
					// ���Ǹ�� ������ ����ް�
					cnt = pstmt.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					db.close(pstmt);
					db.close(con);
				}
				
				// ��� ��ȯ�ϰ�
				return cnt;
			}
		}