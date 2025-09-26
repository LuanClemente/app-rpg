package com.apprpg;

import org.springframework.boot.SpringApplication; // Importa a classe SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa a anotação SpringBootApplication

@SpringBootApplication // Indica que esta é a classe principal do Spring Boot
public class AppRpgApplication { // Classe principal da aplicação
    public static void main(String[] args) { // Método principal que inicia a aplicação
        SpringApplication.run(AppRpgApplication.class, args); // Inicia a aplicação Spring Boot
    }
}
// Fim da classe AppRpgApplication


/**
 * O que faz:
 * - Inicia a aplicação Spring Boot
 */