package br.com.alura.alura_forum.topico.model;

import br.com.alura.alura_forum.model.Curso;
import br.com.alura.alura_forum.model.Resposta;
import br.com.alura.alura_forum.model.Usuario;
import br.com.alura.alura_forum.topico.dto.TopicoDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public final class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico")
    private List<Resposta> respostas;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Long id() {
        return id;
    }

    public String titulo() {
        return titulo;
    }

    public String mensagem() {
        return mensagem;
    }

    public LocalDateTime dataCriacao() {
        return dataCriacao;
    }

    public StatusTopico status() {
        return status;
    }

    public Usuario usuario() {
        return usuario;
    }

    public Curso curso() {
        return curso;
    }

    public List<Resposta> respostas() {
        return respostas;
    }

    public static TopicoDto toDto(Topico topico) {
        return new TopicoDto(topico.id(), topico.titulo(), topico.mensagem(), topico.dataCriacao());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Topico) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.titulo, that.titulo) &&
                Objects.equals(this.mensagem, that.mensagem) &&
                Objects.equals(this.dataCriacao, that.dataCriacao) &&
                Objects.equals(this.status, that.status) &&
                Objects.equals(this.usuario, that.usuario) &&
                Objects.equals(this.curso, that.curso) &&
                Objects.equals(this.respostas, that.respostas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, mensagem, dataCriacao, status, usuario, curso, respostas);
    }

    @Override
    public String toString() {
        return "Topico[" +
                "id=" + id + ", " +
                "titulo=" + titulo + ", " +
                "mensagem=" + mensagem + ", " +
                "dataCriacao=" + dataCriacao + ", " +
                "status=" + status + ", " +
                "usuario=" + usuario + ", " +
                "curso=" + curso + ", " +
                "respostas=" + respostas + ']';
    }

}
