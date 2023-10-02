package sisgerim.backend.domain.endereco;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO(
    UUID id,
    @NotBlank String cep,
    @NotBlank String uf,
    @NotBlank String localidade,
    @NotBlank String bairro,
    @NotBlank String logradouro,
    int numero,
    String complemento
) {}
