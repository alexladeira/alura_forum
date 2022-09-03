package br.com.alura.alura_forum.resposta.model;

import br.com.alura.alura_forum.model.Usuario;
import br.com.alura.alura_forum.resposta.dto.RespostaDto;
import br.com.alura.alura_forum.topico.model.Topico;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Resposta {
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

    public static RespostaDto toDto(Resposta resposta) {
        return new RespostaDto(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao(), resposta.getAutor()
                .nome());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public boolean isSolucao() {
        return solucao;
    }

    public void setSolucao(boolean solucao) {
        this.solucao = solucao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resposta resposta = (Resposta) o;
        return solucao == resposta.solucao && Objects.equals(id, resposta.id) && Objects.equals(mensagem, resposta.mensagem) && Objects.equals(topico, resposta.topico) && Objects.equals(dataCriacao, resposta.dataCriacao) && Objects.equals(autor, resposta.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mensagem, topico, dataCriacao, autor, solucao);
    }

    @Override
    public String toString() {
        return "Resposta{" + "id=" + id + ", mensagem='" + mensagem + '\'' + ", topico=" + topico + ", dataCriacao=" + dataCriacao + ", autor=" + autor + ", solucao=" + solucao + '}';
    }
}
