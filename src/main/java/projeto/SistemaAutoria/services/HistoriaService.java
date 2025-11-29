package projeto.SistemaAutoria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.SistemaAutoria.entities.Historia;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.entities.dto.HistoriaDto;
import projeto.SistemaAutoria.repositories.HistoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaService {

    @Autowired
    private HistoriaRepository repository;
    private NodeService nodeService;

    public List<Historia> findAll() {
        return repository.findAll();
    }

    public Historia findById(long id) {
        Optional<Historia> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public long saveHistoria(HistoriaDto historiaDto) {
        Historia historia = (historiaDto.id() != 0) ?
                repository.findById(historiaDto.id()).orElse(new Historia()) :
                new Historia();

        Node nodeInicial = nodeService.findById(historiaDto.nodeInicialId());
        if (nodeInicial == null) nodeInicial = new Node();

        historia.setTitulo(historiaDto.titulo());
        historia.setNodeInicial(nodeInicial);

        historia = repository.save(historia);

        return historia.getId();
    }

    public void deleteById(long id){
        repository.deleteById(id);
    }
}
