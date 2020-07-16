package com.mailsender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class AttachmentHandler {
	
	private	Multipart multipart;
	private	MimeBodyPart textPart;
	private StringBuilder bodyText;
	
	public AttachmentHandler() {
		this.multipart = new MimeMultipart();

		this.textPart = new MimeBodyPart();
	}
	
	private void writeBody(String bodyPath) {
		File f = new File(bodyPath);
		String line =null;
		bodyText=new StringBuilder();
		
		try {
			Scanner in = new Scanner(f);
			while(in.hasNextLine()) {
				line=in.nextLine();
				bodyText.append(line + "\n");
				
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			textPart.setText(bodyText.toString());
			multipart.addBodyPart(textPart);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
	}
	
	private void attach(List<String> pathList) {
		for(String filePath : pathList) {
			try {
		        File f = new File(filePath);
		        System.out.println(filePath);
		        MimeBodyPart attachmentPart= new MimeBodyPart();
		        attachmentPart.attachFile(f);
		        multipart.addBodyPart(attachmentPart);
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    } catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void attachAllFromText(String attachFileText) {
		File f = new File(attachFileText);
		List<String> pathList = new ArrayList<String>();
		try {
			String line=null;
			Scanner in = new Scanner(f);
			while(in.hasNextLine()) {
				line=in.nextLine();
				pathList.add(line);
				System.out.println(line);
			}
			in.close();
			attach(pathList);
			for(String s: pathList) {
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public Multipart writeBodyAndAttachFiles(String bodyPath,String attachFileText) {
		writeBody( bodyPath);
		attachAllFromText(attachFileText);
		return multipart;
	}

}
