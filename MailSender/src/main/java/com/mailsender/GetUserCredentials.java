package com.mailsender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class  GetUserCredentials {
	
	private final String fromMail;
	private final String password;
	
	public GetUserCredentials (String filePath) {
		File userInfo = new File(filePath);
		String from=null,pass=null;
		try {
			Scanner in = new Scanner(userInfo);
			from = in.nextLine();
			pass = in.nextLine();
			in.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			from = null;
			pass = null;
			e.printStackTrace();
		}
		finally {
			fromMail=from;
			password=pass;
		}
		
	}

	public String getFromMail() {
		return fromMail;
	}

	public String getPassword() {
		return password;
	}
	
	

}
