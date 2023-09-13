package sisgerim.backend.domain.imovel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
