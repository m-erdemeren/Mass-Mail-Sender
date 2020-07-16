package com.mailsender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailListHandler {
	public static void sendMails(String toMails,MimeMessage message) {
		String toMail;
		File f = new File(toMails);
		Scanner in;
		try {
			in = new Scanner(f);
			while(in.hasNextLine()) {
				toMail = in.nextLine();
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
		        System.out.println("sending...");
		        // Send message
		        Transport.send(message);
		        System.out.println("Sent message successfully....");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
        
	}

}
