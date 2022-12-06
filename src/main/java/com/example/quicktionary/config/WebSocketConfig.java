package com.example.quicktionary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @EnableWebSocketMessageBroker - enables our WebSocket server
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * registers a websocket endpoint that the clients will use to connect to our websocket server
     * STOMP (Simple Text Oriented Messaging Protocol):
     * - is a messaging protocol that defines the format and rules for data exchange
     * - STOMP is used to define things like:
     *   - how to send a message only to users who are subscribed to a particular topic
     *   - how to send a message to a particular user
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // .withSockJS() - SockJS is used to enable fallback options for browsers that don’t support websocket
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    /**
     * configures a message broker that will be used to route messages from one client to another
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // all the messages sent from clients with a destination starting with '/app'
        // will be routed to these message handling methods annotated with @MessageMapping
        registry.setApplicationDestinationPrefixes("/app");

        // enabled a simple in-memory message broker.
        // But you’re free to use any other full-featured message broker like:
        // - RabbitMQ or ActiveMQ
        registry.enableSimpleBroker("/topic");
    }
}