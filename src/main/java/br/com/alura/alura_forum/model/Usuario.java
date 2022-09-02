package br.com.alura.alura_forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public final class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Long id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public String email() {
        return email;
    }

    public String senha() {
        return senha;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Usuario) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.nome, that.nome) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha);
    }

    @Override
    public String toString() {
        return "Usuario[" +
                "id=" + id + ", " +
                "nome=" + nome + ", " +
                "email=" + email + ", " +
                "senha=" + senha + ']';
    }

}
