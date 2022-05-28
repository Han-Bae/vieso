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
			buff.append("	A.mno, A.name, A.id, A.pw, A.mail, A.addr, A.gen, A.nickname, A.tel, A.birth, A.joindate, A.isshow, B.areaname, B.city ");
			buff.append("FROM ");
			buff.append("	member A");
			buff.append("	INNER JOIN");
			buff.append("	AREA B ON ");
			buff.append(" A.ADDR = B.areaNo ");
			//buff.append("INNER JOIN AREA B ON A.ADDR = B.areaNo");
			buff.append("WHERE ");
			buff.append("	A.isshow = 'Y' ");
			buff.append("	AND A.id = ? ");
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
			buff.append("	### ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			buff.append("	AND id = ? ");
			break;
		
		}
		return buff.toString();
	}
}


