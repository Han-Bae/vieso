package com.viseo.dao;

import java.sql.*;
import java.util.*;

import com.viseo.db.*;
import com.viseo.sql.*;
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
	

}
