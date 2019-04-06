package com.spring.boot.blog.service;

import com.spring.boot.blog.domain.Authority;

/**
 * @className IAuthorityService.java
 * @author Nuri
 * @date Mar 31, 2019 4:20:15 PM   
*/
public interface IAuthorityService {
	
	/**
	 * 根据ID查询权限
	 * @param id
	 * @return
	 */
	Authority getAuthorityById(Long id);
}

