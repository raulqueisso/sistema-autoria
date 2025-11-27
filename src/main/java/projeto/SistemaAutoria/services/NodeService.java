package projeto.SistemaAutoria.services;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.SistemaAutoria.entities.Historia;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.entities.dto.NodeDto;
import projeto.SistemaAutoria.repositories.NodeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NodeService {

    @Autowired
    private NodeRepository repository;

    @Autowired
    private HistoriaService historiaService;

    public List<Node> findAll() {
        return repository.findAll();
    }

    @Autowired
    private EntityManager em;

    @Transactional
    public List<Node> findNodeSemLinks() {
        return em.createNativeQuery("CALL mostrar_node_sem_links()", Node.class)
                .getResultList();
    }

    public Node findById(long id) {
        Optional<Node> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public long saveNode(NodeDto nodeDto) {
        Node node = (nodeDto.id() != 0) ?
                repository.findById(nodeDto.id()).orElse(new Node()) :
                new Node();

        Historia historia = historiaService.findById(nodeDto.historiaId());
        if (historia == null) {
            throw new IllegalArgumentException("História não existe");
        }

        node.setNome(nodeDto.nome());
        node.setConteudo(nodeDto.conteudo());
        node.setMaximoAtivacoes(nodeDto.maximoAtivacoes());
        node.setHistoria(historia);
        node = repository.save(node);

        return node.getId();
    }

    public void deleteById(long id) {
        Node node = repository.findById(id)
                .orElseThrow();

        Historia historia = node.getHistoria();


        if (historia.getNodeInicial() != null &&
                historia.getNodeInicial().getId() == node.getId()) {
            historia.setNodeInicial(null);
        }

        repository.delete(node);
    }
}
