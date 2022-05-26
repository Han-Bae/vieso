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
 */

public class MainSQL {
	public final int SEL_SYSDATE = 1001;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_SYSDATE:
			buff.append("SELECT ");
			buff.append("    TO_CHAR(sysdate, 'YYYYMMddHH24') mt ");
			buff.append("FROM ");
			buff.append("    dual ");
			break;
		}
		return buff.toString();
	}
	
}
