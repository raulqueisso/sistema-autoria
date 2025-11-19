package projeto.SistemaAutoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.SistemaAutoria.entities.Node;

public interface NodeRepository extends JpaRepository<Node, Long> {
}
