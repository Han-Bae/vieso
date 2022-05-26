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

	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		
		case SEL_MEMBER_INFO:
			buff.append("SELECT ");
			buff.append("	mno, name, id, mail, tel, m.gen, joindate, ano, savename ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	m.isshow = 'Y' ");
			buff.append("	AND id = ? ");
			break;
		
		case DEL_MEMBER:
			buff.append("UPDATE ");
			buff.append("	member ");
			buff.append("SET ");
			buff.append("	isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			buff.append("	AND mno = ? ");
			break;
		case EDIT_MEMBER:
			buff.append("UPDATE ");
			buff.append("	member ");
			buff.append("SET ");
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
			buff.append("	AND mno = ? ");
			break;
		
		}
		return buff.toString();
	}
}


