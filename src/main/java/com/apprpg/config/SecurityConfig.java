package com.apprpg.config; // Define o pacote do arquivo

import org.springframework.context.annotation.Bean; // Importa a anotação Bean
import org.springframework.context.annotation.Configuration; // Importa a anotação Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Importa configuração de segurança
import org.springframework.security.web.SecurityFilterChain; // Importa o filtro de segurança

@Configuration // Indica que esta classe é uma configuração
public class SecurityConfig { // Classe de configuração de segurança
    @Bean // Indica que este método retorna um bean gerenciado pelo Spring
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // Configura o filtro de segurança
        http
            .csrf().disable() // Desabilita proteção CSRF para facilitar testes
            .authorizeHttpRequests() // Inicia configuração de autorização
                .requestMatchers("/api/users/**", "/api/charactersheets/**").permitAll() // Permite acesso livre aos endpoints de usuário e ficha
                .anyRequest().authenticated(); // Exige autenticação para outros endpoints
        return http.build(); // Retorna a configuração
    }
}
// Fim da classe SecurityConfig
