import java.io.*;
import java.net.*;

public class Client {

  Socket mySocket;
  DataInputStream dis;
  PrintStream ps;

  public static void main(String[] args) {
    new Client();
  }

  public Client() {
    try {
      mySocket = new Socket("127.0.0.1", 4433);
      dis = new DataInputStream(mySocket.getInputStream());
      ps = new PrintStream(mySocket.getOutputStream());
      ps.println("Message from Client");
      String replyMsg = dis.readLine();
      System.out.println(replyMsg);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    try {
      ps.close();
      dis.close();
      mySocket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
