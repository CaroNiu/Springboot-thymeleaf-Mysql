package com.spring.boot.blog.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.Blog;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.IBlogService;
import com.spring.boot.blog.service.IUserService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户操作类.
 * 
 * @className UserController.java
 * @author Nuri
 * @date Mar 31, 2019 5:33:34 PM   
*/
@Controller
@RequestMapping("/u")
@Slf4j
public class UserSpaceController {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBlogService blogService;
	
	/**
	 * 展示用户操作界面
	 * @return
	 */
	@GetMapping("/userinfo/{username}")
	public String userinfo(@PathVariable("username")String username) {
		log.info("获取查询用户名："+username);
		return "redirect:/u/userinfo/userblog/" + username;
	}
	
	/**
	 * 展示用户详情界面
	 */
	@GetMapping("/userinfo/userblog/{username}")
	public String userSpace(@PathVariable("username") String username,
							@RequestParam(value="async",required=false) boolean async,
							@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
							@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
							Model model) {
		log.info("查询客户名下发布的博客信息！！");
		// 查询客户名下发布博客
		User  user = (User)userDetailsService.loadUserByUsername(username);

		Pageable pageable = new PageRequest(pageIndex, pageSize);
		
		Page<Blog> page = blogService.findByUser(user, pageable);
		
		List<Blog> list = page.getContent();
		// 当前页面的数据列表
		model.addAttribute("user", user);
		model.addAttribute("page", page);
		model.addAttribute("blogList", list);
		log.info("查询成功");
		return (async==true?"/userspace/userblog :: #mainContainerRepleace":"/userspace/userblog");
	}
	
	/**
	 * 展示客户修改页面
	 * @param username	
	 * @param model
	 * @return
	 */
	@GetMapping("/{username}")
	public ModelAndView userSpace(@PathVariable("username") String username, Model model) {
		log.info("展示客户修改页面，获取用户名："+username);
		User  user = (User)userDetailsService.loadUserByUsername(username);
		model.addAttribute("user", user);
		log.info("返回model信息："+model.toString());
		return new ModelAndView("/userspace/userinfo", "userModel", model);
	}
	
	/**
	 * 保存客户修改信息
	 * @param username
	 * @param model
	 * @return
	 */
	@PostMapping("/{username}/profile")
	@PreAuthorize("authentication.name.equals(#username)") // 验证当前修改用户身份和登录用户身份
	public String profile(@PathVariable("username") String username, User user) {
		log.info("保存用户修改信息："+username);
		User originalUser = userService.findUserById(user.getId());
		// 设值
		originalUser.setName(user.getName());
		originalUser.setEmail(user.getEmail());
		// 判断密码是否修改
		// 原密码
		String rawPassword = originalUser.getPassword();
		// 加密方式
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// 原密码加密
		String oriPassword = encoder.encode(rawPassword);
		// 新密码加密
		String encodePasswd = encoder.encode(user.getPassword());
		
		boolean isMatch = encoder.matches(oriPassword, encodePasswd);
		if(!isMatch) {
			originalUser.setPassword(encodePasswd);
		}
		// 保存Ukey
		userService.saveUser(originalUser);
		return "redirect:/u/" + username + "/profile";
	}
	
	/**
	 * 修改成功后返回修改界面
	 * @param username
	 * @param model
	 * @return
	 */
	@GetMapping("/{username}/profile")
	@PreAuthorize("authentication.name.equals(#username)") 
	public ModelAndView profile(@PathVariable("username") String username, Model model) {
		User  user = (User)userDetailsService.loadUserByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("errorMsg", "success");
		return new ModelAndView("/userspace/userinfo", "userModel", model);
	}
	
	/**
	 * 获取新增博客的界面
	 * @param model
	 * @return
	 */
	@GetMapping("/blogs/edit")
	public ModelAndView createBlog(Model model) {
		model.addAttribute("blog", new Blog(null, null, null));
		return new ModelAndView("/userspace/writeBlog", "blogModel", model);
	}
	
	/**
	 * 保存博客
	 * @param username
	 * @param blog
	 * @return
	 */
	@PostMapping("/{username}/blogs/edit")
	@PreAuthorize("authentication.name.equals(#username)") 
	public ResponseEntity<Response> saveBlog(@PathVariable("username") String username, @RequestBody Blog blog) {
		log.info("进入blog添加方法");
		try {
			// 判断是修改还是新增
			if (blog.getId()!=null) {
				log.info("blog添加--当前为修改");
				Blog orignalBlog = blogService.findById(blog.getId());
				orignalBlog.setTitle(blog.getTitle());
				orignalBlog.setContent(blog.getContent());
				orignalBlog.setSummary(blog.getSummary());
				blogService.saveBlog(orignalBlog);
	        } else {
	        	log.info("blog添加--当前为新增");
	    		User user = (User)userDetailsService.loadUserByUsername(username);
	    		blog.setUser(user);
				blogService.saveBlog(blog);
	        }
		} catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		// 获取展示用户所有博客界面 /userinfo/userblog/{username}
		String redirectUrl = "/u/userinfo/userblog/" + username ;
		log.info("获取返回路径："+redirectUrl);
		return ResponseEntity.ok().body(new Response(true, "处理成功", redirectUrl));
	}
	
}

