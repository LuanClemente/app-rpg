package com.apprpg.config; // Define o pacote do arquivo

import com.apprpg.security.JwtFilter; // Importa o filtro JWT
import org.springframework.beans.factory.annotation.Autowired; // Importa Autowired
import org.springframework.context.annotation.Bean; // Importa a anotação Bean
import org.springframework.context.annotation.Configuration; // Importa a anotação Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Importa configuração de segurança
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; // Importa o filtro de segurança
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Importa filtro de autenticação

@Configuration // Indica que esta classe é uma configuração
@EnableMethodSecurity // Habilita segurança a nível de método (para @PreAuthorize)
public class SecurityConfig { // Classe de configuração de segurança
    @Autowired // Injeta filtro JWT
    private JwtFilter jwtFilter; // Filtro para validar JWT

    @Bean // Indica que este método retorna um bean gerenciado pelo Spring
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/ws/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
// Fim da classe SecurityConfig
