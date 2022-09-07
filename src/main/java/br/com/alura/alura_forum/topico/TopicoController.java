package br.com.alura.alura_forum.topico;

import br.com.alura.alura_forum.curso.CursoRepository;
import br.com.alura.alura_forum.model.Curso;
import br.com.alura.alura_forum.topico.command.TopicoCommand;
import br.com.alura.alura_forum.topico.command.TopicoUpdateCommand;
import br.com.alura.alura_forum.topico.dto.TopicoDto;
import br.com.alura.alura_forum.topico.model.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController()
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public Page<TopicoDto> list(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        var result = nomeCurso != null ? topicoRepository.findByCursoNome(nomeCurso, pageable) : topicoRepository.findAll(pageable);
        return result.map(Topico::toDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> post(@RequestBody @Valid TopicoCommand command, UriComponentsBuilder builder) {
        Curso curso = cursoRepository.findByNome(command.nomeCurso());
        Topico topico = topicoRepository.save(Topico.fromCommand(command, curso));
        URI uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(Topico.toDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detail(@PathVariable Long id) {
        //            return Topico.toDetailDto(topicoRepository.getReferenceById(id));
        var topico = find(id);
        return topico.<ResponseEntity<Object>>map(value -> ResponseEntity.ok(Topico.toDetailDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid TopicoUpdateCommand command) {
        var optional = find(id);
        if (optional.isPresent()) {
            var topico = TopicoUpdateCommand.update(optional.get(), command);
            return ResponseEntity.ok(Topico.toDto(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        if (find(id).isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Optional<Topico> find(Long id) {
        return topicoRepository.findById(id);
    }

}
