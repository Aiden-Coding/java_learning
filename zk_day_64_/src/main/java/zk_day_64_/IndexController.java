/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package zk_day_64_;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月14日 下午9:17:16<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class IndexController {
	@Value("${server.port}")
	private String serverPort;

	@RequestMapping("/getServerInfo")
	public String getServerInfo() {
		return "serverPort:" + serverPort + (ElectionMaster.isSurvival ? "选举为主服务器" : "该服务器为从服务器");
	}

	public static void main(String[] args) {
		// 1.项目启动的时候会在zk上创建一个相同的临时节点
		// 2.谁能够创建成功谁就是为主服务器
		// 3.使用服务监听节点是否被删除，如果接受到节点被删除的话，重新开始选举（重新开始创建节点）

	}

}
