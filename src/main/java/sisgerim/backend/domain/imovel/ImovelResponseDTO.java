package sisgerim.backend.domain.imovel;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.tipo.Tipo;

public record ImovelResponseDTO(UUID id, UUID idUsuario, UUID idEndereco, Tipo tipo, List<Caracteristica> caracteristicas, UUID idNegociador, double metragem, double valor, LocalDate dataVenda, String descricao, String matricula, OffsetDateTime excluidoEm) {}
