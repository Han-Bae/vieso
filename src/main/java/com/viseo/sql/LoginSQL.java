package com.viseo.sql;

/**
 * 
 * @author	김태현
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.24	-	담당자 : 김태현
 * 								내	용 : 클래스 제작
 */

public class LoginSQL {
	public final int SEL_LOGIN_CNT 	= 1001;
	public final int SEL_FID_CNT 	= 1002;
	public final int SEL_FID_ID 	= 1003;
	public final int SEL_FPW_CNT 	= 1004;
	public final int SEL_OK_CK		= 1005;
	
	public final int INSERT_MAIL	= 2001;
	
	public final int SEL_ALL 		= 3005;

	public final int EDIT_PW 		= 4001;
	public final int EDIT_ST_MAIL	= 4002;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
				case SEL_ALL:
			buff.append("select mno, id, addr, name, pw, mail, gen, nickname ");
			buff.append("from member ");			
				break;
		// 로그인 확인 질의문
				case SEL_LOGIN_CNT:
		buff.append("SELECT COUNT(*) cnt ");
		buff.append("FROM MEMBER ");
		buff.append("WHERE ");
		buff.append("	isshow = 'Y' ");
		buff.append("	AND id = ? ");
		buff.append("	AND pw = ? ");
				break;   
		// 아이디 찾기 유저 확인 질의문
				case SEL_FID_CNT:
		buff.append("SELECT COUNT(*) cnt ");
		buff.append("FROM MEMBER ");
		buff.append("WHERE ");
		buff.append("	isshow = 'Y' ");
		buff.append("	AND name = ? ");
		buff.append("	AND mail = ? ");
				break;
				
		// 아이디 찾기 질의문
				case SEL_FID_ID:
		buff.append("SELECT id ");
		buff.append("FROM MEMBER ");
		buff.append("WHERE ");
		buff.append("	isshow = 'Y' ");
		buff.append("	AND mail = ? ");
				break;   
	
	
	 // 비밀번호 찾기 유저 확인 질의문
				case SEL_FPW_CNT:
		buff.append("SELECT COUNT(*) cnt ");
		buff.append("FROM MEMBER ");
		buff.append("WHERE ");
		buff.append("	isshow = 'Y' ");
		buff.append("	AND id = ? ");
		buff.append("	AND mail = ? ");
				break; 
	// 비밀번호 재설정 질의문
				case EDIT_PW:
		buff.append("UPDATE MEMBER ");
		buff.append("SET pw = ? ");
		buff.append("WHERE ");
		buff.append("	isshow = 'Y' ");
		buff.append("	AND id = ? ");
				break; 
	// 이메일 등록 질의문
				case INSERT_MAIL:
		buff.append("INSERT INTO EMAIL(email) ");
		buff.append("VALUES( ");
		buff.append("	?) ");
			break; 
	// 이메일 허가 체크 질의문
				case SEL_OK_CK:
		buff.append("SELECT COUNT(*) cnt ");
		buff.append("FROM EMAIL ");
		buff.append("WHERE ");
		buff.append("	isokay = 'N' ");
		buff.append("   AND email = ? ");
			break; 
			
	// 이메일 가입 허가 질의문('Y'면 이미 가입된 거)
				case EDIT_ST_MAIL:
		buff.append("UPDATE EMAIL ");
		buff.append("SET isokay = 'Y' ");
		buff.append("WHERE ");
		buff.append("	email = ? ");
			  break;			 		
		 
}
	
		return buff.toString();
	}
	
}
