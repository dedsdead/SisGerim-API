package sisgerim.backend.domain.pessoa.corretor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import sisgerim.backend.domain.endereco.Endereco;

public record CorretorRequestDTO(UUID id, Corretor usuario, Endereco endereco, @NotBlank String nome, @NotBlank String email, @NotBlank String telefone, String cpf, @NotBlank String creci, String imobiliaria, String senha, List<String> redesSociais, OffsetDateTime excluidoEm) {}
