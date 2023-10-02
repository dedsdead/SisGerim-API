package sisgerim.backend.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.venda.Venda;
import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, UUID>{
    Optional<Venda> findById(UUID id);
    List<Venda> findAllByExcluidaEmNull();
    List<Venda> findAllByCorretorIdAndExcluidaEmNull(UUID idCorretor);
    List<Venda> findAllByCorretorIdAndStatusLikeIgnoreCaseAndExcluidaEmNull(UUID idCorretor, String status);
}
