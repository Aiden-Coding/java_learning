package thread_demo01;

/**
 * 
 * @classDesc: ��������:()
 * @author: ��ʤ��
 * @createTime: 2017��12��1�� ����6:20:36
 * @version: v1.0
 * @copyright:���Ͽ���(ÿ��ѧԺ)�Ϻ�ÿ�ؽ����Ƽ����޹�˾��Ʒ
 * @website:www.itmayiedu.com����www.meiteedu.com
 * @weixin:�ٷ�΢�ź� ÿ��ѧԺ
 * @QQ:644064779 QQȺ:116295598
 */
class CreateThread01 extends Thread {
	// 1.�̳�Thread�� ��дrun����
	// 2.ʵ��runlabel�ӿ� ��дrun����
	// 3.ʹ�������ڲ���ķ�ʽ

	@Override
	public void run() {
		// ������߳���Ҫִ�е�����
		System.out.println("���߳̿�ʼ����....");
		for (int i = 0; i < 30; i++) {
			System.out.println("run i:" + i);
		}
	}

}

public class ThreadDemo01 {

	public static void main(String[] args) {
		// ʲô���̣߳� ��ʵ���ǳ����� ִ�е�һ��·����
		// һ�������У�һ��main���߳� GC�߳������ػ��߳�
		// ʲ�N���ػ��̣߳����̻߳��߽���ֹͣ ���߳̾ͻ�ֹͣ��
		// �û��߳� �û��Զ�����߳�--------���������߳�ֹͣ���û��̲߳�����Ӱ�졣
		System.out.println("main��������...");
		CreateThread01 createThread01 = new CreateThread01();
		// �����̵߳���start(),
		createThread01.start();
		System.out.println("���߳̿�ʼ����....");
		for (int i = 0; i <5; i++) {
			System.out.println("main i=" + i);

		}
		System.out.println("���߳�ִ�����....");
		// �������̣߳�Ϊ�����̲߳���ִ�С� 
	}

}
