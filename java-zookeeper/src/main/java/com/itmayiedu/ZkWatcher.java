
package com.itmayiedu;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @classDesc: 功能描述:(zk watcher 用法)
 * @author: 余胜军
 * @createTime: 2017年10月14日 下午4:12:54
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 * @QQ:644064779
 */
public class ZkWatcher implements Watcher {
  private static final String CONNECT_ADDRES = "192.168.110.159:2181,192.168.110.160:2181,192.168.110.162:2181";
  private static final int SESSIONTIME = 2000;
  private static final CountDownLatch countDownLatch = new CountDownLatch(1);
  private ZooKeeper zooKeeper = null;

  public void process(WatchedEvent watchedEvent) {
    // 获取事件状态
    KeeperState keeperState = watchedEvent.getState();
    // 获取事件类型
    EventType eventType = watchedEvent.getType();
    // 获取路径
    String path = watchedEvent.getPath();
    System.out.println("###process()####Watcher事件监听#####keeperState:" + keeperState + "###eventType:" + eventType
      + "###path:" + path);
    // 判断是否建立连接
    if (KeeperState.SyncConnected == keeperState) {
      // 如果时间为连接状态
      if (EventType.None == eventType) {
        System.out.println("zk 连接成功!");
      }
      // 如果当前节点为创建状态
      if (EventType.NodeCreated == eventType) {
        System.out.println("事件通知,新增节点:" + path);
      }
      // 如果当前节点修改状态
      if (EventType.NodeDataChanged == eventType) {
        System.out.println("事件通知,修改节点:" + path);
      }
    }
    System.out.println(
      "##############################################################################################");
  }

  public void createConnection(String connectAddres, int sessionTimeOut) {
    try {
      zooKeeper = new ZooKeeper(CONNECT_ADDRES, sessionTimeOut, this);
      System.out.println("zk 开始启动服务连接...");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void createNode(String path, String data) {
    try {
      // zooKeeper.exists(path, true);
      String result = zooKeeper.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
      System.out.println("createNode()节点创建成功..." + result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateNode(String path, String data) {
    try {
      zooKeeper.exists(path, true);
      zooKeeper.setData(path, data.getBytes(), -1);
      System.out.println("updateNode()节点修改成功...");
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public static void main(String[] args) {
    ZkWatcher zkWatcher = new ZkWatcher();
    zkWatcher.createConnection(CONNECT_ADDRES, SESSIONTIME);
    zkWatcher.updateNode("/p22", "644064");
  }

}
