package com.apprpg.controller;

import com.apprpg.model.CharacterSheet; // Importa o modelo CharacterSheet
import com.apprpg.model.User; // Importa o modelo User
import com.apprpg.repository.CharacterSheetRepository; // Importa o repositório CharacterSheetRepository
import com.apprpg.repository.UserRepository; // Importa o repositório UserRepository
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired
import org.springframework.web.bind.annotation.*; // Importa as anotações do Spring Web
import org.springframework.security.core.Authentication; // Importa Authentication para obter usuário logado

import java.util.List; // Importa a classe List

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/charactersheets") // Mapeia as requisições para /api/charactersheets
public class CharacterSheetController { // Classe controladora para operações de ficha de personagem
    @Autowired // Injeta o repositório CharacterSheetRepository
    private CharacterSheetRepository repository; // Repositório para operações CRUD

    @Autowired // Injeta o repositório UserRepository
    private UserRepository userRepository; // Repositório de usuários

    @GetMapping // Mapeia as requisições GET
    public ResponseEntity<List<CharacterSheet>> getAll(Authentication authentication) { // Retorna fichas conforme permissão
        User user = userRepository.findByUsername(authentication.getName()); // Obtém usuário logado
        List<CharacterSheet> sheets;
        if (user.getRole().equals("MASTER")) { // Se for mestre, retorna todas as fichas
            sheets = repository.findAll(); // Busca todas as fichas no banco de dados
        } else { // Se for jogador, retorna apenas suas fichas
            sheets = repository.findByUserId(user.getId()); // Busca apenas as fichas do usuário no banco
        }
        return ResponseEntity.ok(sheets);
    }

    @GetMapping("/{id}") // Mapeia as requisições GET com um ID específico
    public ResponseEntity<CharacterSheet> getById(@PathVariable Long id, Authentication authentication) { // Retorna ficha pelo ID conforme permissão
        User user = userRepository.findByUsername(authentication.getName()); // Obtém usuário logado
        Optional<CharacterSheet> sheetOpt = repository.findById(id); // Busca a ficha pelo ID

        return sheetOpt.map(sheet -> {
            if (user.getRole().equals("MASTER") || (sheet.getUser() != null && sheet.getUser().getId().equals(user.getId()))) {
                return ResponseEntity.ok(sheet); // 200 OK com a ficha
            }
            return ResponseEntity.<CharacterSheet>status(403).build(); // 403 Forbidden (sem permissão)
        }).orElse(ResponseEntity.<CharacterSheet>notFound().build()); // 404 Not Found (não encontrada)
    }

    @PostMapping // Mapeia as requisições POST
    public ResponseEntity<CharacterSheet> create(@RequestBody CharacterSheet sheet, Authentication authentication) { // Cria ficha vinculada ao usuário logado
        User user = userRepository.findByUsername(authentication.getName()); // Obtém usuário logado
        sheet.setUser(user); // Vincula ficha ao usuário
        CharacterSheet savedSheet = repository.save(sheet); // Salva a ficha no banco de dados
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSheet);
    }

    @PutMapping("/{id}") // Mapeia as requisições PUT com um ID específico
    public ResponseEntity<CharacterSheet> update(@PathVariable Long id, @RequestBody CharacterSheet sheetDetails, Authentication authentication) { // Atualiza ficha conforme permissão
        User user = userRepository.findByUsername(authentication.getName()); // Obtém usuário logado
        Optional<CharacterSheet> existingSheetOpt = repository.findById(id); // Busca ficha existente

        return existingSheetOpt.map(existing -> {
            if (user.getRole().equals("MASTER") || (existing.getUser() != null && existing.getUser().getId().equals(user.getId()))) {
                sheetDetails.setId(id); // Garante que o ID é o da URL
                sheetDetails.setUser(existing.getUser()); // Mantém o usuário dono original
                CharacterSheet updatedSheet = repository.save(sheetDetails);
                return ResponseEntity.ok(updatedSheet); // 200 OK com a ficha atualizada
            }
            return ResponseEntity.<CharacterSheet>status(403).build(); // 403 Forbidden
        }).orElse(ResponseEntity.<CharacterSheet>notFound().build()); // 404 Not Found
    }

    @DeleteMapping("/{id}") // Mapeia as requisições DELETE com um ID específico
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) { // Deleta ficha conforme permissão
        User user = userRepository.findByUsername(authentication.getName()); // Obtém usuário logado
        Optional<CharacterSheet> sheetOpt = repository.findById(id); // Busca ficha existente

        return sheetOpt.map(sheet -> {
            if (user.getRole().equals("MASTER") || (sheet.getUser() != null && sheet.getUser().getId().equals(user.getId()))) {
                repository.deleteById(id); // Deleta a ficha do banco de dados
                return ResponseEntity.noContent().build(); // 204 No Content (sucesso, sem corpo)
            }
            return ResponseEntity.<Void>status(403).build(); // 403 Forbidden
        }).orElse(ResponseEntity.<Void>notFound().build()); // 404 Not Found
    }
}
// Fim da classe CharacterSheetController

/**
 * Metodos utilizados:
 * - getAll: Retorna todas as fichas de personagem
 * - getById: Retorna uma ficha de personagem pelo ID
 * - create: Cria uma nova ficha de personagem
 * - update: Atualiza uma ficha de personagem existente
 * - delete: Deleta uma ficha de personagem pelo ID
 * 
 * Anotações utilizadas:
 * - @RestController: Indica que esta classe é um controlador REST
 * - @RequestMapping: Mapeia as requisições para um caminho específico
 * - @GetMapping: Mapeia as requisições GET
 * - @PostMapping: Mapeia as requisições POST
 * - @PutMapping: Mapeia as requisições PUT
 * - @DeleteMapping: Mapeia as requisições DELETE
 * - @Autowired: Injeta dependências automaticamente
 * - @PathVariable: Indica que um parâmetro de método deve ser vinculado a uma variável de caminho na URL
 * - @RequestBody: Indica que um parâmetro de método deve ser vinculado ao corpo da requisição
 * 
 * Importações utilizadas:
 * - com.apprpg.model.CharacterSheet: Importa o modelo CharacterSheet
 * - com.apprpg.repository.CharacterSheetRepository: Importa o repositório CharacterSheetRepository
 * - org.springframework.beans.factory.annotation.Autowired: Importa a anotação Autowired
 * - org.springframework.web.bind.annotation.*: Importa as anotações do Spring Web
 * - java.util.List: Importa a classe List
 * 
 * Requisições feitas:
 * - GET /api/charactersheets: Retorna todas as fichas de personagem
 * - GET /api/charactersheets/{id}: Retorna uma ficha de personagem pelo ID
 * - POST /api/charactersheets: Cria uma nova ficha de personagem
 * - PUT /api/charactersheets/{id}: Atualiza uma ficha de personagem existente
 * - DELETE /api/charactersheets/{id}: Deleta uma ficha de personagem pelo ID
 * 
 * Resposta esperada:
 * - GET /api/charactersheets: Lista de fichas de personagem em formato JSON
 * - GET /api/charactersheets/{id}: Ficha de personagem em formato JSON ou  null se não encontrada
 * - POST /api/charactersheets: Ficha de personagem criada em formato JSON
 * - PUT /api/charactersheets/{id}: Ficha de personagem atualizada em formato JSON
 * - DELETE /api/charactersheets/{id}: Nenhum conteúdo (204 No Content)
 * 
 * Função geral: Controlar as operações CRUD para fichas de personagem em um aplicativo RPG
 * 
 */