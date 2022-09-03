package br.com.alura.alura_forum.topico.command;

import br.com.alura.alura_forum.topico.model.Topico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TopicoUpdateCommand {
    private @NotNull @NotEmpty @Length(min = 5) String titulo;
    private @NotNull @NotEmpty @Length(min = 10) String mensagem;

    public static Topico update(Topico referenceById, TopicoUpdateCommand command) {
        referenceById.setTitulo(command.getTitulo());
        referenceById.setMensagem(command.getMensagem());
        return referenceById;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TopicoUpdateCommand) obj;
        return Objects.equals(this.titulo, that.titulo) && Objects.equals(this.mensagem, that.mensagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, mensagem);
    }

    @Override
    public String toString() {
        return "TopicoUpdateCommand[" + "titulo=" + titulo + ", " + "mensagem=" + mensagem + ']';
    }
}
