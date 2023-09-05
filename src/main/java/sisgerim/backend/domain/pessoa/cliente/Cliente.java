package sisgerim.backend.domain.pessoa.cliente;

import java.util.List;
import java.util.UUID;
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
    @ManyToOne
    @JoinColumn(name = "id_usuario", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Corretor usuario;
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
    private List<Caracteristica> caracteristicas;
    private String bairro;
    public Cliente(ClienteRequestDTO data) {
        super(data.id(), data.endereco(), data.nome(), data.email(), data.telefone(), data.cpf(), data.excluidoEm());
        this.usuario = data.usuario();
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
    public UUID getUsuarioId(){
        if(this.usuario != null){
            return this.usuario.getId();
        }
        return null;
    }
}
