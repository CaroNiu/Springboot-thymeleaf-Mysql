package com.spring.boot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.User;

/**
 * @className UserRepository.java
 * @author Nuri
 * @date Mar 27, 2019 9:16:39 PM   
*/
public interface UserRepository extends JpaRepository<User, Long>{
	
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
}

