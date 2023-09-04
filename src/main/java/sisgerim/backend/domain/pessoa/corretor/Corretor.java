package sisgerim.backend.domain.pessoa.corretor;

import java.util.ArrayList;
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
import sisgerim.backend.domain.pessoa.Pessoa;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Corretor extends Pessoa {
    @ManyToOne
    @JoinColumn(name = "id_usuario", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Corretor usuario;
    private String creci;
    private String imobiliaria;
    private String senha;
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<String> redesSociais;
    @OneToMany
    @JoinColumn(name = "id_usuario", updatable = false)
    private List<Corretor> corretores = new ArrayList<Corretor>();
    
    public Corretor(CorretorRequestDTO data){
        super(data.id(), data.idEndereco(), data.nome().toUpperCase(), data.email(), data.telefone(), data.cpf(), data.excluidoEm());
        if (data.usuario() != null) {
            this.usuario = data.usuario();
        }
        this.creci = data.creci().toUpperCase();
        if (data.imobiliaria() != null) {
            this.imobiliaria = data.imobiliaria().toUpperCase();    
        }
        if (data.senha() != null) {
            this.senha = data.senha();
        }
        if (data.redesSociais() != null && data.redesSociais().size() > 0) {
            this.redesSociais = data.redesSociais();
        }
    }

    public UUID getUsuarioId(){
        if(this.usuario != null){
            return this.usuario.getId();
        }
        return null;
    }
    
}