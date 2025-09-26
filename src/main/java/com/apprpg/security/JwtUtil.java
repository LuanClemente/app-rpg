package com.apprpg.security; // Define o pacote do arquivo

import io.jsonwebtoken.Jwts; // Importa utilitário para criação de JWT
import io.jsonwebtoken.SignatureAlgorithm; // Importa algoritmo de assinatura
import io.jsonwebtoken.Claims; // Importa Claims para extrair dados do token
import org.springframework.stereotype.Component; // Importa anotação Component

import java.util.Date; // Importa classe Date

@Component // Indica que esta classe é um componente gerenciado pelo Spring
public class JwtUtil { // Classe utilitária para operações com JWT
    private final String SECRET_KEY = "secret"; // Chave secreta para assinatura do token
    private final long EXPIRATION_TIME = 86400000; // Tempo de expiração do token (1 dia)

    // Gera um token JWT para o usuário
    public String generateToken(String username) {
        return Jwts.builder() // Inicia construção do token
                .setSubject(username) // Define o usuário como subject
                .setIssuedAt(new Date()) // Data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Data de expiração
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Assina o token
                .compact(); // Finaliza e retorna o token
    }

    // Extrai o nome de usuário do token
    public String extractUsername(String token) {
        return getClaims(token).getSubject(); // Retorna o subject do token
    }

    // Verifica se o token está válido
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token); // Extrai o usuário do token
        return (extractedUsername.equals(username) && !isTokenExpired(token)); // Verifica se usuário bate e token não expirou
    }

    // Verifica se o token está expirado
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date()); // Compara data de expiração com data atual
    }

    // Extrai os claims do token
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody(); // Retorna os dados do token
    }
}
// Fim da classe JwtUtil
