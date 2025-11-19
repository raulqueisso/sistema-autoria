package projeto.SistemaAutoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.SistemaAutoria.entities.Historia;

public interface HistoriaRepository extends JpaRepository<Historia, Long> {
}
