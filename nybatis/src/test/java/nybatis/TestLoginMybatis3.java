package nybatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.itmayiedu.entity.UserTable;

public class TestLoginMybatis3 {

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

		String resource = "mybatis.xml";
		// 读取配置文件
		Reader reader = Resources.getResourceAsReader(resource);
		// 获取会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession openSession = sqlSessionFactory.openSession();
		// 查询
		String sql = "com.itmayiedu.mapper.UserTableMapper.login";
		// 调用api查询
		UserTable userTable = new UserTable();
		userTable.setUserName("''  OR 1=1 -- ");
		userTable.setPassWord("12345");
		List<UserTable> listUserTable = openSession.selectList(sql, userTable);
		for (UserTable ub : listUserTable) {
			System.out.println(ub.getUserName());
		}
	}
}
