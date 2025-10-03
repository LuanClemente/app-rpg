package com.apprpg.model; // Define o pacote do arquivo

import jakarta.persistence.*; // Importa as anotações de persistência do Jakarta
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "users")
public class User { // Classe que representa o usuário do sistema
    @Id // Indica o campo identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long id; // Identificador do usuário

    @NotBlank(message = "O nome de usuário não pode estar em branco")
    @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre 3 e 20 caracteres")
    private String username; // Nome de usuário
    private String email; // Email do usuário
    @NotBlank(message = "A senha não pode estar em branco")
    private String password; // Senha do usuário
    private String role; // Papel do usuário (ex: PLAYER, MASTER)

    // Métodos getters e setters para acessar e modificar os campos
    public Long getId() { return id; } // Retorna o ID
    public void setId(Long id) { this.id = id; } // Define o ID
    public String getUsername() { return username; } // Retorna o nome de usuário
    public void setUsername(String username) { this.username = username; } // Define o nome de usuário
    public String getEmail() { return email; } // Retorna o email
    public void setEmail(String email) { this.email = email; } // Define o email
    public String getPassword() { return password; } // Retorna a senha
    public void setPassword(String password) { this.password = password; } // Define a senha
    public String getRole() { return role; } // Retorna o papel
    public void setRole(String role) { this.role = role; } // Define o papel
}
// Fim da classe User
