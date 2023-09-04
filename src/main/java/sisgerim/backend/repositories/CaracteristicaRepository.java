package sisgerim.backend.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import java.util.Optional;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, UUID>{
        Optional<Caracteristica> findById(UUID id);
}
