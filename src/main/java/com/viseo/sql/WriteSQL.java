package com.viseo.sql;

public class WriteSQL {
	public final int ADD_TODO		= 1001;
	public final int SELECT_READ	= 1002;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
			case ADD_TODO:
			buff.append(" INSERT INTO ");
			buff.append(" TODO(WNO, ID, TITLE, TODODATE, TODOTIME, MEMO, ALARMREPEAT, TODOCATEGORY, AREA) ");
			buff.append(" VALUES( ");
			buff.append(" (SELECT NVL(MAX(WNO) + 1, 1) FROM TODO),?, ?, ?, ?, ?, ?, ?, ? ");
			buff.append(" ) ");
			break;
		 	
			case SELECT_READ:
			buff.append(" SELECT ");
			buff.append(" 	TODOCATEGORY, TITLE, AREA, TODODATE, TODOTIME, ALARMREPEAT, MEMO ");
			buff.append(" FROM ");
			buff.append(" 	TODO ");
			buff.append(" WHERE ");
			buff.append(" 	ID = ? ");
			buff.append(" 	AND TODODATE = ? ");
			break;
		}
		return buff.toString();
	}
}

	
