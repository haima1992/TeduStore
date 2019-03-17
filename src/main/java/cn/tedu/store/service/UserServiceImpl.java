package cn.tedu.store.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UserNotExistsException;
@Service("userService")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserMapper userMapper;
	
	public User reg(User user) {
		// 判断用户名是否被占用
		User u = findUserByUsername(user.getUsername());
		if(u != null) {
			// 用户名被占用,则:
			throw new UsernameConflictException("用户名"+user.getUsername()+"已注册！");
		}else {
			//用户名没有被占用,则执行注册相关任务
			//把密码加密
			String salt = UUID.randomUUID().toString().toUpperCase();
			String md5Password = getEncrpytedPassword(user.getPassword(),salt);
			user.setPassword(md5Password);
			//保存uuid,即盐
			user.setUuid(salt);
			//保存日志信息
			Date now = new Date();
			user.setCreatedUser("System");
			user.setCreatedTime(now);
			user.setModifiedUser("System");
			user.setModifiedTime(now);
			// 使用Mybatis处理insert后,数据的id将被封装到参数对象中
			// 在执行以下代码之前，参数user中并没有id，执行完后，MyBatis将把id封装到参数对象user中
			userMapper.insert(user);
			return user;
		}
	}

	public User findUserById(Integer id) {
		//确定where子句的内容
		String where = "id="+id;
		//调用持久层对象实现查询
		List<User> users = userMapper.select(where,null,null,null);
		//判断查询结果
		if(users.size() == 0) {
			//没有找到数据,则用户不存在
			return null;
		}else {
			//找到数据,由于id唯一 ,则集合中的第1个元素就是要查询的数据
			return users.get(0);
		}
	}

	public User findUserByUsername(String username) {
		//确定where子句的内容
		String where = "username='"+username+"'";
		//调用持久层对象实现查询
		List<User> users = userMapper.select(where,null,null,null);
		//判断查询结果
		if(users.size() == 0) {
			//没有找到数据,则用户不存在
			return null;
		}else {
			//找到数据,由于用户名唯一 ,则集合中的第1个元素就是要查询的数据
			return users.get(0);
		}
	}

	public String getEncrpytedPassword(String password, String salt) {
		//把密码与盐拼接起来
		String str = password + salt;
		//获取拼接后的字符串的消息摘要
		String md5 = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		//返回摘要字符串,即密文
		return md5;
	}

	public User login(String username, String password) {
		//根据用户名查询用户信息
		User user = findUserByUsername(username);
		//判断是否查询到匹配的用户信息
		if(user == null) {//没有
			//抛出异常:用户名不存在UsernameNotExistsException异常
			throw new UserNotExistsException("用户名"+username+"不存在！");
		}else {//存在:继续
			//或取用户信息的盐(uuid)
			String uuid = user.getUuid();
			//基于盐,对于用户输入的密码(方法的参数)进行加密
			String md5 = getEncrpytedPassword(password,uuid);
			//判断加密后的密文与用户信息的密码是否匹配
			if(user.getPassword().equals(md5)) {//密码匹配
				//返回查询到的User对象
				return user;
			}else {//密码不匹配
				//抛出异常:密码不匹配PasswordNotMatchException异常
				throw new PasswordNotMatchException("您输入的密码不正确！");
			}
		}
	}

	public Integer changePassword(Integer id, String oldPassword, String newPassword) {
		//获取id匹配的用户信息
		User data = findUserById(id);
		//判断是否获取到用户信息
		if(data == null) {
			//抛出异常:用户名不存在UsernameNotExistsException异常
			throw new UserNotExistsException("用户信息(id="+id+")不存在，请联系系统管理员！");
		}else {
			// 获取盐
			String salt = data.getUuid();
			// 将旧密码加密
			String md5OldPasswprd = getEncrpytedPassword(oldPassword, salt);
			// 验证旧密码
			if(data.getPassword().equals(md5OldPasswprd)) {//原密码匹配
				//将新密码加密
				String md5NewPassword = getEncrpytedPassword(newPassword, salt);
				User user = new User(id,md5NewPassword);
				//调用持久层实现修改用户信息
				return userMapper.update(user);
			}else {//原密码不匹配
				//抛出异常:密码不匹配PasswordNotMatchException异常
				throw new PasswordNotMatchException("您输入的原密码不正确！");
			}
		}
	}

	public Integer changeInfo(Integer id, String username, Integer gender, String phone, String email) {
		//获取id匹配的用户信息
		User data = findUserById(id);
		//判断是否获取到用户信息
		if(data == null) {
			//抛出异常:用户名不存在UsernameNotExistsException异常
			throw new UserNotExistsException("用户信息(id="+id+")不存在，请联系系统管理员！");
		}else {
			// 判断用户名参数是否为null
			if(username != null) {// 不为null，即客户端提交了用户名，表示希望修改用户名
				//检查用户名是否被占用
				User u = findUserByUsername(username);
				if(u != null) {// 找到用户名匹配的数据，即用户名是存在的
					//判断这个用户名是不是当前自己的用户名
					if(data.getUsername().equals(u.getUsername())) {//找到的用户名是自己的
						//不需要修改用户名
						username = null;
					}else {//找到的用户名不是自己的
						//不需要修改用户名,抛出异常
						throw new UsernameConflictException("您要修改的用户名("+username+")已经被占用！");
					}
				}
			}
			//将id、username、gender、phone、email封装到User对象
			User user = new User(id,username,null,gender,phone,email);
			// 保存日志信息
			Date now = new Date();
			user.setModifiedUser("System");
			user.setModifiedTime(now);
			//调用持久层实现修改用户信息
			return userMapper.update(user);
		}
	}
	
}
