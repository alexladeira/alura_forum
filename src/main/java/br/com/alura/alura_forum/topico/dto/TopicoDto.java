package br.com.alura.alura_forum.topico.dto;

import java.time.LocalDateTime;

public record TopicoDto(Long id, String titulo, String mensagem, LocalDateTime dataCriacao) {
}
