package com.sky.gateway.listeners;

import com.sky.gateway.events.AccessEvent;
import com.sky.system.api.dto.AccessDto;
import com.sky.system.client.service.AuthenticationClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AccessListener {

    @Autowired
    private AuthenticationClientService authenticationClientService;

    @Async
    @EventListener
    public void onApplicationEvent(AccessEvent event) {
        AccessDto accessDto = new AccessDto();
        accessDto.setAuthorization((String) event.getSource());
        this.authenticationClientService.access(accessDto);
    }
}
