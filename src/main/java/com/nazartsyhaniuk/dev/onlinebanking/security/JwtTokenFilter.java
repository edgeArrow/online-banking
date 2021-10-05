package com.nazartsyhaniuk.dev.onlinebanking.security;

import com.nazartsyhaniuk.dev.onlinebanking.exception.InvalidJwtTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenUtil.resolveToken(httpServletRequest);

        try {
            if (token != null && jwtTokenUtil.validateToken(token)) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication == null) {
                    authentication = jwtTokenUtil.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (InvalidJwtTokenException e) {
            SecurityContextHolder.clearContext();
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
