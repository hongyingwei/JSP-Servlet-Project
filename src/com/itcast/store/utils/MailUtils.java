package com.itcast.store.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		

		Properties props = new Properties();
		
		//props.setProperty("mail.transport.protocol", "SMTP");
		
		
		//props.setProperty("mail.host", "smtp.126.com");
		//props.setProperty("mail.smtp.auth", "true");// 鎸囧畾楠岃瘉涓簍rue

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//发送者的账号和密码
				return new PasswordAuthentication("1773261858@qq.com", "15874473705hyw");
			}
		};

		Session session = Session.getInstance(props, auth);

		
		Message message = new MimeMessage(session);

		
		message.setFrom(new InternetAddress("1773261858@qq.com"));

		
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		String content="<h1>来自微雨电商的激活码："+emailMsg+"</h1>";
		message.setContent(content, "text/html;charset=utf-8");
		Transport.send(message);
	}
}
