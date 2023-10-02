package sisgerim.backend.domain.venda;

import java.time.OffsetDateTime;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.pessoa.cliente.Cliente;
import sisgerim.backend.domain.pessoa.corretor.Corretor;

public record VendaRequestDTO(
    UUID id,
    @NotNull Corretor corretor,
    @NotNull Imovel imovel,
    Cliente comprador,
    Corretor parceiro,
    @NotNull double valor,
    @NotBlank String status,
    @NotBlank String descricao,
    OffsetDateTime excluidaEm
) {}
