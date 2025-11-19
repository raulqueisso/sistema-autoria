package projeto.SistemaAutoria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.repositories.NodeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NodeService {

    @Autowired
    private NodeRepository repository;

    public List<Node> findAll() {
        return repository.findAll();
    }

    public Node findById(long id) {
        Optional<Node> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
