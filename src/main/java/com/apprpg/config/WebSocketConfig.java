package com.apprpg.config; // Define o pacote do arquivo

import org.springframework.context.annotation.Configuration; // Importa Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry; // Importa MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker; // Habilita WebSocket
import org.springframework.web.socket.config.annotation.StompEndpointRegistry; // Importa StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer; // Importa WebSocketMessageBrokerConfigurer

@Configuration // Indica que esta classe é uma configuração
@EnableWebSocketMessageBroker // Habilita o uso de WebSocket com STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { // Configuração do WebSocket
    @Override // Sobrescreve método de configuração
    public void configureMessageBroker(MessageBrokerRegistry config) { // Configura o broker de mensagens
        config.enableSimpleBroker("/topic"); // Define o prefixo para tópicos
        config.setApplicationDestinationPrefixes("/app"); // Prefixo para destinos da aplicação
    }

    @Override // Sobrescreve método de registro de endpoint
    public void registerStompEndpoints(StompEndpointRegistry registry) { // Registra endpoint para conexão WebSocket
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); // Endpoint /ws com SockJS
    }
}
// Fim da classe WebSocketConfig
