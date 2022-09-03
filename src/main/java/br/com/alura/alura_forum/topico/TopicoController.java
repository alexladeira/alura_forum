package br.com.alura.alura_forum.topico;

import br.com.alura.alura_forum.curso.CursoRepository;
import br.com.alura.alura_forum.model.Curso;
import br.com.alura.alura_forum.topico.command.TopicoCommand;
import br.com.alura.alura_forum.topico.dto.TopicoDetailDto;
import br.com.alura.alura_forum.topico.dto.TopicoDto;
import br.com.alura.alura_forum.topico.model.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> list(String nomeCurso) {
        var result = nomeCurso != null ? topicoRepository.findByCursoNome(nomeCurso) : topicoRepository.findAll();
        return result.stream().map(Topico::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<TopicoDto> post(@RequestBody @Valid TopicoCommand command, UriComponentsBuilder builder) {
        Curso curso = cursoRepository.findByNome(command.nomeCurso());
        Topico topico = topicoRepository.save(Topico.fromCommand(command, curso));
        URI uri = builder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();
        return ResponseEntity.created(uri).body(Topico.toDto(topico));
    }

    @GetMapping("/{id}")
    public TopicoDetailDto detail(@PathVariable Long id) {
        return Topico.toDetailDto(topicoRepository.getReferenceById(id));
    }

}
