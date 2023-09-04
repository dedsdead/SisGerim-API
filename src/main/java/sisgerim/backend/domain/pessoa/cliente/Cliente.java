package sisgerim.backend.domain.pessoa.cliente;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @ManyToOne
    @JoinColumn(name = "id_caracteristica", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Caracteristica caracteristica;
    private String bairro;
    @OneToMany
    @JoinColumn(name = "id_usuario", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Corretor> corretores;
    public Cliente(ClienteRequestDTO data) {
        super(data.id(), data.endereco(), data.nome(), data.email(), data.telefone(), data.cpf(), data.excluidoEm());
        this.usuario = data.usuario();
        if (data.tipo() != null) {
            this.tipo = data.tipo();
        }
        if (data.caracteristica() != null) {
            this.caracteristica = data.caracteristica();
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
    public UUID getTipoId(){
        if(this.tipo != null){
            return this.tipo.getId();
        }
        return null;
    }
    public UUID getCaracteristicaId(){
        if(this.caracteristica != null){
            return this.caracteristica.getId();
        }
        return null;
    }
}
