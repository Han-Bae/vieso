package com.viseo.controller.kth;

/**
 * 로그인 관련 기능을 처리할 때 사용한 VO 클래스
 * @author 김태현
 * @since 2022.05.24
 * @version v.1.0
 * 					담당자 : 김태현
 */
public class MemberVO {
	private int mno, addr;
	private String name, id, pw, mail, gen, nickname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getAddr() {
		return addr;
	}
	public void setAddr(int addr) {
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", addr=" + addr + ", name=" + name + ", pw=" + pw + ", mail=" + mail + ", gen="
				+ gen + ", nickname=" + nickname + "]";
	}
	
	
}
