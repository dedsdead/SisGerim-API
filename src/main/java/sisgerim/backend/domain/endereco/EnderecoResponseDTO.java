package sisgerim.backend.domain.endereco;

import java.util.UUID;

public record EnderecoResponseDTO(
    UUID id,
    String cep,
    String uf,
    String localidade,
    String bairro,
    String logradouro,
    int numero,
    String complemento
) {
    public EnderecoResponseDTO(Endereco endereco){
        this(
            endereco.getId(),
            endereco.getCep(),
            endereco.getUf(),
            endereco.getLocalidade(),
            endereco.getBairro(),
            endereco.getLogradouro(),
            endereco.getNumero(),
            endereco.getComplemento()
        );
    }
}
