package br.com.alura.alura_forum.curso;

import br.com.alura.alura_forum.model.Curso;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//spring default database test to a memory database, use this configuration to avoid that
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private Curso curso = createCurso();

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

    @BeforeEach
    void setUp() {
        curso = entityManager.persist(curso);
    }

    @AfterEach
    void tearDown() {
        entityManager.remove(curso);
    }

    private Curso createCurso() {
        Curso curso = new Curso();
        curso.setNome("HTML 5");
        curso.setCategoria("Programacao");

        return curso;
    }
}