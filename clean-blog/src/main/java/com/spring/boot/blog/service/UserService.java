package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import com.spring.boot.blog.vo.EmailSendUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.repository.UserRepository;

/**
 * 用户Service.
 * 
 * @className UserService.java
 * @author Nuri
 * @date Mar 27, 2019 9:33:59 PM   
*/
@Service
public class UserService implements IUserService,UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * 根据用户名查询用户
	 * @param username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
	
	/**
	 * 存储注册用户信息
	 * 使用发布订阅模式，使用Spring自带发布订阅模式，使用异步处理，单一迷失，发送邮件失败不影响主要任务的执行
	 * @param user
	 */
	@Transactional
	@Override
	public User saveUser(User user) {
		// modify 新增客户注册，发送邮件提醒
		this.applicationContext.publishEvent(new EmailSendUserEvent(user));
		return userRepository.save(user);
	}
	
	/**
	 * 删除用户
	 * @param id
	 */
	@Transactional
	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}
	
	/**
	 * 更新用户
	 * @param user
	 */
	@Transactional
	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * 查询所有用户信息
	 */
	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}
	
	/**
	 * 根据ID查询用户信息
	 */
	@Override
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}
	
}

