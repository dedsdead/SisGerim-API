package sisgerim.backend.domain.foto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import sisgerim.backend.domain.imovel.Imovel;

public record FotoRequestDTO(UUID id, Imovel imovel, @NotBlank String caminho) {}
