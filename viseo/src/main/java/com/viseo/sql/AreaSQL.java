package com.viseo.sql;

/**
 * 	AREA 테이블에 지역번호, 1차 지역, 2차 지역, X값, Y값 넣는 질의명령 클래스
 * 
 * 
 * @author	전다빈
 * @since	2022.05.26
 * @version	v.1.0
 * 
* 			작업이력	]
 * 				2022.05.26	-	담당자 : 전다빈
 * 								내	용 : 지역 데이터 SQL
 */
public class AreaSQL {
	public final int INSERT_AREA = 1001;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case INSERT_AREA:
			buff.append("INSERT INTO area ");
			buff.append("( ");
			buff.append("	areano, areaname, city, x, y ");
			buff.append(") ");
			buff.append("VALUES ");
			buff.append("( ");
			buff.append("	?, ?, ?, ?, ? ");
			buff.append(") ");
			break;
		}
		return buff.toString();
	}

}
