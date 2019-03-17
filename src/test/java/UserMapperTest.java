import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;

public class UserMapperTest {
	@Test
	public void testInsert() {
		//1.获取spring容器对象
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		//2.获取bean对象
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		//3.创建User对象,调用userMapper的insert(User)方法
		User user = new User("admin","123456",1,"13500505445","943909831@qq.com");
		userMapper.insert(user);
		//4.关闭容器
		ac.close();
	}
	@Test
	public void testSelect() {
		//1.获取spring容器对象
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext("spring-dao.xml");
		//2.获取bean对象
		UserMapper mapper = 
				ac.getBean("userMapper", UserMapper.class);
		//3.创建User对象,调用userMapper的select(....)方法
		String where = "id=1";
		String orderBy = null;
		Integer offset = null;
		Integer countPerPage = null;
		List<User> users 
			= mapper.select(where, orderBy, offset, countPerPage);
		
		System.out.println(users.size());
		for(User u : users) {
			System.out.println(u);
		}
		//4.关闭容器
		ac.close();
	}
	@Test
	public void testUpdate() {
		//1.获取spring容器对象
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		//2.获取bean对象
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		//3.创建User对象,调用userMapper的update(User)方法
		User user = new User(1,"yaya","yaya520",0,"13500505445","13500505445@163.com");
		user.setModifiedUser("System");
		Date now = new Date();
		user.setModifiedTime(now);
		Integer rows = userMapper.update(user);
		System.out.println("rows:"+rows);
		//4.关闭容器
		ac.close();
	}
}
