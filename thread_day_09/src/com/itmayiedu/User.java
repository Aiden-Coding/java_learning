package com.itmayiedu;

//使用枚举实现单例模式 优点:实现简单、枚举本身就是单例，由jvm从根本上提供保障!避免通过反射和反序列化的漏洞 缺点没有延迟加载
public class User {
	public static User getInstance() {
		return SingletonDemo04.INSTANCE.getInstance();
	}

	private static enum SingletonDemo04 {
		INSTANCE;
		// 枚举元素为单例
		private User user;

		private SingletonDemo04() {
			System.out.println("SingletonDemo04");
			user = new User();
		}

		public User getInstance() {
			return user;
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		User u1 = User.getInstance();
		User u2 = User.getInstance();
		System.out.println(u1 == u2);
		
	}
}
