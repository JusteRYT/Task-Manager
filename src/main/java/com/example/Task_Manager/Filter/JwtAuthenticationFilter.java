package com.example.Task_Manager.Filter;

import com.example.Task_Manager.Utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Фильтр для проверки JWT-токена.
 * <p>
 * Извлекает токен из заголовка Authorization и проверяет его.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    Logger logger = Logger.getLogger(JwtAuthenticationFilter.class.getName());

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
        String token = extractToken(request);
        logger.info("Полученный токен: " + token);
        if(token != null && jwtUtil.isTokenValid(token)) {
            String username = jwtUtil.extractUsername(token);
            logger.info("Пользователь токена" + username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    username, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Извлекает токен из заголовка Authorization.
     *
     * @param request HTTP-запрос
     * @return токен, если он присутствует
     */
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

}
