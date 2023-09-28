package sisgerim.backend.domain.imovel;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.endereco.Endereco;
import sisgerim.backend.domain.foto.Foto;
import sisgerim.backend.domain.pessoa.cliente.Cliente;
import sisgerim.backend.domain.tipo.Tipo;

public record ImovelRequestDTO(UUID id, @NotNull Endereco endereco, @NotNull Tipo tipo, List<Caracteristica> caracteristicas, @NotNull Cliente proprietario, @NotNull double metragem, @NotNull double valor, LocalDate dataVenda, @NotBlank String descricao, String matricula, @NotEmpty List<Foto> fotos, OffsetDateTime excluidoEm) {}
