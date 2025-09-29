package com.apprpg.controller; // Define o pacote do arquivo

import com.apprpg.model.Message; // Importa o modelo Message
import org.springframework.messaging.handler.annotation.MessageMapping; // Importa MessageMapping
import org.springframework.messaging.handler.annotation.SendTo; // Importa SendTo
import org.springframework.stereotype.Controller; // Importa Controller

@Controller // Indica que esta classe é um controlador WebSocket
public class ChatController { // Classe controladora para chat ao vivo
    @MessageMapping("/chat") // Mapeia mensagens enviadas para /app/chat
    @SendTo("/topic/messages") // Envia para todos os clientes conectados no tópico
    public Message send(Message message) { // Recebe e retorna a mensagem
        return message; // Retorna a mensagem para ser exibida em tempo real
    }
}
// Fim da classe ChatController
