package projeto.SistemaAutoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.SistemaAutoria.entities.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
