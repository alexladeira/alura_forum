package br.com.alura.alura_forum.topico.model;

import br.com.alura.alura_forum.model.Curso;
import br.com.alura.alura_forum.model.Usuario;
import br.com.alura.alura_forum.resposta.model.Resposta;
import br.com.alura.alura_forum.topico.command.TopicoCommand;
import br.com.alura.alura_forum.topico.dto.TopicoDetailDto;
import br.com.alura.alura_forum.topico.dto.TopicoDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class Topico {

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

    public Topico() {
    }

    public Topico(String titulo, String mensagem, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.curso = curso;
    }


    public static TopicoDto toDto(Topico topico) {
        return new TopicoDto(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao());
    }

    public static Topico fromCommand(TopicoCommand command, Curso curso) {
        return new Topico(command.titulo(), command.mensagem(), curso);
    }

    public static TopicoDetailDto toDetailDto(Topico topico) {
        return new TopicoDetailDto(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getUsuario()
                .getNome(), topico.getStatus(), topico.getRespostas()
                .stream()
                .map(Resposta::toDto)
                .collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public void setStatus(StatusTopico status) {
        this.status = status;
    }

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

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Topico) obj;
        return Objects.equals(this.id, that.id) && Objects.equals(this.titulo, that.titulo) && Objects.equals(this.mensagem, that.mensagem) && Objects.equals(this.dataCriacao, that.dataCriacao) && Objects.equals(this.status, that.status) && Objects.equals(this.usuario, that.usuario) && Objects.equals(this.curso, that.curso) && Objects.equals(this.respostas, that.respostas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, mensagem, dataCriacao, status, usuario, curso, respostas);
    }

    @Override
    public String toString() {
        return "Topico[" + "id=" + id + ", " + "titulo=" + titulo + ", " + "mensagem=" + mensagem + ", " + "dataCriacao=" + dataCriacao + ", " + "status=" + status + ", " + "usuario=" + usuario + ", " + "curso=" + curso + ", " + "respostas=" + respostas + ']';
    }
}
