package br.com.alura.alura_forum.topico.dto;

import br.com.alura.alura_forum.resposta.dto.RespostaDto;
import br.com.alura.alura_forum.topico.model.StatusTopico;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDetailDto(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, String autor,
                              StatusTopico statusTopico, List<RespostaDto> respostas) {
}
