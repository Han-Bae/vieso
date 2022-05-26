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
	public final int SEL_LOGIN_CNT = 1001;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		
		// 로그인 확인 질의문
				case SEL_LOGIN_CNT:
		buff.append("SELECT COUNT(*) cnt ");
		buff.append("FROM MEMBER ");
		buff.append("WHERE ");
		buff.append("	isshow = 'Y' ");
		buff.append("	AND id = ? ");
		buff.append("	AND pw = ? ");
				break;   
		}
		return buff.toString();
	}
	
}
