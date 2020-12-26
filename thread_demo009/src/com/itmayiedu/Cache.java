package com.itmayiedu;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class Cache {
	private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	private static Map<Object, Object> map = new HashMap<>();
	private static ReadLock readLock = rwl.readLock();
	private static WriteLock writeLock = rwl.writeLock();

	public static Object get(String key) {

		try {
			readLock.lock();
			System.out.println("���������Ĳ���,key:" + key + "��ʼ..");
			Thread.sleep(50);
			Object object = map.get(key);
			System.out.println("���������Ĳ���,key:" + key + "����..");
			return object;
		} catch (Exception e) {
			return null;
		}finally {
			readLock.unlock();
		}
		
	
	}

	public static void put(String key, Object value) {
		try {
			writeLock.lock();
			System.out.println("������д�Ĳ���,key:" + key + ",value:" + value + "��ʼ..");
				Thread.sleep(50);
			map.put(key, value);
			System.out.println("������д�Ĳ���,key:" + key + ",value:" + value + "�Y��..");
		} catch (Exception e) {
			
		}finally {
			writeLock.unlock();
		}
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					Cache.put(i + "", "i:" + i);
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					Object object = Cache.get(i + "");
				}
			}
		}).start();
	}

}
