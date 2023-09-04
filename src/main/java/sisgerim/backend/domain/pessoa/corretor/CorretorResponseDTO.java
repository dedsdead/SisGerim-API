package sisgerim.backend.domain.pessoa.corretor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record CorretorResponseDTO (UUID id, UUID idUsuario, UUID idEndereco, String nome, String email, String telefone, String cpf, String creci, String imobiliaria, List<String> redesSociais, OffsetDateTime excluidoEm) {
    public CorretorResponseDTO (Corretor corretor){
        this(corretor.getId(), corretor.getUsuarioId(), corretor.getIdEndereco(), corretor.getNome(), corretor.getEmail(), corretor.getTelefone(), corretor.getCpf(), corretor.getCreci(), corretor.getImobiliaria(), corretor.getRedesSociais(), corretor.getExcluidoEm());
    }   
}