package projeto.SistemaAutoria.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.SistemaAutoria.entities.Historia;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.entities.dto.NodeDto;
import projeto.SistemaAutoria.entities.dto.Pager;
import projeto.SistemaAutoria.repositories.NodeRepository;

import java.util.ArrayList;
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

    public Pager<Node> findAll(Pageable pageable, String searchQuery, long idHistoria) {
        String search = (searchQuery != null) ? searchQuery.trim() : "";
        Specification<Node> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!search.isEmpty()) {
                String lowerSearch = "%" + search.toLowerCase() + "%";
                Predicate searchPredicate = builder.or(
                        builder.like(builder.lower(root.get("nome")), lowerSearch),
                        builder.like(builder.lower(root.get("conteudo")), lowerSearch)
                );
                predicates.add(searchPredicate);
            }

            Historia historia = historiaService.findById(idHistoria);
            predicates.add(builder.and(builder.equal(root.get(("historia")), historia)));

            return builder.and(predicates.toArray(new Predicate[0]));
        };

        Page<Node> users = repository.findAll(spec,pageable);

        return new Pager<>(
                users.getContent(),
                users.getNumber(),
                users.getSize(),
                users.getTotalPages(),
                users.getTotalElements()
        );
    }

    @Autowired
    private EntityManager em;

    @Transactional
    public List<Node> findNodeSemLinks(long idHistoria) {
        return em.createNativeQuery("CALL mostrar_node_sem_links(" + idHistoria + ")", Node.class)
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

        repository.delete(node);
    }
}
