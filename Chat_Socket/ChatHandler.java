import java.io.*;
import java.net.*;
import java.util.*;
import java.util.ArrayList;

class ChatHandler extends Thread {

  DataInputStream dis;
  PrintStream ps;
  String clientID;

  static List<ChatHandler> clientsList = new ArrayList<ChatHandler>();
  static int id = 0;

  public ChatHandler(Socket clientSocket) {
    try {
      dis = new DataInputStream(clientSocket.getInputStream());
      ps = new PrintStream(clientSocket.getOutputStream());
      clientID = String.valueOf(++id);

      clientsList.add(this);
      start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    while (true) {
      try {
        String clientMsg = this.clientID + " : " + dis.readLine();
        if (clientMsg != null) {
          System.out.println(clientMsg);
          sendMessageToAll(clientMsg);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  void sendMessageToAll(String msg) {
    for (ChatHandler clientHandler : clientsList) {
      clientHandler.ps.println(msg);
    }
  }
}
