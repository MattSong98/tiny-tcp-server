package servertest;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Connector {

  private Socket socket;
  private OutputStream out;
  private InputStream in;
  private Scanner scanner;

  public Connector() {
    try {
      socket = new Socket(InetAddress.getLocalHost(), 9000);
      out = socket.getOutputStream();
      in = socket.getInputStream();
      scanner = new Scanner(in);
    } catch (Exception e) {
      System.out.println("Client: failed to build connection");
    }
  }

  public void send(String text) {
    try {
      out.write(text.getBytes());
      out.flush();
    } catch (Exception e) {
      System.out.println("Client: failed to send message");
    }
  }

  public String receive() {
    try {
      String message = scanner.nextLine();
      return message;
    } catch (Exception e) {
      System.out.println("Client: failed to receive message");
    }
    return null;
  }

  public void close() {
    try {
      socket.close();
      in.close();
      out.close();
    } catch (Exception e) {
      System.out.println("Client: failed to close connection");
    }
  }

}
