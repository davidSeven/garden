package com.sky.system.events;

import org.springframework.context.ApplicationEvent;

/**
 * @date 2020-11-05 005 14:31
 */
public class LoginLogEvent extends ApplicationEvent {

    private Long userId;
    private Long leaseTime;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public LoginLogEvent(Object source) {
        super(source);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(Long leaseTime) {
        this.leaseTime = leaseTime;
    }
}
