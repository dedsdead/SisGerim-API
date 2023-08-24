package sisgerim.backend.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.tipo.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, UUID> {
    Optional<Tipo> findById(UUID id);
}
