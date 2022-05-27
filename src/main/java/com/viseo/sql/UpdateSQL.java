package com.viseo.sql;

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

public class UpdateSQL {
	public final int SEL_MEMBER_INFO 	= 1002;
	public final int DEL_MEMBER 		= 2001;
	public final int EDIT_MEMBER 		= 2002;
	public final int DEL_MEMBER2 		= 2003;

	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		
		case SEL_MEMBER_INFO:
			buff.append("SELECT ");
			buff.append("	mno, name, id, pw, mail, addr, gen, nickname, tel, birth, joindate, isshow ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			buff.append("	AND id = ? ");
			break;
		case DEL_MEMBER:
			buff.append("DELETE ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			break;
		case DEL_MEMBER2:
			buff.append("SELECT ");
			buff.append("	pw ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			break;
		case EDIT_MEMBER:
			buff.append("UPDATE ");
			buff.append("	member ");
			buff.append("SET ");
			/*
			buff.append("	pw = ? ");
			buff.append("	mail = ? ");
			buff.append("	tel = ? ");
			buff.append("	addr = ? ");
			buff.append("	nickname = ? ");
			*/
			buff.append("	### ");
			/*
				비밀번호를 수정하는 경우
					pw = ?
				메일을 수정하는 경우
					mail = ?
				tel
					tel = ?
				avatar
					avt = ?
					
				수정되는 가지수에 따라서
				질의명령의 set 절이 달라질 수 있다.
				이렇게 동적질의를 사용해야 할 경우는
				바뀌는 부분을 특수문자로 표시를 해두고
				데이터에 따라서 특수문자 대신 채워주면 된다.
			 */
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			buff.append("	AND id = ? ");
			break;
		
		}
		return buff.toString();
	}
}


