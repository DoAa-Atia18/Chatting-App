import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.ArrayList;
import javax.swing.*;

public class ChatClient extends JFrame {

  Client client;
  JTextArea textArea;
  JScrollPane scroll;
  JTextField textField;
  JButton okButton;

  public ChatClient() {
    this.setLayout(new FlowLayout());
    textArea = new JTextArea(20, 50);
    scroll = new JScrollPane(textArea);
    textField = new JTextField(40);
    okButton = new JButton("Send");

    client = new Client("127.0.0.1", 4433);

    okButton.addActionListener(
      (ActionEvent e) -> {
        String msg = textField.getText();
        //textArea.append(msg + "\n");
        //textField.setText("");
        client.sendMsg(msg);
      }
    );
    add(scroll);
    add(textField);
    add(okButton);
  }

  public static void main(String[] args) {
    ChatClient ui = new ChatClient();
    ui.setSize(600, 400);
    ui.setResizable(false);
    ui.setVisible(true);
  }

  /* Client Thread  Class*/
  public class Client extends Thread {

    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;

    public Client(String ip, int port) {
      try {
        mySocket = new Socket(ip, port);
        dis = new DataInputStream(mySocket.getInputStream());
        ps = new PrintStream(mySocket.getOutputStream());
        start();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      // try {
      //   ps.close();
      //   dis.close();
      //   mySocket.close();
      // } catch (Exception e) {
      //   e.printStackTrace();
      // }
    }

    public void sendMsg(String msg) {
      try {
        ps.println(msg);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public void run() {
      while (true) {
        try {
          String receivedMsg = dis.readLine();
          textArea.append(receivedMsg + "\n");
          System.out.println(receivedMsg);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
