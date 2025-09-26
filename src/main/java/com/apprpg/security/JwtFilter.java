package com.apprpg.security; // Define o pacote do arquivo

import jakarta.servlet.FilterChain; // Importa FilterChain
import jakarta.servlet.ServletException; // Importa ServletException
import jakarta.servlet.http.HttpServletRequest; // Importa HttpServletRequest
import jakarta.servlet.http.HttpServletResponse; // Importa HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired; // Importa Autowired
import org.springframework.stereotype.Component; // Importa Component
import org.springframework.web.filter.OncePerRequestFilter; // Importa filtro para cada requisição

import java.io.IOException; // Importa IOException

@Component // Indica que esta classe é um componente gerenciado pelo Spring
public class JwtFilter extends OncePerRequestFilter { // Filtro para validar JWT em cada requisição
    @Autowired // Injeta utilitário JwtUtil
    private JwtUtil jwtUtil; // Utilitário para operações JWT

    @Override // Sobrescreve método do filtro
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization"); // Obtém o header Authorization
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) { // Verifica se header começa com Bearer
            token = authHeader.substring(7); // Extrai o token
            username = jwtUtil.extractUsername(token); // Extrai o usuário do token
        }

        // Aqui você pode adicionar lógica para autenticar o usuário no contexto do Spring
        // Exemplo: se token válido, permitir acesso

        filterChain.doFilter(request, response); // Continua o fluxo da requisição
    }
}
// Fim da classe JwtFilter
