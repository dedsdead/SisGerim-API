package sisgerim.backend.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.endereco.Endereco;
import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID>{
    Optional<Endereco> findById(UUID id);
    List<Endereco> findAllByCepLikeIgnoreCase(String cep);
    List<Endereco> findAllByLocalidadeLikeIgnoreCase(String localidade);
    List<Endereco> findAllByBairroLikeIgnoreCase(String bairro);
}
