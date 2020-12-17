
package bjsxt;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock implements Lock {
	protected static final String CONNECT_ADDRES = "192.168.110.159:2181,192.168.110.160:2181,192.168.110.162:2181";
	protected static final int SESSIONTIME = 2000;
	protected static final String PATH = "/lock";
	protected ZkClient zkClient = new ZkClient(CONNECT_ADDRES);

	public void getLock() {
		if (tryLock()) {
			System.out.println(Thread.currentThread().getName() + "-- get lock");
		} else {
			waitLock();
			getLock();
		}
	}

	public abstract boolean tryLock();

	public abstract void waitLock();

	public void unLock() {
		try {
			if (zkClient != null)
				zkClient.close();
		} catch (Exception e) {

		}
	}

}
