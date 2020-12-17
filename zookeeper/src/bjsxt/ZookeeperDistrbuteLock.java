
package bjsxt;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {
	private CountDownLatch countDownLatch=null;

	@Override
	public boolean tryLock() {
		try {
			zkClient.createEphemeral(PATH);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void waitLock() {
		IZkDataListener iZkDataListener=	new IZkDataListener() {

			public void handleDataDeleted(String dataPath) throws Exception {
				if(countDownLatch!=null)
				countDownLatch.countDown();
			}

			public void handleDataChange(String dataPath, Object data) throws Exception {

			}
		};
		if (zkClient.exists(PATH)) {
			try {
				countDownLatch = new CountDownLatch(1);
				countDownLatch.wait();
			} catch (Exception e) {
			}
		}
		zkClient.unsubscribeDataChanges(PATH, iZkDataListener);

	}

}
