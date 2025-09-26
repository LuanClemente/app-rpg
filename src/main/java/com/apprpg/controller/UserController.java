package com.apprpg.controller; // Define o pacote do arquivo

import com.apprpg.model.User; // Importa o modelo User
import com.apprpg.repository.UserRepository; // Importa o repositório UserRepository
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired
import org.springframework.web.bind.annotation.*; // Importa as anotações do Spring Web

import java.util.List; // Importa a classe List

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/users") // Mapeia as requisições para /api/users
public class UserController { // Classe controladora para operações de usuário
    @Autowired // Injeta o repositório UserRepository
    private UserRepository repository; // Repositório para operações CRUD

    @GetMapping // Mapeia as requisições GET
    public List<User> getAll() { // Retorna todos os usuários
        return repository.findAll(); // Busca todos os usuários no banco de dados
    }

    @GetMapping("/{id}") // Mapeia as requisições GET com um ID específico
    public User getById(@PathVariable Long id) { // Retorna um usuário pelo ID
        return repository.findById(id).orElse(null); // Busca o usuário pelo ID ou retorna null se não encontrado
    }

    @PostMapping // Mapeia as requisições POST
    public User create(@RequestBody User user) { // Cria um novo usuário
        return repository.save(user); // Salva o usuário no banco de dados
    }

    @PutMapping("/{id}") // Mapeia as requisições PUT com um ID específico
    public User update(@PathVariable Long id, @RequestBody User user) { // Atualiza um usuário existente
        user.setId(id); // Define o ID do usuário a ser atualizado
        return repository.save(user); // Salva o usuário atualizado no banco de dados
    }

    @DeleteMapping("/{id}") // Mapeia as requisições DELETE com um ID específico
    public void delete(@PathVariable Long id) { // Deleta um usuário pelo ID
        repository.deleteById(id); // Deleta o usuário do banco de dados pelo ID
    }
}
// Fim da classe UserController
