package sisgerim.backend.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.pessoa.corretor.Corretor;
import java.util.List;

public interface CorretorRepository extends JpaRepository<Corretor, UUID>{
    Optional<Corretor> findById(UUID id);
    Optional<Corretor> findByEmail(String email);
    Optional<Corretor> findByCpf(String cpf);
    Optional<Corretor> findByCreci(String creci);
    List<Corretor> findAllByExcluidoEmNull();
    Optional<Corretor> findByExcluidoEmNullAndUsuarioNotNullAndEmailLikeIgnoreCase(String email);
}
