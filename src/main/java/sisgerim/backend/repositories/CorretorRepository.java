package sisgerim.backend.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import sisgerim.backend.domain.pessoa.corretor.Corretor;
import java.util.List;

public interface CorretorRepository extends JpaRepository<Corretor, UUID>{
    Optional<Corretor> findById(UUID id);
    UserDetails findByEmail(String email);
    Optional<Corretor> findByCpf(String cpf);
    Optional<Corretor> findByCreci(String creci);
    Optional<Corretor> findByEmailLikeIgnoreCaseAndExcluidoEmNull(String email);
    List<Corretor> findAllByExcluidoEmNull();
    List<Corretor> findAllByParceirosAndExcluidoEmNull(Corretor corretor);
}
