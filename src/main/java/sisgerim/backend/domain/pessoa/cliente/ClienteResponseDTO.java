package sisgerim.backend.domain.pessoa.cliente;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.tipo.Tipo;

public record ClienteResponseDTO(
    UUID id,
    UUID idEndereco,
    Tipo tipo,
    List<Caracteristica> caracteristicas,
    String bairro,
    String nome,
    String email,
    String telefone,
    String cpf,
    OffsetDateTime excluidoEm
) {
    public ClienteResponseDTO(Cliente cliente){
        this(
            cliente.getId(),
            cliente.getEnderecoId(),
            cliente.getTipo(),
            cliente.getCaracteristicas(),
            cliente.getBairro(),
            cliente.getNome(),
            cliente.getEmail(),
            cliente.getTelefone(),
            cliente.getCpf(),
            cliente.getExcluidoEm()
        );
    }
}
