package br.com.alura.alura_forum.curso;

import br.com.alura.alura_forum.model.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Test
    void should_loadCurso_when_findByNomeFoundCurso() {
        String nomeCurso = "HTML 5";
        Curso curso = repository.findByNome(nomeCurso);
        assertNotNull(curso);
        assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    void should_failLoadCurso_when_findByNomeDontFoundCurso() {
        String nomeCurso = "JPA";
        Curso curso = repository.findByNome(nomeCurso);
        assertNull(curso);
    }
}