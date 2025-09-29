package com.apprpg.repository; // Define o pacote do arquivo

import com.apprpg.model.Message; // Importa o modelo Message
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

// Interface para operações de acesso a dados de mensagens
public interface MessageRepository extends JpaRepository<Message, Long> { // Herda métodos CRUD do JpaRepository
    // Não é necessário implementar métodos, pois JpaRepository já fornece operações básicas
}
// Fim da interface MessageRepository
