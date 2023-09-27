package sisgerim.backend.domain.foto;

import java.util.UUID;

public record FotoResponseDTO(UUID id, UUID imovelId, String caminho) {
    public FotoResponseDTO(Foto foto){
        this(foto.getId(), foto.getImovel().getId(), foto.getCaminho());
    }
}
