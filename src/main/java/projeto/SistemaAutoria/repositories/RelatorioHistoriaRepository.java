package projeto.SistemaAutoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.SistemaAutoria.entities.Historia;
import projeto.SistemaAutoria.entities.RelatorioHistoria;

public interface RelatorioHistoriaRepository extends JpaRepository<RelatorioHistoria, Long> {
}
