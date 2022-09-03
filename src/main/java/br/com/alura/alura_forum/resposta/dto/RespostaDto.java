package br.com.alura.alura_forum.resposta.dto;

import java.time.LocalDateTime;

public record RespostaDto(Long id, String mensagem, LocalDateTime dataCriacao, String nome) {
}
