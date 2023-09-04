package sisgerim.backend.domain.pessoa;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisgerim.backend.domain.endereco.Endereco;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_endereco", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Endereco endereco;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Column(name = "excluido_em", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime excluidoEm;
    
    public UUID getEnderecoId(){
        if(this.endereco != null){
            return this.endereco.getId();
        }
        return null;
    }
}
