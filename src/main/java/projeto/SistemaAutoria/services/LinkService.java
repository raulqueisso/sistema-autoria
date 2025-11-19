package projeto.SistemaAutoria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.SistemaAutoria.entities.Link;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.repositories.LinkRepository;
import projeto.SistemaAutoria.repositories.NodeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    public List<Link> findAll() {
        return repository.findAll();
    }

    public Link findById(long id) {
        Optional<Link> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
