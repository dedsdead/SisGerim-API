package sisgerim.backend.domain.pessoa.cliente;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.pessoa.Pessoa;
import sisgerim.backend.domain.pessoa.corretor.Corretor;
import sisgerim.backend.domain.tipo.Tipo;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa {
    @ManyToMany(mappedBy = "clientes")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Corretor> corretores;
    @ManyToOne
    @JoinColumn(name = "id_tipo", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Tipo tipo;
    @ManyToMany
    @JoinTable(
        name = "cliente_busca_caracteristica",
        joinColumns = @JoinColumn(name = "id_cliente", updatable = false),
        inverseJoinColumns = @JoinColumn(name = "id_caracteristica", updatable = false)
    )
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
    private String bairro;
    public Cliente(ClienteRequestDTO data) {
        super(data.id(), data.endereco(), data.nome(), data.email(), data.telefone(), data.cpf(), data.excluidoEm());
        if (data.tipo() != null) {
            this.tipo = data.tipo();
        }
        if (data.caracteristicas() != null) {
            this.caracteristicas = data.caracteristicas();
        }
        if (data.bairro() != null) {
            this.bairro = data.bairro().toUpperCase();
        }
    }
}
