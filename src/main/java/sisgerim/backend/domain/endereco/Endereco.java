package sisgerim.backend.domain.endereco;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String cep;
    private String uf;
    private String localidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String complemento;

    public Endereco(EnderecoRequestDTO data) {
        this.cep = data.cep().toUpperCase();
        this.uf = data.uf().toUpperCase();
        this.localidade = data.localidade().toUpperCase();
        this.bairro = data.bairro().toUpperCase();
        this.logradouro = data.logradouro().toUpperCase();
        this.numero = data.numero();
        if (data.complemento() != null) {
            this.complemento = data.complemento().toUpperCase();
        }
    }    
}
