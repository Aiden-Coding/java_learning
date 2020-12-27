package com.itmayiedu.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.I0Itec.zkclient.ZkClient;

//##ServerScoekt服务端
public class ZkServerScoekt implements Runnable {
	private static int port = 18081;

	public static void main(String[] args) throws IOException {
		ZkServerScoekt server = new ZkServerScoekt(port);
		Thread thread = new Thread(server);
		thread.start();
	}

	public ZkServerScoekt(int port) {
		this.port = port;
	}

	public void regServer() {
		ZkClient zkClient = new ZkClient("127.0.0.1:2181");
		String parentServicePath = "/service";
		if (!zkClient.exists(parentServicePath)) {
			zkClient.createPersistent(parentServicePath);
		}
		String serverPortPath = parentServicePath + "/server-" + port;
		if (zkClient.exists(serverPortPath)) {
			zkClient.delete(serverPortPath);
		}
		zkClient.createEphemeral(serverPortPath, "127.0.0.1:" + port);

	}

	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			regServer();
			System.out.println("Server start port:" + port);
			Socket socket = null;
			while (true) {
				socket = serverSocket.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (Exception e2) {

			}
		}
	}

}
