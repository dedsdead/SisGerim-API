package sisgerim.backend.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.endereco.Endereco;
import sisgerim.backend.domain.endereco.EnderecoResponseDTO;

import java.util.List;


public interface EnderecoRepository extends JpaRepository<Endereco, UUID>{
    Optional<Endereco> findById(UUID id);
    List<EnderecoResponseDTO> findAllByCepLike(String cep);
    List<EnderecoResponseDTO> findAllByLocalidadeLike(String localidade);
    List<EnderecoResponseDTO> findAllByBairroLike(String bairro);
}
