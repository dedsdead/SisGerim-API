package sisgerim.backend.domain.imovel;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.endereco.Endereco;
import sisgerim.backend.domain.pessoa.Pessoa;
import sisgerim.backend.domain.pessoa.corretor.Corretor;
import sisgerim.backend.domain.tipo.Tipo;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_usuario", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Corretor usuario;
    @ManyToOne
    @JoinColumn(name = "id_endereco", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Endereco endereco;
    @ManyToOne
    @JoinColumn(name = "id_tipo", updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Tipo tipo;
    @ManyToMany
    @JoinTable(
        name = "imovel_tem_caracteristica",
        joinColumns = @JoinColumn(name = "id_imovel", updatable = false),
        inverseJoinColumns = @JoinColumn(name = "id_caracteristica", updatable = false)
    )
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Caracteristica> caracteristicas;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_proprietario", referencedColumnName = "id", updatable = false),
        @JoinColumn(name = "id_parceiro", referencedColumnName = "id", updatable = false)
    })
    @JsonProperty(access = Access.WRITE_ONLY)
    private Pessoa negociador;
    private double metragem;
    private double valor;
    private LocalDate dataVenda;
    private String descricao;
    private String matricula;
    @Column(name = "excluido_em", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime excluidoEm;
    public Imovel(ImovelRequestDTO data){
        this.usuario = data.usuario();
        this.endereco = data.endereco();
        this.tipo = data.tipo();
        if(data.caracteristicas() != null && data.caracteristicas().size() > 0){
            this.caracteristicas = data.caracteristicas();
        }
        this.negociador = data.negociador();
        this.metragem = data.metragem();
        this.valor = data.valor();
        if (data.dataVenda() != null) {
            this.dataVenda = data.dataVenda();
        }
        this.descricao = data.descricao();
        if (data.matricula() != null) {
            this.matricula = data.matricula();
        }
        if(data.excluidoEm() != null){
            this.excluidoEm = data.excluidoEm();
        }
    }
}
