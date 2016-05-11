package threadDemo;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// final CyclicBarrier cb = new
		// CyclicBarrier(3);//����CyclicBarrier��������3���������ϵ�
		final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
			@Override
			public void run() {
				System.out.println("********������ִ��***********");
			}
		});
		for (int i = 0; i < 9; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�" + Thread.currentThread().getName() + "�������Ｏ�ϵص�1����ǰ����"
								+ cb.getNumberWaiting() + "���Ѿ�������ڵȺ�");
						cb.await();// �������û�дﵽ�������ϵ㣬����̴߳��ڵȴ�״̬������ﵽ�������ϵ������д��ڵȴ����̶߳�������������

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�" + Thread.currentThread().getName() + "�������Ｏ�ϵص�2����ǰ����"
								+ cb.getNumberWaiting() + "���Ѿ�������ڵȺ�");
						cb.await(); // ����CyclicBarrier�����ֿ�������
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�" + Thread.currentThread().getName() + "�������Ｏ�ϵص�3����ǰ����"
								+ cb.getNumberWaiting() + "���Ѿ�������ڵȺ�");
						cb.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}

}