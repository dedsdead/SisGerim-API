package sisgerim.backend.domain.caracteristica;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CaracteristicaRequestDTO(UUID id, @NotNull int quantidade, @NotBlank String descricao) {}
