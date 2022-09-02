package br.com.alura.alura_forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public final class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;

    public Long id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public String categoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Curso) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.nome, that.nome) &&
                Objects.equals(this.categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, categoria);
    }

    @Override
    public String toString() {
        return "Curso[" +
                "id=" + id + ", " +
                "nome=" + nome + ", " +
                "categoria=" + categoria + ']';
    }

}
