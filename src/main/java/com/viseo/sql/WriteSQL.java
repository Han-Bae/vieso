package com.viseo.sql;

/**
 * 	//할일 저장 //할일 조회 //할일 기존등록 건수조회 //할일 수정 //기본 지역조회 //상세 지역조회
 * 
 * 
 * @author	박형근
 * @since	2022.05.26-29
 * @version	v.1.0
 * 
* 			작업이력	]
 * 				2022.05.26-29	-	담당자 : 박형근
 * 									내	용 : //할일 저장 //할일 조회 //할일 기존등록 건수조회 //할일 수정 //기본 지역조회 //상세 지역조회
 */

public class WriteSQL {
	public final int ADD_TODO			= 1001;
	public final int SELECT_READ		= 1002;
	public final int SELECT_READ_CNT	= 1003;
	public final int UPDATE_TODO		= 1004;
	public final int SEL_AREA_NAME		= 1005;
	public final int SEL_AREA_CITY		= 1006;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
			//할일 저장
			case ADD_TODO:
			buff.append(" INSERT INTO ");
			buff.append(" TODO(WNO, ID, TITLE, TODODATE, TODOTIME, MEMO, category, AREA, tddate, mno) ");
			buff.append(" VALUES( ");
			buff.append(" (SELECT NVL(MAX(WNO) + 1, 1) FROM TODO),?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-dd'),"
					+ " (SELECT mno FROM member m WHERE m.id = ?) ");
			buff.append(" ) ");
			break;
		 	
			//할일 조회
			case SELECT_READ:
			buff.append(" SELECT ");
			buff.append(" 	CATEGORY, TITLE, AREA, TODODATE, NVL(TODOTIME, '종일') AS TODOTIME, MEMO ");
			buff.append(" FROM ");
			buff.append(" 	TODO ");
			buff.append(" WHERE ");
			buff.append(" 	ID = ? ");
			buff.append(" 	AND TODODATE = ? ");
			break;

			//할일 기존등록 건수조회
			case SELECT_READ_CNT:
			buff.append("SELECT COUNT(*) cnt ");
			buff.append("FROM TODO ");
			buff.append("WHERE ");
			buff.append("	ID = ? ");
			buff.append(" 	AND TODODATE = ? ");
			break; 
			
			//할일 수정
			case UPDATE_TODO:
			buff.append("UPDATE TODO ");
			buff.append("SET TITLE = ? ");
			buff.append(", TODOTIME = ?");
			buff.append(", MEMO = ?");
			buff.append(", CATEGORY = ?");
			buff.append("WHERE ");
			buff.append("	ID = ? ");
			buff.append(" 	AND TODODATE = ? ");
			break; 
			
			//기본 지역조회
			case SEL_AREA_NAME:
				buff.append("SELECT ");
				buff.append("	DISTINCT areaname ");
				buff.append("FROM ");
				buff.append("	area ");
				buff.append("ORDER BY ");
				buff.append("	areaname ");
				break;
			//상세 지역조회
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

	
