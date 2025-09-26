package com.apprpg.repository; // Define o pacote do arquivo

import com.apprpg.model.User; // Importa o modelo User
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

// Interface para operações de acesso a dados do usuário
public interface UserRepository extends JpaRepository<User, Long> { // Herda métodos CRUD do JpaRepository
    // Não é necessário implementar métodos, pois JpaRepository já fornece operações básicas
    User findByUsername(String username); // Busca usuário pelo nome de usuário
}
// Fim da interface UserRepository
