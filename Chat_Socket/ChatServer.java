import java.io.*;
import java.net.*;
import java.util.*;
import java.util.ArrayList;

public class ChatServer {

  ServerSocket myServerSocket;

  public static void main(String[] args) {
    new ChatServer();
  }

  public ChatServer() {
    try {
      myServerSocket = new ServerSocket(4433);
      while (true) {
        Socket socket = myServerSocket.accept();
        new ChatHandler(socket);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
