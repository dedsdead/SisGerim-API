package sisgerim.backend.domain.caracteristica;

import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.pessoa.cliente.Cliente;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int quantidade;
    private String descricao;
    @ManyToMany(mappedBy = "caracteristicas")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Cliente> clientes;
    @ManyToMany(mappedBy = "caracteristicas")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Imovel> imoveis;
    public Caracteristica(CaracteristicaRequestDTO data) {
        this.quantidade = data.quantidade();
        this.descricao = data.descricao().toUpperCase();
    }
}
