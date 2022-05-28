package com.viseo.sql;

/**
 * 
 * @author  정유나
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 정유나
 * 								내	용 : 회원가입 SQL
 *
 */

public class JoinSQL {
	public final int SEL_ID_CNT	= 1005;
	public final int SEL_NCNAME_CNT	= 1006;
	
	public final int ADD_MEMBER = 3001;

	public final int SEL_AREA_INFO = 2001;
	public final int SEL_AREA_NAME = 2002;
	public final int SEL_AREA_CITY = 2003;
		
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		// 멤버 생성
		case ADD_MEMBER:
			buff.append("INSERT INTO ");
			buff.append("	member(mno, name, id, pw, mail, addr, gen, nickname, tel, birth) ");
			buff.append("VALUES( ");
			buff.append("		(SELECT NVL(MAX(mno) + 1, 0001) FROM member), "); //서브질의로 회원번호 자동입력받기
			buff.append("		?, ?, ?, ?, ?, ?, ?, ?, ? ");
			buff.append(")");
			break;
		// 아이디 중복 체크
		case SEL_ID_CNT:
			buff.append("SELECT ");
			buff.append("	COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			break;		
		// 닉네임 중복 체크
		case SEL_NCNAME_CNT:
			buff.append("SELECT ");
			buff.append("	COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	nickname = ? ");
			break;		
		// 지역 데이터 불러오기
		case SEL_AREA_INFO:
			buff.append("SELECT ");
			buff.append("	COUNT(*) cnt ");
/*			buff.append("	areano, areaname, city, x, y ");*/
			buff.append("FROM ");
			buff.append("	area ");
			buff.append("WHERE ");
			buff.append("	?, ?, ?, ?, ? ");
			buff.append(") ");
			break;
		case SEL_AREA_NAME:
			buff.append("SELECT ");
			buff.append("	DISTINCT areaname ");
			buff.append("FROM ");
			buff.append("	area ");
			buff.append("ORDER BY ");
			buff.append("	areaname ");
			break;
		case SEL_AREA_CITY:
			buff.append("SELECT ");
			buff.append("	areano, city ");
			buff.append("FROM ");
			buff.append("	area ");
			buff.append("WHERE ");
			buff.append("	areaname = ? ");
			buff.append("ORDER BY ");
			buff.append("	city ");
			break;
		}

		return buff.toString();
	}
	
}
