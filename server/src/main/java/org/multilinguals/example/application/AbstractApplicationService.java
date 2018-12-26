package org.multilinguals.example.application;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.annotation.Resource;

public abstract class AbstractApplicationService {
    protected transient CommandGateway commandGateway;

    @Resource
    protected SimpMessagingTemplate messagingTemplate;

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
}
