package com.viseo.sql;

public class WriteSQL {
	public final int ADD_TODO			= 1001;
	public final int SELECT_READ		= 1002;
	public final int SELECT_READ_CNT	= 1003;
	public final int UPDATE_TODO		= 1004;
	
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
			buff.append(" 	TODOCATEGORY, TITLE, AREA, TODODATE, NVL(TODOTIME, '종일') AS TODOTIME, ALARMREPEAT, MEMO ");
			buff.append(" FROM ");
			buff.append(" 	TODO ");
			buff.append(" WHERE ");
			buff.append(" 	ID = ? ");
			buff.append(" 	AND TODODATE = ? ");
			break;

			case SELECT_READ_CNT:
			buff.append("SELECT COUNT(*) cnt ");
			buff.append("FROM TODO ");
			buff.append("WHERE ");
			buff.append("	ID = ? ");
			buff.append(" 	AND TODODATE = ? ");
			break; 
			
			case UPDATE_TODO:
			buff.append("UPDATE TODO ");
			buff.append("SET TITLE = ? ");
			buff.append(", TODOTIME = ?");
			buff.append(", MEMO = ?");
			buff.append(", ALARMREPEAT = ?");
			buff.append(", TODOCATEGORY = ?");
			buff.append("WHERE ");
			buff.append("	ID = ? ");
			buff.append(" 	AND TODODATE = ? ");
			break; 
		}
		return buff.toString();
	}
}

	
