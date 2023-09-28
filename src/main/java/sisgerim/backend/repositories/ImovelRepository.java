package sisgerim.backend.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.tipo.Tipo;

public interface ImovelRepository extends JpaRepository<Imovel, UUID>{
    Optional<Imovel> findById(UUID id);
    Optional<Imovel> findByMatricula(String matricula);
    List<Imovel> findAllByExcluidoEmNull();
    List<Imovel> findAllByCorretores_IdAndExcluidoEmNull(UUID corretorId);
    List<Imovel> findAllByCorretores_IdAndExcluidoEmNullAndDataVendaNull(UUID corretorId);
    List<Imovel> findAllByCorretores_IdAndExcluidoEmNullAndDataVendaNotNull(UUID corretorId);
    List<Imovel> findAllByCorretores_IdAndTipoAndExcluidoEmNullAndDataVendaNull(UUID corretorId, Tipo tipo);
    List<Imovel> findAllByCorretores_IdAndEnderecoId_BairroLikeIgnoreCaseAndExcluidoEmNullAndDataVendaNull(UUID corretorId, String bairro);
    List<Imovel> findAllByCorretores_IdAndMetragemGreaterThanEqualAndExcluidoEmNullAndDataVendaNull(UUID corretorId, double metragem);
    List<Imovel> findAllByCorretores_IdAndValorBetweenAndExcluidoEmNullAndDataVendaNull(UUID corretorId, double valorInicial, double valorFinal);
}
