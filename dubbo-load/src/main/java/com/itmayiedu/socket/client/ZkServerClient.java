package com.itmayiedu.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.proto.GetChildren2Request;

public class ZkServerClient {
	public static List<String> listServer = new ArrayList<String>();
	public static String parentServicePath = "/service";

	public static void main(String[] args) {
		initServer();
		ZkServerClient client = new ZkServerClient();
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String name;
			try {
				name = console.readLine();
				if ("exit".equals(name)) {
					System.exit(0);
				}
				client.send(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static int requestCount = 1;

	// 注册所有server
	public static void initServer() {
		// listServer.clear();
		final ZkClient zkClient = new ZkClient("127.0.0.1:2181");
		List<String> children = zkClient.getChildren(parentServicePath);
		getChildren(zkClient, children);
		zkClient.subscribeChildChanges(parentServicePath, new IZkChildListener() {

			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				System.out.println("节点信息发生变化.....");
				getChildren(zkClient, currentChilds);
			}
		});
	}

	public static void getChildren(ZkClient zkClient, List<String> children) {
		listServer.clear();
		for (String p : children) {
			String pathValue = zkClient.readData(parentServicePath + "/" + p);
			listServer.add(pathValue);
		}
	}

	// 获取当前server信息
	public static String getServer() {
		int index = requestCount % listServer.size();
		String serverUrl = listServer.get(index);
		System.out.println("向服务器" + serverUrl + "发送请求.");
		requestCount++;
		return serverUrl;
	}

	public void send(String name) {

		String server = ZkServerClient.getServer();
		String[] cfg = server.split(":");

		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket(cfg[0], Integer.parseInt(cfg[1]));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			out.println(name);
			while (true) {
				String resp = in.readLine();
				if (resp == null)
					break;
				else if (resp.length() > 0) {
					System.out.println("Receive : " + resp);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
