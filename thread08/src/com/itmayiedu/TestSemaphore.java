package com.itmayiedu;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author qifuguang
 * @date 15/8/25 23:23
 */
public class TestSemaphore {
    public static void main(String[] args) throws Exception {
    	
    
    	
        Semaphore wc = new Semaphore(3, true); // 3����λ
        for (int i = 1; i <= 6; i++) {
            Thread t = new Thread(new Person("��" + i + "����", wc));
            t.start();
            Thread.sleep(new Random().nextInt(300));
        }
    }

    static class Person implements Runnable {
        private String name;
        private Semaphore wc;

        public Person(String name, Semaphore wc) {
            this.name = name;
            this.wc = wc;
        }

        public void run() {
            System.out.print(name + "������������!");
            if (wc.availablePermits() > 0) {
                System.out.println("������Ҳ���п�λ��");
            } else {
                System.out.println("�Բۣ�û��λ�ˣ��Ȼ����...");
            }
            try {
                wc.acquire(); //�����λ
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "�������ֵ����ˣ���ʺ����ˬ��");
            try {
                Thread.sleep(new Random().nextInt(1000)); // ģ���ϲ���ʱ�䡣
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "�������ˣ��ó�!");
            wc.release();
        }
    }
}