package com.apprpg.repository; // Define o pacote do arquivo

import com.apprpg.model.CharacterSheet; // Importa o modelo CharacterSheet
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

// Interface para operações de acesso a dados da ficha de personagem
public interface CharacterSheetRepository extends JpaRepository<CharacterSheet, Long> { // Herda métodos CRUD do JpaRepository
	// Não é necessário implementar métodos, pois JpaRepository já fornece operações básicas
}
// Fim da interface CharacterSheetRepository
