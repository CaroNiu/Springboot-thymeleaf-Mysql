package com.spring.boot.blog.service;
/**
 * blog Service接口.
 * 
 * @className IBlogService.java
 * @author Nuri
 * @date Apr 1, 2019 8:01:46 PM   
*/


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.Blog;
import com.spring.boot.blog.domain.User;

public interface IBlogService {
	/**
	 * 保存blog信息
	 * @param blog
	 * @return
	 */
	Blog saveBlog(Blog blog);
	
	/**
	 * 删除博客
	 * @param id
	 */
	void deleteBlog(Long id);
	
	/**
	 * 根据ID查询博客信息
	 * @param id
	 * @return
	 */
	Blog getById(Long id);
	
	/**
	 * 查询客户名下发布博客
	 * @param user
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUser(User user ,Pageable pageable);
	
	/**
	 * 查询所有博客
	 * @return
	 */
	List<Blog> finaAll();
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Blog findById(Long id);
}

