/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package zk_day_64_;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月14日 下午9:18:40<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
	private ZkClient zkClient = new ZkClient("127.0.0.1:2181");
	private String path = "/election";
	@Value("${server.port}")
	private String serverPort;

	// 启动后执行方法
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("项目启动成功...");
		// 1.项目启动的时候会在zk上创建一个相同的临时节点
		// 2.谁能够创建成功谁就是为主服务器
		createEphemeral();
		// 3.使用服务监听节点是否被删除，如果接受到节点被删除的话，重新开始选举（重新开始创建节点）
		zkClient.subscribeDataChanges(path, new IZkDataListener() {
			// 节点如果被删除后 ，返回通知
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("开始重新选举策略。。。");
				createEphemeral();
			}

			/// 当节点值发生变化的之后，事件通知
			public void handleDataChange(String dataPath, Object data) throws Exception {

			}
		});
	}

	private void createEphemeral() {
		try {
			zkClient.createEphemeral(path);
			System.out.println("serverPort:" + serverPort + "，选举成功....");
			ElectionMaster.isSurvival = true;
		} catch (Exception e) {
			System.out.println("该节点已经存在");
			ElectionMaster.isSurvival = false;
		}
	}

}
