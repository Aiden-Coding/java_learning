package com.aiden;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
  public static void main(String[] args) throws IOException {
    System.out.println("socket启动....");
    Socket s = new Socket("127.0.0.1", 8080);
    OutputStream outputStream = s.getOutputStream();
    outputStream.write("我是客戶端....".getBytes());
    s.close();
  }
}
