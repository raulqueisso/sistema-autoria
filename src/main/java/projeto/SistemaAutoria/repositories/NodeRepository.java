package projeto.SistemaAutoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import projeto.SistemaAutoria.entities.Node;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long>, JpaSpecificationExecutor<Node> {

    @Query("SELECT n.id, h.titulo, n.nome, n.conteudo FROM Node n JOIN n.historia h")
    List<Object[]> findAllTituloNomeConteudo();


}