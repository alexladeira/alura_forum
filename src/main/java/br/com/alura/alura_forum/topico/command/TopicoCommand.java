package br.com.alura.alura_forum.topico.command;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record TopicoCommand(@NotNull @NotEmpty @Length(min = 5) String titulo,
                            @NotNull @NotEmpty @Length(min = 10) String mensagem,
                            @NotNull @NotEmpty String nomeCurso) {
}
