import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu.entity.UserEntity;
import com.itmayiedu02.service.UserService;

public class SpringTest001 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("db.xml");
		UserService userService = (UserService) app.getBean("");
       
	   //封装xmk解析springbean文件
		

	}

}
