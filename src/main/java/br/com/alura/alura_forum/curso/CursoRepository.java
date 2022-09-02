package br.com.alura.alura_forum.curso;

import br.com.alura.alura_forum.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nomeCurso);
}
