package com.apprpg.model; // Define o pacote do arquivo

import jakarta.persistence.*; // Importa as anotações de persistência do Jakarta


@Entity // Indica que esta classe é uma entidade JPA
public class CharacterSheet { // Classe que representa a ficha de personagem
    @Id // Indica o campo identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long id; // Identificador da ficha

    private String name; // Nome do personagem
    private String characterClass; // Classe do personagem (ex: Guerreiro, Mago)
    private String race; // Raça do personagem (ex: Humano, Elfo)
    private int level; // Nível do personagem
    private int strength; // Força
    private int dexterity; // Destreza
    private int constitution; // Constituição
    private int intelligence; // Inteligência
    private int wisdom; // Sabedoria
    private int charisma; // Carisma
    // Adicione outros campos conforme necessário

    // Métodos getters e setters para acessar e modificar os campos
    public Long getId() { return id; } // Retorna o ID
    public void setId(Long id) { this.id = id; } // Define o ID
    public String getName() { return name; } // Retorna o nome
    public void setName(String name) { this.name = name; } // Define o nome
    public String getCharacterClass() { return characterClass; } // Retorna a classe
    public void setCharacterClass(String characterClass) { this.characterClass = characterClass; } // Define a classe
    public String getRace() { return race; } // Retorna a raça
    public void setRace(String race) { this.race = race; } // Define a raça
    public int getLevel() { return level; } // Retorna o nível
    public void setLevel(int level) { this.level = level; } // Define o nível
    public int getStrength() { return strength; } // Retorna a força
    public void setStrength(int strength) { this.strength = strength; } // Define a força
    public int getDexterity() { return dexterity; } // Retorna a destreza
    public void setDexterity(int dexterity) { this.dexterity = dexterity; } // Define a destreza
    public int getConstitution() { return constitution; } // Retorna a constituição
    public void setConstitution(int constitution) { this.constitution = constitution; } // Define a constituição
    public int getIntelligence() { return intelligence; } // Retorna a inteligência
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; } // Define a inteligência
    public int getWisdom() { return wisdom; } // Retorna a sabedoria
    public void setWisdom(int wisdom) { this.wisdom = wisdom; } // Define a sabedoria
    public int getCharisma() { return charisma; } // Retorna o carisma
    public void setCharisma(int charisma) { this.charisma = charisma; } // Define o carisma
}

// Fim da classe CharacterSheet
