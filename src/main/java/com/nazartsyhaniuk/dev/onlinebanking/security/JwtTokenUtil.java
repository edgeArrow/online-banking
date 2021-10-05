package com.nazartsyhaniuk.dev.onlinebanking.security;

import com.nazartsyhaniuk.dev.onlinebanking.exception.InvalidJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.token.secretKey}")
    private String secretKey;

    @Value("${jwt.token.expiredTime}")
    private long expiredTime;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtTokenUtil(@Qualifier("customerDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", userDetails.getAuthorities());

        Date currentTime = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentTime)
                .setExpiration(new Date(currentTime.getTime() + expiredTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public boolean validateToken(String token) {
        try {
            final Claims claims = extractClaims(token);

            return !isTokenExpired(claims);

        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtTokenException("Expired or invalid JWT token");
        }
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

}
