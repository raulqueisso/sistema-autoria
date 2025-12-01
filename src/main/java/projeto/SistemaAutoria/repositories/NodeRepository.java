package projeto.SistemaAutoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.transaction.annotation.Transactional;
import projeto.SistemaAutoria.entities.Node;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long>, JpaSpecificationExecutor<Node> {


}

