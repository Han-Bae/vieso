package com.viseo.vo;

import java.text.*;
import java.util.Date;

/**
 * 
 * @author  정유나
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 정유나
 * 								내	용 : 회원가입 VO
 *
 */


public class JoinVO {
   private int mno, addr, cnt;
   private String name, id, pw, mail, city, gen, nickname, tel, birth, jdate;
   private Date joindate;
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
   public int getCnt() {
	   return cnt;
   }
   public void setCnt(int cnt) {
	   this.cnt = cnt;
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
   public String getCity() {
	return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setJdate(String jdate) {
		this.jdate = jdate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
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
   
   public String getBirth() {
	   return birth;
   }
   public void setBirth(String birth) {
	   this.birth = birth;
   }
   
   public String getJdate() {
      return jdate;
   }
   public void setJdate() {
	  SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd ");
	  jdate = form.format(joindate);
   }
   public void getJdate(String jdate) {
	   this.jdate = jdate;
   }
	public Date getJoindate() {
		return joindate;
	}
	public void setJdate(Date joindate) {
		this.joindate = joindate;
	}
   
   @Override
   public String toString() {
      return "JoinVO [mno=" + mno + ",  addr=" + addr + ",cnt=" + cnt + ", name=" + name + ", id=" + id + ", pw=" + pw + ", mail="
    		  + mail + ", gen=" + gen + ", nickname=" + nickname + ", tel=" + tel + ", birth=" + birth + ", joindate=" + joindate + ", jdate=\" + jdate + \"]";
   }
 
	public String getJson() {
		StringBuffer buff = new StringBuffer();
		buff.append("{\r\n");
		buff.append("\"mno\": \"" + mno + "\",\r\n");
		buff.append("\"addr\": \"" + addr + "\",\r\n");
		buff.append("\"name\": \"" + name + "\",\r\n");
		buff.append("\"id\": \"" + id + "\",\r\n");
		buff.append("\"pw\": \"" + pw + "\",\r\n");
		buff.append("\"mail\": \"" + mail + "\",\r\n");
		buff.append("\"gen\": \"" + (gen.equals("M")?"남자":"여자") + "\",\r\n");
		buff.append("\"nickname\": \"" + nickname + "\",\r\n");
		buff.append("\"tel\": \"" + tel + "\",\r\n");
		buff.append("\"birth\": \"" + birth + "\",\r\n");
		buff.append("\"jdate\": \"" + jdate + "\"\r\n");
		buff.append("\"joindate\": \"" + joindate + "\"\r\n");
		buff.append("}");
			return buff.toString();
	}
}