package sisgerim.backend.domain.pessoa.corretor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record CorretorResponseDTO (UUID id, UUID idEndereco, String nome, String email, String telefone, String creci, String imobiliaria, List<String> redesSociais, OffsetDateTime excluidoEm) {
    public CorretorResponseDTO (Corretor corretor){
        this(corretor.getId(), corretor.getEnderecoId(), corretor.getNome(), corretor.getEmail(), corretor.getTelefone(), corretor.getCreci(), corretor.getImobiliaria(), corretor.getRedesSociais(), corretor.getExcluidoEm());
    }   
}
