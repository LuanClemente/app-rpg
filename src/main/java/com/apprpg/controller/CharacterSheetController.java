package com.apprpg.controller;

import com.apprpg.model.CharacterSheet; // Importa o modelo CharacterSheet
import com.apprpg.repository.CharacterSheetRepository; // Importa o repositório CharacterSheetRepository
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired
import org.springframework.web.bind.annotation.*; // Importa as anotações do Spring Web

import java.util.List; // Importa a classe List

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/charactersheets") // Mapeia as requisições para /api/charactersheets
public class CharacterSheetController { 
    @Autowired // Injeta o repositório CharacterSheetRepository
    private CharacterSheetRepository repository; // Repositório para operações CRUD

    @GetMapping // Mapeia as requisições GET
    public List<CharacterSheet> getAll() { // Retorna todas as fichas de personagem
        return repository.findAll(); // Busca todas as fichas no banco de dados
    }

    @GetMapping("/{id}") // Mapeia as requisições GET com um ID específico
    public CharacterSheet getById(@PathVariable Long id) { // Retorna uma ficha de personagem pelo ID
        return repository.findById(id).orElse(null); // Busca a ficha pelo ID ou retorna null se não encontrada
    }

    @PostMapping
    public CharacterSheet create(@RequestBody CharacterSheet sheet) { // Cria uma nova ficha de personagem
        return repository.save(sheet); // Salva a ficha no banco de dados
    }

    @PutMapping("/{id}") // Mapeia as requisições PUT com um ID específico
    public CharacterSheet update(@PathVariable Long id, @RequestBody CharacterSheet sheet) { // Atualiza uma ficha de personagem existente
        sheet.setId(id); // Define o ID da ficha a ser atualizada
        return repository.save(sheet); // Salva a ficha atualizada no banco de dados
    }

    @DeleteMapping("/{id}") // Mapeia as requisições DELETE com um ID específico
    public void delete(@PathVariable Long id) { // Deleta uma ficha de personagem pelo ID
        repository.deleteById(id); // Deleta a ficha do banco de dados pelo ID
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