package com.itcast.store.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailUtils2 {
	public static void sendMail(String eamil, String resultCode) {
		HtmlEmail send = new HtmlEmail();
		try {
		send.setHostName("smtp.qq.com"); 
		send.setSmtpPort(465);//端口号
		send.setSSLOnConnect(true);  //开启SSL加密
		send.setCharset("utf-8");
		send.addTo(eamil); //接收者的QQEamil
		send.setFrom("1773261858@qq.com", "御风（洪迎伟）");//第一个参数是发送者的QQEamil   第二个参数是发送者QQ昵称
		send.setAuthentication("1773261858@qq.com", "xajlkklycruvbiaf"); //第一个参数是发送者的QQEamil   第二个参数是刚刚获取的授权码
		send.setSubject("小渣渣特给您送上验证码");//Eamil的标题 
		send.setMsg("HelloWorld!欢迎大大光临，特此送上验证:   " + resultCode + "   请大大签收");//Eamil的内容
		send.send();//发送
		} catch (EmailException e) {
		e.printStackTrace();
		}
	}
}
