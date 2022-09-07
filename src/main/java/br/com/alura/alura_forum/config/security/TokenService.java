package br.com.alura.alura_forum.config.security;

import br.com.alura.alura_forum.usuario.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generate(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuer("Api Forum Alura")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.secret).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
        }

        return false;
    }

    public Long getIdUsuario(String token) {
        Claims body = Jwts.parserBuilder().setSigningKey(this.secret).build().parseClaimsJws(token).getBody();
        return Long.valueOf(body.getSubject());
    }
}
