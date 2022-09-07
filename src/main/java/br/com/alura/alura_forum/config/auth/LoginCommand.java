package br.com.alura.alura_forum.config.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginCommand {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
