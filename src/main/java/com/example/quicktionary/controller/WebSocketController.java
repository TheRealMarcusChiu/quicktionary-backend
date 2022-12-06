package com.example.quicktionary.controller;

import com.example.quicktionary.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * from config/WebSocketConfig.configureMessageBroker(...) all the messages sent from clients with a destination
 * starting with /app will be routed to these message handling methods annotated with @MessageMapping
 */
@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate template;

    public void roomUpdated(final Room room) {
        this.template.convertAndSend("/topic/room-updated", room);
    }
}
