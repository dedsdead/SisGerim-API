package sisgerim.backend.domain.venda;

import java.time.OffsetDateTime;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.pessoa.cliente.Cliente;
import sisgerim.backend.domain.pessoa.corretor.Corretor;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_corretor", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Corretor corretor;
    @ManyToOne
    @JoinColumn(name = "id_imovel", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Imovel imovel;
    @ManyToOne
    @JoinColumn(name = "id_comprador", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Cliente comprador;
    @ManyToOne
    @JoinColumn(name = "id_parceiro", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Corretor parceiro;
    private double valor;
    private String status;
    private String descricao;
    @Column(name = "excluida_em", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime excluidaEm;

    public Venda(VendaRequestDTO data){
        this.corretor = data.corretor();
        this.imovel = data.imovel();
        if(data.comprador() != null){
            this.comprador = data.comprador();
        }
        if (data.parceiro() != null){
            this.parceiro = data.parceiro();
        }
        this.valor = data.valor();
        this.status = data.status();
        this.descricao = data.descricao();
        if(data.excluidaEm() != null){
            this.excluidaEm = data.excluidaEm();
        }
    }
    public UUID getCompradorId(){
        if(this.comprador != null){
            return this.comprador.getId();
        }
        return null;
    }
    public UUID getParceiroId(){
        if(this.parceiro != null){
            return this.parceiro.getId();
        }
        return null;
    }
}
