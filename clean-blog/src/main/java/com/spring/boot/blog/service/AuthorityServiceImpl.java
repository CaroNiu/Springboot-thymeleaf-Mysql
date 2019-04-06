package com.spring.boot.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.domain.Authority;
import com.spring.boot.blog.repository.AuthorityRepository;

/**
 * Authority 服务.
 * @className AuthorityServiceImpl.java
 * @author Nuri
 * @date Mar 31, 2019 4:22:11 PM   
*/
@Service
public class AuthorityServiceImpl implements IAuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	/**
	 * 根据ID查询Authority
	 */
	@Override
	public Authority getAuthorityById(Long id) {
		Authority authority = authorityRepository.findOne(id);
		return authority;
	}

}

