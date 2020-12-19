import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu.entity.UserEntity;
import com.itmayiedu02.service.UserService;

public class SpringTest001 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("spring.xml", "transaction2.xml");
		// UserEntity userEntity01 = (UserEntity) app.getBean("userEntity01");
		// UserEntity userEntity02 = (UserEntity) app.getBean("userEntity01");
		// userEntity01.setAge(20);
		// userEntity01.setUserName("yushengjun");
		// System.out.println(userEntity01.toString());
		// System.out.println(userEntity01 == userEntity02);
		// UserEntity userEntity=(UserEntity) app.getBean("userEntity02");
		// System.out.println(userEntity.toString());
		// UserService userService = (UserService) app.getBean("userSerivce");
		// userService.add();
		UserService userService = (UserService) app.getBean("userService");
		userService.add();
	}

}
