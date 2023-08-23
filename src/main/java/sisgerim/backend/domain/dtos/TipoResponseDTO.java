package sisgerim.backend.domain.dtos;

import java.util.UUID;

import sisgerim.backend.domain.tipo.Tipo;

public record TipoResponseDTO(UUID id, String nome) {
    public TipoResponseDTO(Tipo tipo){
        this(tipo.getId(), tipo.getNome());
    }
}
