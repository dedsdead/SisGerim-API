package sisgerim.backend.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.pessoa.cliente.Cliente;
import sisgerim.backend.domain.tipo.Tipo;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
    Optional<Cliente> findById(UUID id);
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);
    List<Cliente> findAllByExcluidoEmNull();
    List<Cliente> findAllByCorretores_IdAndExcluidoEmNull(UUID corretorId);
    List<Cliente> findAllByCorretores_IdAndTipoAndExcluidoEmNull(UUID corretorId, Tipo tipo);
    List<Cliente> findAllByCorretores_IdAndCaracteristicasInAndExcluidoEmNull(UUID id, List<Caracteristica> caracteristicas);
    List<Cliente> findAllByCorretores_IdAndBairroLikeIgnoreCaseAndExcluidoEmNull(UUID id, String bairro);
}
