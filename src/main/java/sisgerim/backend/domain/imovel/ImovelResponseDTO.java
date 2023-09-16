package sisgerim.backend.domain.imovel;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.tipo.Tipo;

public record ImovelResponseDTO(UUID id, UUID idEndereco, Tipo tipo, List<Caracteristica> caracteristicas, UUID idProprietario, double metragem, double valor, LocalDate dataVenda, String descricao, String matricula, OffsetDateTime excluidoEm) {
    public ImovelResponseDTO(Imovel imovel) {
        this(imovel.getId(), imovel.getEndereco().getId(), imovel.getTipo(), imovel.getCaracteristicas(), imovel.getProprietario().getId(), imovel.getMetragem(), imovel.getValor(), imovel.getDataVenda(), imovel.getDescricao(), imovel.getMatricula(), imovel.getExcluidoEm());
    }
}
