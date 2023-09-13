package sisgerim.backend.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.endereco.Endereco;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.tipo.Tipo;

public interface ImovelRepository extends JpaRepository<Imovel, UUID>{
    Optional<Imovel> findById(UUID id);
    List<Imovel> findAllByExcluidoEmNull();
    List<Imovel> findAllByExcluidoEmNullAndDataVendaNull();
    List<Imovel> findAllByExcluidoEmNullAndDataVendaNotNull();
    List<Imovel> findAllByTipoAndExcluidoEmNullAndDataVendaNull(Tipo tipo);
    List<Imovel> findAllByCaracteristicasAndExcluidoEmNullAndDataVendaNull(Caracteristica caracteristica);
    List<Imovel> findAllByEnderecoAndExcluidoEmNullAndDataVendaNull(Endereco endereco);
    List<Imovel> findAllByMetragemGreaterThanEqualAndExcluidoEmNullAndDataVendaNull(double metragem);
}
