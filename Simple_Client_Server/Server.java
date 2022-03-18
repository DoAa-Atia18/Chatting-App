import java.io.*;
import java.net.*;

public class Server {

  ServerSocket myServerSocket;
  Socket mySocket;
  DataInputStream dis;
  PrintStream ps;

  public static void main(String[] args) {
    new Server();
  }

  public Server() {
    try {
      myServerSocket = new ServerSocket(4433);
      mySocket = myServerSocket.accept();
      dis = new DataInputStream(mySocket.getInputStream());
      ps = new PrintStream(mySocket.getOutputStream());
      String msg = dis.readLine();
      System.out.println(msg);
      ps.println("Received");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    try {
      ps.close();
      dis.close();
      mySocket.close();
      myServerSocket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
