package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.domain.Blog;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.repository.BlogRepository;

/**
 * Blog Service.
 * @className BlogServiceImpl.java
 * @author Nuri
 * @date Apr 1, 2019 8:08:50 PM   
*/
@Service
public class BlogServiceImpl implements IBlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	/**
	 * 保存博客
	 */
	@Override
	public Blog saveBlog(Blog blog) {
		return blogRepository.save(blog);
	}
	
	/**
	 * 删除博客
	 */
	@Override
	public void deleteBlog(Long id) {
		blogRepository.delete(id);
	}

	@Override
	public Blog getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 查询所有blog
	 */
	@Override
	public List<Blog> finaAll() {
		return blogRepository.findAll();
	}


	/**
	 * 根据用户名查询名下博客
	 */
	@Override
	public Page<Blog> findByUser(User user, Pageable pageable) {
		return blogRepository.findByUser(user, pageable);
	}
	
	/**
	 * 根据ID查询
	 */
	@Override
	public Blog findById(Long id) {
		return blogRepository.findOne(id);
	}
	
}

