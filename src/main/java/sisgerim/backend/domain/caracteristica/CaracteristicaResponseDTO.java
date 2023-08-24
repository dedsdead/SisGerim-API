package sisgerim.backend.domain.caracteristica;

import java.util.UUID;

public record CaracteristicaResponseDTO(UUID id, int quantidade, String descricao) {
    public CaracteristicaResponseDTO(Caracteristica caracteristica){
        this(caracteristica.getId(), caracteristica.getQuantidade(), caracteristica.getDescricao());
    }
}
