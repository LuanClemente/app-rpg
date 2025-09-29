package com.apprpg.controller; // Define o pacote do arquivo

import com.apprpg.model.User; // Importa o modelo User
import com.apprpg.repository.UserRepository; // Importa o repositório UserRepository
import com.apprpg.security.JwtUtil; // Importa utilitário JWT
import org.springframework.beans.factory.annotation.Autowired; // Importa anotação Autowired
import org.springframework.web.bind.annotation.*; // Importa anotações do Spring Web

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/auth") // Mapeia as requisições para /api/auth
public class AuthController { // Classe controladora para autenticação
    @Autowired // Injeta o repositório UserRepository
    private UserRepository userRepository; // Repositório de usuários

    @Autowired // Injeta utilitário JwtUtil
    private JwtUtil jwtUtil; // Utilitário para operações JWT

    // Endpoint para login
    @PostMapping("/login") // Mapeia requisições POST para /login
    public String login(@RequestBody User loginRequest) { // Recebe dados de login
        User user = userRepository.findByUsername(loginRequest.getUsername()); // Busca usuário pelo nome
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) { // Verifica se usuário existe e senha confere
            return jwtUtil.generateToken(user.getUsername()); // Retorna token JWT
        }
        return "Credenciais inválidas"; // Retorna mensagem de erro
    }

    // Endpoint para cadastro de usuário
    @PostMapping("/register") // Mapeia requisições POST para /register
    public String register(@RequestBody User registerRequest) { // Recebe dados de cadastro
        // Verifica se já existe usuário com o mesmo nome
        if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
            return "Usuário já existe";
        }
        // Simples hash de senha (exemplo, use BCrypt em produção)
        String hashedPassword = Integer.toHexString(registerRequest.getPassword().hashCode());
        registerRequest.setPassword(hashedPassword);
        if (registerRequest.getRole() == null || registerRequest.getRole().isEmpty()) {
            registerRequest.setRole("PLAYER"); // Papel padrão
        }
        userRepository.save(registerRequest); // Salva usuário
        return "Usuário cadastrado com sucesso";
    }
}
// Fim da classe AuthController
