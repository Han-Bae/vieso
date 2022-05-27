package com.viseo.controller.kth.mail;

/**
 * 
 * @author	김태현
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.27	-	담당자 : 김태현
 * 								내	용 : 이메일 전송용 클래스
 */

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSend {
	// 회원가입 메일 전송
	public MailSend(String mail) {
		sendMail(mail);
	}
	// 아이디 확인 메일 전송
	public MailSend(String mail, char[] id) {
		sendMail(mail, id);
	}
	public void sendMail(String mail, char[] id) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("taehk1011@gmail.com", "mgphcsbdzbpnvgag");
			}
		});		
		System.out.println(id);
		String sid = new String(id);
		String receiver = mail; // 메일 받을 주소
		String title = "아이디 체크 메일입니다.";
		String content = "<h1>회원님의 아이디는 "+sid+"입니다.</h1>";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("taehk1011@gmail.com", "관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMail(String mail) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("taehk1011@gmail.com", "mgphcsbdzbpnvgag");
			}
		});
		
		String receiver = mail; // 메일 받을 주소
		String title = "회원 인증 메일입니다.";
		String content = "";//인증메일 내용
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("taehk1011@gmail.com", "관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}