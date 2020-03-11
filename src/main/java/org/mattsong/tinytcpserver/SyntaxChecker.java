package org.mattsong.tinytcpserver;

public class SyntaxChecker {

  public static boolean isValid(String message) {	
		String[] tokens = message.split("\\\\");
		if (tokens.length != 3)
			return false;
		if (!(tokens[0].equals(Service.SIGN_UP) || tokens[0].equals(Service.CONTENT_REQUEST)))
			return false;
		if (tokens[1].length() > 20 || tokens[1].length() < 3) 
	    return false;
		if (tokens[2].length() > 20 || tokens[2].length() < 3) 
	    return false;
		return true;
  }

}
