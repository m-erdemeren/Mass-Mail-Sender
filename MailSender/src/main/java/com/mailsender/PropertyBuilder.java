package com.mailsender;

import java.util.Properties;

public class PropertyBuilder {
	
	private static final String host = "smtp.gmail.com";
	
	public static Properties build() {
		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", 465);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		return properties;
	}
}
