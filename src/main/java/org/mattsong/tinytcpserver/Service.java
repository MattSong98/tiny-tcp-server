package org.mattsong.tinytcpserver;

public class Service {
  // service code 
  static final String SIGN_UP = "0000";
  static final String CONTENT_REQUEST = "0001";
  static final String SYNTAX_ERROR = "0002";

  public static String process(String message) {  
    if (!SyntaxChecker.isValid(message)) return SYNTAX_ERROR; 
    String respondMessage = null;
    String code = message.substring(0, 4);
    String[] tokens = message.split("\\\\");
    
    switch (code) {
      case SIGN_UP:
      {
        String name = tokens[1];
        String password = tokens[2];
        String account = User.signUp(name, password);
        if (account == null) respondMessage = SIGN_UP + '\\' + "0";
        else respondMessage = SIGN_UP + '\\' + "1" + '\\' + account;
        break;
      }
      case CONTENT_REQUEST:
      {
        String account = tokens[1];
        String password = tokens[2];
        boolean isValid = User.validation(account, password);
        if (isValid) {
          String content = Content.getContent();
          respondMessage = CONTENT_REQUEST + '\\' + "1" + '\\' + content;
        } else respondMessage = CONTENT_REQUEST + '\\' + "0";
        break;
      }
    }
    return respondMessage;
  }

  public static void patch(String message) {
    // case SIGN_UP
    String code = message.substring(0, 4);
    if (code.equals(SIGN_UP)) {
      String[] tokens = message.split("\\\\");
      if (tokens[1].equals("1")) {
        String account = tokens[2];
        User.delete(account);
      }
    }
  }

} 
