package sisgerim.backend.domain.pessoa.corretor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import sisgerim.backend.domain.endereco.Endereco;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.pessoa.cliente.Cliente;

public record CorretorRequestDTO(
    UUID id,
    List<Corretor> parceiros,
    Endereco endereco,
    @NotBlank String nome,
    @NotBlank String email,
    @NotBlank String telefone,
    String cpf,
    @NotBlank String creci,
    String imobiliaria,
    @NotBlank String senha,
    List<String> redesSociais,
    List<Cliente> clientes,
    List<Imovel> imoveis,
    OffsetDateTime excluidoEm
) {}
