package sisgerim.backend.domain.pessoa.corretor;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.pessoa.Pessoa;
import sisgerim.backend.domain.pessoa.cliente.Cliente;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Corretor extends Pessoa implements UserDetails{
    @ManyToMany
    @JoinTable(
        name = "corretor_associa_corretor",
        joinColumns = @JoinColumn(name = "id_corretor", updatable = false),
        inverseJoinColumns = @JoinColumn(name = "id_parceiro", updatable = false)
    )
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Corretor> parceiros; 
    private String creci;
    private String imobiliaria;
    private String senha;
    private List<String> redesSociais;
    @ManyToMany(mappedBy = "parceiros")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Corretor> corretores;
    @ManyToMany
    @JoinTable(
        name = "corretor_tem_cliente",
        joinColumns = @JoinColumn(name = "id_corretor", updatable = false),
        inverseJoinColumns = @JoinColumn(name = "id_cliente", updatable = false)
    )
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Cliente> clientes;
    @ManyToMany
    @JoinTable(
        name = "corretor_tem_imovel",
        joinColumns = @JoinColumn(name = "id_corretor", updatable = false),
        inverseJoinColumns = @JoinColumn(name = "id_imovel", updatable = false)
    )
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Imovel> imoveis;
    
    public Corretor(CorretorRequestDTO data){
        super(data.id(), data.endereco(), data.nome().toUpperCase(), data.email(), data.telefone(), data.cpf(), data.excluidoEm());
        if (data.parceiros() != null) {
            this.parceiros = data.parceiros();
        }
        this.creci = data.creci().toUpperCase();
        if (data.imobiliaria() != null) {
            this.imobiliaria = data.imobiliaria().toUpperCase();    
        }
        if (data.senha() != null) {
            this.senha = new BCryptPasswordEncoder().encode(data.senha());
        }
        if (data.redesSociais() != null && data.redesSociais().size() > 0) {
            this.redesSociais = data.redesSociais();
        }
        if (data.clientes() != null && data.clientes().size() > 0) {
            this.clientes = data.clientes();
        }
        if (data.imoveis() != null && data.imoveis().size() > 0) {
            this.imoveis = data.imoveis();
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getPassword() {
        return senha;
    }
    @Override
    public String getUsername() {
        return getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Implementation
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Implementation
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Implementation 
        return true;
    }
    @Override
    public boolean isEnabled() {
        if(getExcluidoEm() == null) {
            return true;
        } else return false;
    }
}
