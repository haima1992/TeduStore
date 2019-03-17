package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UserNotExistsException;
import cn.tedu.store.service.ex.UsernameConflictException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/reg.do")
	public String showReg() {
		return "register";
	}
	
	@RequestMapping("/login.do")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/change_password.do")
	public String showChangePassword() {
		return "user_password";
	}
	
	@RequestMapping("/change_info.do")
	public String showChangeInfo(ModelMap map,HttpSession session) {
		//从session中获取当前用户id
		Integer id = getUidFromSession(session);
		//根据id获取当前用户信息
		User user = userService.findUserById(id);
		//判断是否获取到用户数据，因为可能在登录后，数据被管理员删除
		if(user != null) {//用户存在
			//将数据封装到ModelMap对象，再转发到前端页面
			map.addAttribute("user",user);
			//执行转发
			return "user_info";
		}else {//用户不存在
			//执行重定向
			return "redirect:../mian/error.do";
		}
	}
	
	@RequestMapping(value="/handle_reg.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleReg(
			String username,
			String password,
			String phone,
			String email){
		ResponseResult<Void> rr;
		User user = new User(username,password,phone,email);
		try {
			userService.reg(user);
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		}catch(UsernameConflictException e) {
			rr = new ResponseResult<Void>(e);
		}
		return rr;
	}
	
	@RequestMapping(value="/handle_login.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleLogin(String username,String password,HttpSession session) {
		ResponseResult<Void> rr;
		try {
			User user = userService.login(username, password);
			session.setAttribute("uid",user.getId());
			session.setAttribute("uname",user.getUsername());
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		}catch(ServiceException e) {
			rr = new ResponseResult<Void>(e);
		}
		return rr;
	}
	
	@RequestMapping("/logout.do")
	public String handleLogout(HttpSession session) {
		//清除session中的信息
		session.invalidate();
		//重定向到首页
		return "redirect:../main/index.do";
	}
	
	@RequestMapping(value="/handle_change_password.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleChangePassword(String oldPassword,String newPassword,String confirmPassword,HttpSession session){
		ResponseResult<Void> rr;
		if(newPassword!=null && newPassword.length()>=6 && newPassword.length()<=16) {
			if(newPassword.equals(confirmPassword)) {
				try {
					Integer id = Integer.valueOf(session.getAttribute("uid").toString());
					userService.changePassword(id, oldPassword, newPassword);
					rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
				} catch (ServiceException e) {
					rr = new ResponseResult<Void>(e);
				}
			}else {
				rr = new ResponseResult<Void>(ResponseResult.STATE_ERR,"您两次输入的新密码不匹配！");
			}
		}else {
			rr = new ResponseResult<Void>(ResponseResult.STATE_ERR,"您输入的新密码格式有误！");
		}
		return rr;
	}
	
	@RequestMapping(value="/handle_change_info.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> hanleChangeInfo(String username,Integer gender,String phone,String email,HttpSession session){
		// 检查数据的有效性
		if("".equals(username)) {
			username = null;
		}
		// 获取session中的uid
		Integer id = getUidFromSession(session);
		// 声明返回值
		ResponseResult<Void> rr;
		try {
			// 执行修改
			userService.changeInfo(id, username, gender, phone, email);
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (ServiceException e) {
			rr = new ResponseResult<Void>(e);
		}
		// 返回
		return rr;
	}

	@RequestMapping(value="/checkUsername.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> checkUsername(String username){
		System.out.println("丫丫");
		ResponseResult<Void> rr;
		try {
			User user = userService.findUserByUsername(username);
			if(user != null) {
				rr = new ResponseResult<Void>(1);
			}else {
				throw new UserNotExistsException("您输入的用户名不存在！");
			}
		} catch (ServiceException e) {
			rr = new ResponseResult<Void>(e);
		}
		return rr;
	}
	
}
