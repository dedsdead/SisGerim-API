package sisgerim.backend.domain.tipo;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

public record TipoRequestDTO(UUID id, @NotBlank String nome){}
