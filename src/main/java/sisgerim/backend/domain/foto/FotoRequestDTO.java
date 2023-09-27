package sisgerim.backend.domain.foto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sisgerim.backend.domain.imovel.Imovel;

public record FotoRequestDTO(UUID id, @NotNull Imovel imovel, @NotBlank String caminho) {}
