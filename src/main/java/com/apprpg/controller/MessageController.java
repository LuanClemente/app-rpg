package com.apprpg.controller; // Define o pacote do arquivo

import com.apprpg.model.Message; // Importa o modelo Message
import com.apprpg.repository.MessageRepository; // Importa o repositório MessageRepository
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired
import org.springframework.web.bind.annotation.*; // Importa as anotações do Spring Web

import java.util.List; // Importa a classe List

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/messages") // Mapeia as requisições para /api/messages
public class MessageController { // Classe controladora para operações de mensagens
    @Autowired // Injeta o repositório MessageRepository
    private MessageRepository repository; // Repositório para operações CRUD

    @GetMapping // Mapeia as requisições GET
    @PreAuthorize("isAuthenticated()")
    public List<Message> getAll() { // Retorna todas as mensagens
        return repository.findAll(); // Busca todas as mensagens no banco de dados
    }

    @PostMapping // Mapeia as requisições POST
    @PreAuthorize("isAuthenticated()")
    public Message create(@RequestBody Message message) { // Cria uma nova mensagem
        return repository.save(message); // Salva a mensagem no banco de dados
    }
}
// Fim da classe MessageController
