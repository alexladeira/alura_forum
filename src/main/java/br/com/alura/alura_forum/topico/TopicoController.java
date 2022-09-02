package br.com.alura.alura_forum.topico;

import br.com.alura.alura_forum.topico.dto.TopicoDto;
import br.com.alura.alura_forum.topico.model.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @GetMapping("/topicos")
    public List<TopicoDto> list(String nomeCurso) {
        var result = nomeCurso != null ? repository.findByCursoNome(nomeCurso) : repository.findAll();
        return result.stream().map(Topico::toDto).collect(Collectors.toList());
    }

}
