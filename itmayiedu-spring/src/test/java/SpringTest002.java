import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu.entity.UserEntity;

public class SpringTest002 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("spring2.xml");
		UserEntity user = (UserEntity) app.getBean("userEntity01");
		//销毁bean容器
		app.destroy();
	}
}
