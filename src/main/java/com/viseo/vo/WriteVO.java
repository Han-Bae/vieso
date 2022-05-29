package com.viseo.vo;

import java.sql.Time;
import java.util.Date;

public class WriteVO {
	private int addr;
	private String id, title, todoRepeat, memo, alarmRepeat, chcekDate, category, chcekTime , area, city;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTodoRepeat() {
		return todoRepeat;
	}
	public void setTodoRepeat(String todoRepeat) {
		this.todoRepeat = todoRepeat;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAlarmRepeat() {
		return alarmRepeat;
	}
	public void setAlarmRepeat(String alarmRepeat) {
		this.alarmRepeat = alarmRepeat;
	}
	public String getChcekDate() {
		return chcekDate;
	}
	public void setChcekDate(String chcekDate) {
		this.chcekDate = chcekDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getChcekTime() {
		return chcekTime;
	}
	public void setChcekTime(String chcekTime) {
		this.chcekTime = chcekTime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getAddr() {
		return addr;
	}
	public void setAddr(int addr) {
		this.addr = addr;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}


