package org.mattsong.tinytcpserver;

import java.util.Scanner;

public class Boot {
        
  public static void main(String[] args) {
    MultiThreadServer server = new MultiThreadServer(9000);
    new Thread(server).start();
  
    Scanner sc = new Scanner(System.in);
    System.out.print("> ");
    while (!sc.nextLine().equals("exit")) {
      System.out.println("Invalid Syntax");
      System.out.print("> ");
    }
  
    server.stop();
  }
}
      
  
  
