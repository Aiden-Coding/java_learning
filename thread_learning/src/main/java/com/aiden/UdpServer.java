package com.aiden;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
  public static void main(String[] args) throws IOException {
    System.out.println("udp接受数据启动.......");
    DatagramSocket ds = new DatagramSocket(8080);
    byte[] buf = new byte[1024];
    DatagramPacket dp = new DatagramPacket(buf, buf.length);
    // 阻塞效果
    ds.receive(dp);
    System.out.println("来源:"+dp.getAddress().getHostAddress()+",port:"+dp.getPort());
    String str = new String(dp.getData(),0,dp.getLength());
    System.out.println("客户端发送数据:"+str);
    ds.close();

  }
}
