package com.apprpg.controller; // Define o pacote do arquivo

import com.apprpg.model.User; // Importa o modelo User
import com.apprpg.repository.UserRepository; // Importa o repositório UserRepository
import com.apprpg.security.JwtUtil; // Importa utilitário JWT
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired; // Importa anotação Autowired
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*; // Importa anotações do Spring Web

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/auth") // Mapeia as requisições para /api/auth
public class AuthController { // Classe controladora para autenticação
  @Autowired // Injeta o repositório UserRepository
  private UserRepository userRepository; // Repositório de usuários

  @Autowired // Injeta utilitário JwtUtil
  private JwtUtil jwtUtil; // Utilitário para operações JWT

  @Autowired private PasswordEncoder passwordEncoder;

  // Endpoint para login
  @PostMapping("/login") // Mapeia requisições POST para /login
  public ResponseEntity<?> login(@RequestBody User loginRequest) { // Recebe dados de login
    // Verificação de segurança para evitar exceções com senhas nulas/vazias
    if (loginRequest.getUsername() == null || loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) {
        return ResponseEntity.badRequest().body("Usuário e senha não podem ser vazios.");
    }

    User user = userRepository.findByUsername(loginRequest.getUsername()); // Busca usuário pelo nome
    if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      String token = jwtUtil.generateToken(user.getUsername());
      return ResponseEntity.ok(Map.of("token", token, "username", user.getUsername()));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
  }

  // Endpoint para cadastro de usuário
  @PostMapping("/register") // Mapeia requisições POST para /register
  public ResponseEntity<String> register(@Valid @RequestBody User registerRequest) { // Recebe dados de cadastro
    // Verifica se já existe usuário com o mesmo nome
    if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
      return ResponseEntity.badRequest().body("Usuário já existe");
    }
    // Verificação de segurança para garantir que a senha não seja nula/vazia antes de criptografar
    if (registerRequest.getPassword() == null || registerRequest.getPassword().isBlank()) {
        return ResponseEntity.badRequest().body("A senha não pode estar em branco.");
    }

    // Criptografa a senha com BCrypt
    String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
    registerRequest.setPassword(hashedPassword);

    if (registerRequest.getRole() == null || registerRequest.getRole().isEmpty()) {
      registerRequest.setRole("PLAYER"); // Papel padrão
    }
    userRepository.save(registerRequest); // Salva usuário
    return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso");
  }
}
// Fim da classe AuthController
