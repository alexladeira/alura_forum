package br.com.alura.alura_forum.topico;

import br.com.alura.alura_forum.topico.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
