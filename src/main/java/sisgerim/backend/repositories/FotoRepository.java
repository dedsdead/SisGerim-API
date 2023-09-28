package sisgerim.backend.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.foto.Foto;
import java.util.List;
import java.util.Optional;

public interface FotoRepository extends JpaRepository<Foto, UUID>{
    Optional<Foto> findById(UUID id);
    List<Foto> findByCaminhoLikeIgnoreCase(String caminho);
}
