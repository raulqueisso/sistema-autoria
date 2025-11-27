package projeto.SistemaAutoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.SistemaAutoria.entities.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
