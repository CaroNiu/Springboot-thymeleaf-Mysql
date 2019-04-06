package com.spring.boot.blog.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;


import lombok.extern.slf4j.Slf4j;

/**
 * @className ConstraintViolationExceptionHandler.java
 * @author Nuri
 * @date Apr 3, 2019 9:21:14 PM   
*/
@Slf4j
public class ConstraintViolationExceptionHandler {
	/**
	 * 获取批量异常信息
	 * @param e
	 * @return
	 */
	public static String getMessage(ConstraintViolationException e) {
		log.info("公共批量获取异常信息--开始");
		List<String> msgList = new ArrayList<>();
		for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
			msgList.add(constraintViolation.getMessage());
        }
		String messages = StringUtils.join(msgList.toArray(), ";");
		log.info("异常信息："+messages);
		return messages;
	}
}

