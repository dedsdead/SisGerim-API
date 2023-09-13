package sisgerim.backend.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.tipo.Tipo;

public interface ImovelRepository extends JpaRepository<Imovel, UUID>{
    Optional<Imovel> findById(UUID id);
    Optional<Imovel> findByMatricula(String matricula);
    List<Imovel> findAllByExcluidoEmNull();
    List<Imovel> findAllByExcluidoEmNullAndDataVendaNull();
    List<Imovel> findAllByExcluidoEmNullAndDataVendaNotNull();
    List<Imovel> findAllByTipoAndExcluidoEmNullAndDataVendaNull(Tipo tipo);
    List<Imovel> findAllByCaracteristicasAndExcluidoEmNullAndDataVendaNull(Caracteristica caracteristica);
    List<Imovel> findAllByEnderecoId_BairroAndExcluidoEmNullAndDataVendaNull(String bairro);
    List<Imovel> findAllByMetragemGreaterThanEqualAndExcluidoEmNullAndDataVendaNull(double metragem);
    List<Imovel> findAllByValorBetweenAndExcluidoEmNullAndDataVendaNotNull(double valorInicial, double valorFinal);
}
