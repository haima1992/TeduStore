package cn.tedu.store.service;

import cn.tedu.store.entity.User;

public interface IUserService {
	
	/**
	 * 用户注册
	 * @param user 注册的用户信息
	 * @return 用户信息，包括用户id
	 * @throws 
	 */
	User reg(User user);
	
	/**
	 * 根据id查询用户信息
	 * @param id 用户id
	 * @return
	 */
	User findUserById(Integer id);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息,包括用户id
	 */
	User findUserByUsername(String username);
	
	/**
	 * 获取加密后的密码
	 * @param password 明文密码
	 * @param salt 盐
	 * @return 密文密码
	 */
	String getEncrpytedPassword(String password,String salt);
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登录的用户信息
	 * @throws UserNotExistsException
	 * @throws PasswordNotMatchException
	 */
	User login(String username,String password);
	
	/**
	 * 修改用户密码
	 * @param id 用户id
	 * @param oidPassword 原密码
	 * @param newPassword 新密码
	 * @return
	 * @throws
	 */
	Integer changePassword(Integer id,String oldPassword,String newPassword);
	
	/**
	 * 修改用户资料
	 * @param id 用户id
	 * @param username 用户名
	 * @param gender 用户性别
	 * @param phone 用户电话
	 * @param email 用户邮箱
	 * @return
	 */
	Integer changeInfo(Integer id,String username,Integer gender,String phone,String email);
}
