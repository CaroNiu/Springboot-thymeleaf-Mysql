package com.spring.boot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.Authority;

/**
 * 权限管理.
 * 
 * @className AuthorityRepository.java
 * @author Nuri
 * @date Mar 31, 2019 4:19:24 PM   
*/
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}

