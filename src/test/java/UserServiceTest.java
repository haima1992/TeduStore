import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;

public class UserServiceTest {

	@Test
	public void testReg() {
		//1.获取spring容器对象
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		//2.获取bean对象
		IUserService  userService = ac.getBean("userService",IUserService.class);
		//3.创建User对象,调用userService的reg(User)方法
		User user = new User();
		user.setUsername("ABC");
		user.setPassword("123456");
		userService.reg(user);
		//4.关闭容器
		ac.close();
	}
	@Test
	public void testFindUserById() {
		//1.获取spring容器对象
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		//2.获取bean对象
		IUserService  userService = ac.getBean("userService",IUserService.class);
		//3.调用userService的findUserById()方法
		User user = userService.findUserById(1);
		System.out.println("user:"+user);
		//4.关闭容器
		ac.close();
	}
	@Test
	public void testFindUserByUsername() {
		//1.获取spring容器对象
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		//2.获取bean对象
		IUserService  userService = ac.getBean("userService",IUserService.class);
		//3.指定username,调用userService的findUserByUsername()方法
		User user = userService.findUserByUsername("YY");
		System.out.println("user:"+user);
		//4.关闭容器
		ac.close();
	}
	@Test
	public void testLogin() {
		//1.获取spring容器对象
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		//2.获取bean对象
		IUserService  userService = ac.getBean("userService",IUserService.class);
		//3.userService调用login()方法
		User user = userService.login("ABC","123456");
		System.out.println("user:"+user);
		//4.关闭容器
		ac.close();
	}
	@Test
	public void testChangePassword() {
		//1.获取spring容器对象
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		//2.获取bean对象
		IUserService  userService = ac.getBean("userService",IUserService.class);
		//3.userService调用changePassword()方法
		Integer id = 7;
		String oldPassword = "123456";
		String newPassword = "abcdef";
		Integer rows = userService.changePassword(id, oldPassword, newPassword);
		System.out.println("rows:"+rows);
		//4.关闭容器
		ac.close();
	}
	@Test
	public void testChangeInfo() {
		//1.获取spring容器对象
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		//2.获取bean对象
		IUserService  userService = ac.getBean("userService",IUserService.class);
		//3.
		Integer id = 2;
		String username = "圆圆";
		Integer gender = 0;
		String phone = "13500505445";
		String email = "13500505445@163.com";
		Integer rows = userService.changeInfo(id, username, gender, phone, email);
		System.out.println("rows:"+rows);
		//4.关闭容器
		ac.close();
	}
}
