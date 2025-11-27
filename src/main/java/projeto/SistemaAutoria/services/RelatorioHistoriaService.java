package projeto.SistemaAutoria.services;

import org.springframework.beans.factory.annotation.Autowired;
import projeto.SistemaAutoria.entities.Node;
import projeto.SistemaAutoria.entities.RelatorioHistoria;
import projeto.SistemaAutoria.repositories.NodeRepository;
import projeto.SistemaAutoria.repositories.RelatorioHistoriaRepository;

import java.util.List;
import java.util.Optional;

public class RelatorioHistoriaService {
    @Autowired
    private RelatorioHistoriaRepository repository;


    public RelatorioHistoria findById(long id) {
        Optional<RelatorioHistoria> obj = repository.findById(id);
        return obj.orElse(null);
    }
    public List<RelatorioHistoria> findAll() {
        return repository.findAll();
    }

}
