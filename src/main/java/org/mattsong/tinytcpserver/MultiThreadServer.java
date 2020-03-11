package org.mattsong.tinytcpserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class MultiThreadServer implements Runnable {    
  private int          serverPort    = 8080;
  private ServerSocket serverSocket  = null;
  private boolean      isStopped     = false;
  private Thread       runningThread = null;

  MultiThreadServer(int serverPort) {
		this.serverPort = serverPort;
  }
    
  public void run() {
		synchronized(this) {
	    runningThread = Thread.currentThread();
		}

		openServerSocket();	
		while (!isStopped) {
	    Socket clientSocket = null;
	    try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				throw new RuntimeException("Error accepting client connection", e);
	    }
	    new Thread(new WorkerRunnable(clientSocket)).start();
		}
		closeServerSocket();
		System.out.println("Server stopped");
  }

  public synchronized void stop() {
		isStopped = true;
  }

  private void openServerSocket() {
		try {
	    serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
	    throw new RuntimeException("Cannot open Port " + serverPort, e);
		}
  }

  private void closeServerSocket() {
		try {
	    serverSocket.close();
		} catch (IOException e) {
	    throw new RuntimeException("Cannot close ServerSocket", e);
		}
  }

}
