package com.apprpg.config; // Define o pacote do arquivo

import com.apprpg.security.JwtFilter; // Importa o filtro JWT
import org.springframework.beans.factory.annotation.Autowired; // Importa Autowired
import org.springframework.context.annotation.Bean; // Importa a anotação Bean
import org.springframework.context.annotation.Configuration; // Importa a anotação Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Importa configuração de segurança
import org.springframework.security.web.SecurityFilterChain; // Importa o filtro de segurança
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Importa filtro de autenticação

@Configuration // Indica que esta classe é uma configuração
public class SecurityConfig { // Classe de configuração de segurança
    @Autowired // Injeta filtro JWT
    private JwtFilter jwtFilter; // Filtro para validar JWT

    @Bean // Indica que este método retorna um bean gerenciado pelo Spring
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // Configura o filtro de segurança
        http
            .csrf().disable() // Desabilita proteção CSRF
            .authorizeHttpRequests() // Inicia configuração de autorização
                .requestMatchers("/api/auth/**", "/api/users/**").permitAll() // Permite acesso livre aos endpoints de autenticação e usuário
                .anyRequest().authenticated() // Exige autenticação para outros endpoints
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Adiciona filtro JWT antes do filtro padrão
        return http.build(); // Retorna a configuração
    }
}
// Fim da classe SecurityConfig
