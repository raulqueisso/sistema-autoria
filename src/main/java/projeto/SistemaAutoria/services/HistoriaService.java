package projeto.SistemaAutoria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.SistemaAutoria.entities.Historia;
import projeto.SistemaAutoria.repositories.HistoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaService {

    @Autowired
    private HistoriaRepository repository;

    public List<Historia> findAll() {
        return repository.findAll();
    }

    public Historia findById(long id) {
        Optional<Historia> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
