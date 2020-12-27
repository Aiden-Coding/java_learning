import com.itmayeidu.proxy.UserServiceProxy;
import com.itmayiedu.service.UserService;
import com.itmayiedu.service.impl.UserServiceImpl;

public class Test001 {

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		// userService.add();
		UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
		userServiceProxy.add();
	}

}
