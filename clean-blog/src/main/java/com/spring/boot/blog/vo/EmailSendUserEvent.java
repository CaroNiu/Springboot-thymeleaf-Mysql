package com.spring.boot.blog.vo;

import org.springframework.context.ApplicationEvent;

/**
 * 事件监听
 * @author Nuri
 * @CreateTime 2021/2/26
 * @Describe
 */
public class EmailSendUserEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public EmailSendUserEvent(Object source) {
        super(source);
    }
}
