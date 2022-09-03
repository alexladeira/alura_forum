package br.com.alura.alura_forum.resposta.model;

import br.com.alura.alura_forum.model.Usuario;
import br.com.alura.alura_forum.resposta.dto.RespostaDto;
import br.com.alura.alura_forum.topico.model.Topico;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public final class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    private boolean solucao;

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Long id() {
        return id;
    }

    public String mensagem() {
        return mensagem;
    }

    public Topico topico() {
        return topico;
    }

    public LocalDateTime dataCriacao() {
        return dataCriacao;
    }

    public Usuario autor() {
        return autor;
    }

    public boolean solucao() {
        return solucao;
    }

    public static RespostaDto toDto(Resposta resposta) {
        return new RespostaDto(resposta.id(), resposta.mensagem(), resposta.dataCriacao(), resposta.autor().nome());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Resposta) obj;
        return Objects.equals(this.id, that.id) && Objects.equals(this.mensagem, that.mensagem) && Objects.equals(this.topico, that.topico) && Objects.equals(this.dataCriacao, that.dataCriacao) && Objects.equals(this.autor, that.autor) && this.solucao == that.solucao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mensagem, topico, dataCriacao, autor, solucao);
    }

    @Override
    public String toString() {
        return "Resposta[" + "id=" + id + ", " + "mensagem=" + mensagem + ", " + "topico=" + topico + ", " + "dataCriacao=" + dataCriacao + ", " + "autor=" + autor + ", " + "solucao=" + solucao + ']';
    }

}
