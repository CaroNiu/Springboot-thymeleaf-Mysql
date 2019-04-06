package com.spring.boot.blog.service;
/**
 * @className IUserService.java
 * @author Nuri
 * @date Mar 27, 2019 9:33:08 PM   
*/

import java.util.List;

import com.spring.boot.blog.domain.User;

public interface IUserService {

	/**
	 * 存储客户信息
	 * @param user
	 * @return
	 */
	User saveUser(User user);
	
	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	void deleteUser(Long id);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	User updateUser(User user);
	
	/**
	 * 获取用户列表
	 * @return
	 */
	List<User> listUsers();
	
	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	User findUserById(Long id);
}

