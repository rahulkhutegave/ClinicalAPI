package com.clinicallab.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

	//Switch on less secure app feature in gmail
	public static void sendEmail(String message, String subject, String to, String from) throws Exception {

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("FromEmail@gmail.com", "password**");
			}
		});

		MimeMessage	msg=new MimeMessage(session);
		msg.setFrom(from);
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
//		msg.setDescription(message);
		msg.setText(message);
		
		Transport.send(msg);
		
	}

}
