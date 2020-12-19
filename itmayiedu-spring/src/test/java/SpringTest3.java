import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu.entity.UserEntity;
import com.itmayiedu02.service.UserService;

public class SpringTest3 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("spring003.xml");
		UserService userService = (UserService) app.getBean("userService");
		userService.add02();
	}

}
