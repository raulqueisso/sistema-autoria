package projeto.SistemaAutoria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projeto.SistemaAutoria.entities.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
    @Query("SELECT l.id, n1.id, n1.nome, n2.id, n2.nome FROM Link l JOIN l.nodeOrigem n1 JOIN l.nodeDestino n2")
    List<Object[]> findAllNodeOrigemDestino();
}
