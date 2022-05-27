package com.viseo.vo;

import java.util.Date;
import java.sql.Time;
import java.text.*;

public class UpdateVO {
	private int mno;
	private String name, id, pw, mail, addr, gen, nickname, tel, birth;
	private Date joindate;
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
	@Override
	public String toString() {
		return "FileVO [mno=" + mno + ", name=" + name + ", id=" + id + ", pw=" + pw + ", mail=" + mail + ", addr="
				+ addr + ", gen=" + gen + ", nickname=" + nickname + ", tel=" + tel + ", birth=" + birth + ", joindate="
				+ joindate + "]";
	}
}
