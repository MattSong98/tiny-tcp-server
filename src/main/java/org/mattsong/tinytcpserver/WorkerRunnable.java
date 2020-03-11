package org.mattsong.tinytcpserver;

import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class WorkerRunnable implements Runnable {
    
	private Socket socket = null;
  private InputStream in = null;
  private OutputStream out = null;
  private Scanner sc = null;
  private boolean ENABLE = true;
  private final int timeout = 1000 * 30;
    
  WorkerRunnable(Socket socket) {
		try {
	    this.socket = socket;
	    in = socket.getInputStream();
	    out = socket.getOutputStream();
	    sc = new Scanner(in);
	    socket.setSoTimeout(timeout);
		} catch (Exception e) {
			ENABLE = false;
	    close();
		}
  }
	
  public void run() {
		if (!ENABLE) return;
	
		String request = null;
		String reply = null;

		// receive request from client
		// and close socket (thread) if time out.
		try {
	    request = sc.nextLine();
		} catch (Exception e) {
	    close();
	    return;
		}

		reply = Service.process(request);

		// send reply to client
		// and close socket (thread) if failed.
		try {
	    out.write((reply + '\n').getBytes());
	    out.flush();
		} catch (Exception e) {
	    Service.patch(reply);
		} finally {
	    close();
		}
  }

  private void close() {
		// socket must be correctly closed
		try {
	    in.close();
	    out.close();
	    socket.close();
		} catch (IOException e) {
	    throw new RuntimeException("Error closing socket", e);
		}
  }

}
