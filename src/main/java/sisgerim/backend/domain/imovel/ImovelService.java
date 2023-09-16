package sisgerim.backend.domain.imovel;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.tipo.Tipo;
import sisgerim.backend.repositories.ImovelRepository;

@Service
public class ImovelService {
    @Autowired
    private ImovelRepository repository;
    public List<ImovelResponseDTO> getAllActive(){
        List<ImovelResponseDTO> imoveis = new ArrayList<ImovelResponseDTO>();
        for(Imovel imovel : repository.findAllByExcluidoEmNull()){
            ImovelResponseDTO imovelResponseDTO = new ImovelResponseDTO(imovel);
            imoveis.add(imovelResponseDTO);
        }
        return imoveis;
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSold(){
        List<ImovelResponseDTO> imoveis = new ArrayList<ImovelResponseDTO>();
        for(Imovel imovel : repository.findAllByExcluidoEmNullAndDataVendaNull()){
            ImovelResponseDTO imovelResponseDTO = new ImovelResponseDTO(imovel);
            imoveis.add(imovelResponseDTO);
        }
        return imoveis;
    }
    public List<ImovelResponseDTO> getAllActiveAndSold(){
        List<ImovelResponseDTO> imoveis = new ArrayList<ImovelResponseDTO>();
        for(Imovel imovel : repository.findAllByExcluidoEmNullAndDataVendaNotNull()){
            ImovelResponseDTO imovelResponseDTO = new ImovelResponseDTO(imovel);
            imoveis.add(imovelResponseDTO);
        }
        return imoveis;
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByTipo(Tipo tipo){
        List<ImovelResponseDTO> imoveis = new ArrayList<ImovelResponseDTO>();
        for(Imovel imovel : repository.findAllByTipoAndExcluidoEmNullAndDataVendaNull(tipo)){
            ImovelResponseDTO imovelResponseDTO = new ImovelResponseDTO(imovel);
            imoveis.add(imovelResponseDTO);
        }
        return imoveis;
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByCaracteristica(Caracteristica caracteristica){
        List<ImovelResponseDTO> imoveis = new ArrayList<ImovelResponseDTO>();
        for(Imovel imovel : repository.findAllByCaracteristicasAndExcluidoEmNullAndDataVendaNull(caracteristica)){
            ImovelResponseDTO imovelResponseDTO = new ImovelResponseDTO(imovel);
            imoveis.add(imovelResponseDTO);
        }
        return imoveis;
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByBairro(String bairro){
        List<ImovelResponseDTO> imoveis = new ArrayList<ImovelResponseDTO>();
        for(Imovel imovel : repository.findAllByEnderecoId_BairroLikeIgnoreCaseAndExcluidoEmNullAndDataVendaNull(bairro)){
            ImovelResponseDTO imovelResponseDTO = new ImovelResponseDTO(imovel);
            imoveis.add(imovelResponseDTO);
        }
        return imoveis;
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByMetragemGreaterThanOrEqual(double metragem){
        List<ImovelResponseDTO> imoveis = new ArrayList<ImovelResponseDTO>();
        for(Imovel imovel : repository.findAllByMetragemGreaterThanEqualAndExcluidoEmNullAndDataVendaNull(metragem)){
            ImovelResponseDTO imovelResponseDTO = new ImovelResponseDTO(imovel);
            imoveis.add(imovelResponseDTO);
        }
        return imoveis;
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByValueBetween(double valorInicial, double valorFinal){
        List<ImovelResponseDTO> imoveis = new ArrayList<ImovelResponseDTO>();
        for(Imovel imovel : repository.findAllByValorBetweenAndExcluidoEmNullAndDataVendaNotNull(valorInicial, valorFinal)){
            ImovelResponseDTO imovelResponseDTO = new ImovelResponseDTO(imovel);
            imoveis.add(imovelResponseDTO);
        }
        return imoveis;
    }
    public void save(ImovelRequestDTO data){
        if(data.matricula() != null){
            Optional<Imovel> optionalImovel = repository.findByMatricula(data.matricula());
            if (optionalImovel.isPresent()) {
                throw new RuntimeException("Matrícula de imóvel já cadastrada");
            }
        }
        Imovel imovel = new Imovel(data);
        repository.save(imovel);
        return;
    }
    public ImovelResponseDTO update(ImovelRequestDTO data){
        Optional<Imovel> optionalImovel = repository.findById(data.id());
        if (optionalImovel.isPresent()) {
            Imovel imovel = optionalImovel.get();
            imovel.setCorretores(data.corretores());
            imovel.setEndereco(data.endereco());
            imovel.setTipo(data.tipo());
            if (data.caracteristicas() != null) {
                imovel.setCaracteristicas(data.caracteristicas());
            }
            imovel.setProprietario(data.proprietario());
            imovel.setMetragem(data.metragem());
            imovel.setValor(data.valor());
            if(data.dataVenda() != null) {
                imovel.setDataVenda(data.dataVenda());
            }
            imovel.setDescricao(data.descricao());
            if(data.matricula() != null) {
                imovel.setMatricula(data.matricula());
            }
            repository.save(imovel);
            return new ImovelResponseDTO(imovel);
        }
        return null;
    }
    public boolean delete(UUID id){
        Optional<Imovel> optionalImovel = repository.findById(id);
        if (optionalImovel.isPresent()) {
            Imovel imovel = optionalImovel.get();
            imovel.setExcluidoEm(OffsetDateTime.now());
            repository.save(imovel);
            return true;
        }
        return false;
    }
}
