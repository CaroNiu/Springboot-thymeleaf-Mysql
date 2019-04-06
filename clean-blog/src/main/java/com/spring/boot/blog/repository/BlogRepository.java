package com.spring.boot.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.Blog;
import com.spring.boot.blog.domain.User;

/**
 * 博客Repository.
 * 
 * @className BlogRepository.java
 * @author Nuri
 * @date Apr 1, 2019 7:57:06 PM   
*/
public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	/**
	 * 根据用户名查询用户名下发表博客（按照创建时间排序）
	 * @param user
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUser(User user,Pageable pageable);
	
	
	
}

