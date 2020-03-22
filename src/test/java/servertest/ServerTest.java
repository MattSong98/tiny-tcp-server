package servertest;

import java.util.Scanner;

public class ServerTest {
  public static void main(String[] args) {
    Connector connector = new Connector();
    Scanner scanner = new Scanner(System.in);
    String message = scanner.nextLine() + "\n";
    connector.send(message);
    String respondMessage = connector.receive();
    System.out.println(respondMessage);
  }
}
