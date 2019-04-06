package com.spring.boot.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.Blog;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.IBlogService;

import lombok.extern.slf4j.Slf4j;

/**
 * 博客控制器.
 * 
 * @className BlogController.java
 * @author Nuri
 * @date Apr 4, 2019 2:51:31 PM   
*/
@Controller
@RequestMapping("/b")
@Slf4j
public class BlogController {
	
	@Autowired
	private IBlogService blogService;
	
	/**
	 * 查询博客详情
	 * @return
	 */
	@GetMapping("/blogdetail/{id}")
	public ModelAndView blogDetail(@PathVariable("id") Long id,Model model) {
		log.info("查询博客详细信息--开始");
		// 查询博客信息
		Blog blog = blogService.findById(id);
		User user = blog.getUser();
		model.addAttribute("user",user);
		model.addAttribute("blog", blog);
		return new ModelAndView("/share/blogdetail","blogModel",model);
	}
	
	/**
	 * 返回更新博客页面
	 * @return
	 */
	@GetMapping("/updateBlogdetail/{id}")
	public ModelAndView updateBlogDetail(@PathVariable("id") Long id,Model model) {
		log.info("查询博客详细信息--开始");
		// 查询博客信息
		Blog blog = blogService.findById(id);
		User user = blog.getUser();
		model.addAttribute("user",user);
		model.addAttribute("blog", blog);
		return new ModelAndView("/share/updateBlog","blogModel",model);
	}
	
	/**
	 * 删除博客(前台限制)
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteblog/{id}")
	public String deleteBlog(@PathVariable("id") Long id) {
		log.info("删除用户博客!--开始");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		log.info("获取当前登录用户名"+username);
		blogService.deleteBlog(id);
		return "redirect:/u/userinfo/userblog/" + username;
	}
	
	/**
	 * 查询所有博客
	 * @return
	 */
	@GetMapping("/index")
	public ModelAndView findAllBlogs(Model model) {
		log.info("查询所有博客信息");
		List<Blog> finaAllList = blogService.finaAll();
		model.addAttribute("allBlogs", finaAllList);
		return new ModelAndView("index","allBlogsModel",model);
	}
	
}

