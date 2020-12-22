package com.itmayiedu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkServerClient {
	// 获取所有的服务地址
	public static List<String> listServer = new ArrayList<String>();

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

	// 注册所有server
	public static void initServer() {
		listServer.clear();
		// 从zk获取最新获取的注册服务连接
		final String memberServerPath = "/member";
		final ZkClient zkClient = new ZkClient("127.0.0.1:2181", 6000, 1000);
		// 获当前下子节点
		List<String> children = zkClient.getChildren(memberServerPath);
		listServer.clear();
		for (String p : children) {
			// 读取子节点value值
			listServer.add((String) zkClient.readData(memberServerPath + "/" + p));
		}
		System.out.println("最新服务信息listServer:" + listServer.toString());
		// 订阅子节点事件
		zkClient.subscribeChildChanges(memberServerPath, new IZkChildListener() {
			// 子节点发生变化
			public void handleChildChange(String parentPath, List<String> childrens) throws Exception {
				listServer.clear();
				for (String subP : childrens) {
					// 读取子节点value值
					listServer.add((String) zkClient.readData(memberServerPath + "/" + subP));
				}
			}
		});
	}

	// 服务调用次数
	private static int count = 1;
	// 会员服务集群数量，实际开发中不要写死，
	private static int memberServerCount = 2;

	// 获取当前server信息
	public static String getServer() {
		String serverName = listServer.get(count % memberServerCount);
		++count;
		return serverName;
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
