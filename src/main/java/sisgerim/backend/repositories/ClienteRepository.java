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
    List<Cliente> findAllByTipoAndExcluidoEmNull(Tipo tipo);
    List<Cliente> findAllByCaracteristicasAndExcluidoEmNull(Caracteristica caracteristica);
    List<Cliente> findAllByBairroLikeAndExcluidoEmNull(String bairro);
}
