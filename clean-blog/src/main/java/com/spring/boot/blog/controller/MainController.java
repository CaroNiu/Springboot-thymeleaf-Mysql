package com.spring.boot.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.blog.domain.Authority;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.IAuthorityService;
import com.spring.boot.blog.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @className MainController.java
 * @author Nuri
 * @date Mar 26, 2019 9:11:00 PM   
*/
@Controller
@Slf4j
public class MainController {
	
	@Autowired
	private IAuthorityService authorityService;
	@Autowired
	private IUserService userService;
	
	// 注册用户角色权限对应代码
	private static final Long ROLE_USER_AUTHORITY_ID = 2L;
	
	@GetMapping("/")
	public String root() {
		log.info("进入主界面！");
		return "redirect:/b/index";
	}
	
	/**
	 * 获取登录界面
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("errorMsg", "Login failure, account or password error!");
		return "login";
	}
	
	/**
	 * 获取注册页面
	 * @return
	 */
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	@PostMapping("/register")
	@ResponseBody
	public User registerUser(User user) {
		List<Authority> authorities = new ArrayList<>();
		authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
		user.setAuthorities(authorities);
		User saveUser = userService.saveUser(user);
		return saveUser;
	}
	
}

