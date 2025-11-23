package projeto.SistemaAutoria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.SistemaAutoria.entities.Historia;
import projeto.SistemaAutoria.entities.Link;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.entities.dto.HistoriaDto;
import projeto.SistemaAutoria.entities.dto.LinkDto;
import projeto.SistemaAutoria.repositories.LinkRepository;
import projeto.SistemaAutoria.repositories.NodeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    @Autowired
    private NodeService nodeService;

    public List<Link> findAll() {
        return repository.findAll();
    }

    public Link findById(long id) {
        Optional<Link> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public long saveLink(LinkDto linkDto) {
        Link link = (linkDto.id() != 0) ?
                repository.findById(linkDto.id()).orElse(new Link()) :
                new Link();

        Node nodeOrigem = nodeService.findById(linkDto.nodeOrigemId());
        if (nodeOrigem == null) {
            throw new IllegalArgumentException("Nó de origem não existe");
        }

        Node nodeDestino = nodeService.findById(linkDto.nodeDestinoId());
        if (nodeDestino == null) {
            throw new IllegalArgumentException("Nó de destino não existe");
        }

        link.setNodeOrigem(nodeOrigem);
        link.setNodeDestino(nodeDestino);

        link = repository.save(link);

        return link.getId();
    }
}
