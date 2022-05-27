package com.viseo.sql;

/**
 * 
 * @author	전다빈
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.24	-	담당자 : 전다빈
 * 								내	용 : 클래스 제작
 * 
 * 				2022.05.26	-	담당자 : 전다빈
 * 								내	용 : 년도 월 가져오는 질의명령 작성 
 * 
 * 				2022.05.27	-	담당자 : 전다빈
 * 								내	용 : 이번달의 첫날 마지막날 값 가져오는 쿼리문 작성
 * 										 쿼리문 합쳤음
 */

public class MainSQL {
	public final int SEL_DATE = 1001;
	public final int SEL_USER_INFO = 1002;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_DATE:
			buff.append("SELECT ");
			buff.append("	TO_CHAR(sysdate, 'YYYYMMddHH24') todaydate, ");
			buff.append("	TO_CHAR(LAST_DAY(sysdate), 'YYYYMMdd') lastdate, ");
			buff.append("	TO_CHAR( ");
			buff.append("	    TO_DATE( ");
			buff.append("	        CONCAT( ");
			buff.append("	            TO_CHAR(sysdate, 'YYMM'), '01' ");
			buff.append("	        ), 'YYMMdd' ");
			buff.append("	    ), 'dy' ");
			buff.append("	) firstday ");
			buff.append("FROM ");
			buff.append("    dual ");
			break;
		case SEL_USER_INFO:
			buff.append("SELECT ");
			buff.append("    m.mno, areano, areaname, city, x, y, dir, savename ");
			buff.append("FROM ");
			buff.append("    member m, area ar, avatar av ");
			buff.append("WHERE ");
			buff.append("    m.addr = ar.areano ");
			buff.append("    AND m.mno = av.mno ");
			buff.append("    AND id = ? ");
			break;
		// 스케줄 가져오기
		}
		return buff.toString();
	}
	
}
