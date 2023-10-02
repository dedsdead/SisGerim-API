package sisgerim.backend.domain.venda;

import java.time.OffsetDateTime;
import java.util.UUID;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.pessoa.corretor.Corretor;

public record VendaResponseDTO(
    UUID id,
    Corretor corretor,
    Imovel imovel,
    UUID idComprador,
    UUID idParceiro,
    double valor,
    String status,
    String descricao,
    OffsetDateTime excluidaEm
) {
    public VendaResponseDTO(Venda venda){
        this(
            venda.getId(),
            venda.getCorretor(),
            venda.getImovel(),
            venda.getCompradorId(),
            venda.getParceiroId(),
            venda.getValor(),
            venda.getStatus(),
            venda.getDescricao(),
            venda.getExcluidaEm()
        );
    }
}
