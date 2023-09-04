package sisgerim.backend.domain.pessoa.cliente;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ClienteResponseDTO(UUID id, UUID idUsuario, UUID idEndereco, String nome, String email, String telefone, String cpf, UUID idTipo, UUID idCaracteristica, String bairro, OffsetDateTime excluidoEm) {
    public ClienteResponseDTO(Cliente cliente){
        this(cliente.getId(), cliente.getUsuarioId(), cliente.getEnderecoId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getCpf(), cliente.getTipoId(), cliente.getCaracteristicaId(), cliente.getBairro(), cliente.getExcluidoEm());
    }
}
