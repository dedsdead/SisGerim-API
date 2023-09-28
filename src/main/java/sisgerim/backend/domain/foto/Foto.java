package sisgerim.backend.domain.foto;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_imovel")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Imovel imovel;
    String caminho;

    public Foto(FotoRequestDTO data){
        if(data.imovel() != null){
            this.imovel = data.imovel();
        }
        this.caminho = data.caminho().toUpperCase();
    }
    public UUID getImovelId(){
        if(this.imovel != null){
            return imovel.getId();
        }
        return null;
    }
}
