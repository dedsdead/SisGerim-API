package sisgerim.backend.domain.tipo;

import java.util.UUID;

public record TipoResponseDTO(UUID id, String nome) {
    public TipoResponseDTO(Tipo tipo){
        this(tipo.getId(), tipo.getNome());
    }
}
