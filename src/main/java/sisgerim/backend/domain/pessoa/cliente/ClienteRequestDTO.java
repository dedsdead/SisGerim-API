package sisgerim.backend.domain.pessoa.cliente;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.endereco.Endereco;
import sisgerim.backend.domain.pessoa.corretor.Corretor;
import sisgerim.backend.domain.tipo.Tipo;

public record ClienteRequestDTO (UUID id, @NotNull Corretor corretor, Endereco endereco, Tipo tipo, List<Caracteristica> caracteristicas, String bairro, @NotBlank String nome, @NotBlank String email, @NotBlank String telefone, String cpf, OffsetDateTime excluidoEm) {}
