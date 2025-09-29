package com.apprpg.model; // Define o pacote do arquivo

import jakarta.persistence.*; // Importa as anotações de persistência do Jakarta
import java.time.LocalDateTime; // Importa classe para data/hora

@Entity // Indica que esta classe é uma entidade JPA
public class Message { // Classe que representa uma mensagem do chat
    @Id // Indica o campo identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long id; // Identificador da mensagem

    private String text; // Texto da mensagem
    private String username; // Nome do usuário que enviou
    private String characterName; // Nome do personagem da ficha ativa
    private LocalDateTime timestamp; // Data/hora da mensagem

    // Métodos getters e setters para acessar e modificar os campos
    public Long getId() { return id; } // Retorna o ID
    public void setId(Long id) { this.id = id; } // Define o ID
    public String getText() { return text; } // Retorna o texto
    public void setText(String text) { this.text = text; } // Define o texto
    public String getUsername() { return username; } // Retorna o nome do usuário
    public void setUsername(String username) { this.username = username; } // Define o nome do usuário
    public String getCharacterName() { return characterName; } // Retorna o nome do personagem
    public void setCharacterName(String characterName) { this.characterName = characterName; } // Define o nome do personagem
    public LocalDateTime getTimestamp() { return timestamp; } // Retorna o timestamp
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; } // Define o timestamp
}
// Fim da classe Message
