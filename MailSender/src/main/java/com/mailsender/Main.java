package com.mailsender;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@SuppressWarnings("unused")
public class Main {
	private static final String filePath = "credentials.txt" ,subject= "SUBJECT", bodyPath ="mailBody.txt" , attachFileText = "attachments.txt", toMails = "mails.txt";
	public static void main(String[] args) {
		
		Properties properties = PropertyBuilder.build();
		
		GetUserCredentials guc = new GetUserCredentials (filePath);
		final String fromMail = guc.getFromMail();
		final String password = guc.getPassword();
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromMail, password);
            }
        });
		
		
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromMail));
            // Set Subject: header field
            message.setSubject(subject);
            
            AttachmentHandler ah= new AttachmentHandler();
            Multipart multipart = ah.writeBodyAndAttachFiles(bodyPath, attachFileText);
            message.setContent(multipart);
            
            // Set To: header field of the header.
            MailListHandler.sendMails(toMails, message);
            
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
		

	}

}
