package sisgerim.backend.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.caracteristica.CaracteristicaListRequest;
import sisgerim.backend.domain.foto.Foto;
import sisgerim.backend.domain.foto.FotoRequestDTO;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.imovel.ImovelRequestDTO;
import sisgerim.backend.domain.imovel.ImovelResponseDTO;
import sisgerim.backend.domain.pessoa.corretor.Corretor;
import sisgerim.backend.domain.pessoa.corretor.CorretorRequestDTO;
import sisgerim.backend.domain.tipo.Tipo;
import sisgerim.backend.repositories.ImovelRepository;

@Service
public class ImovelService {
    @Autowired
    private ImovelRepository repository;
    @Autowired
    private CorretorService corretorService;
    @Autowired
    private FotoService fotoService;

    public Imovel getImovelById(UUID id){
        Optional<Imovel> optionalImovel = repository.findById(id);
        if(optionalImovel.isPresent()){
            Imovel imovel = optionalImovel.get();
            return imovel;
        } else {
            return null;
        }
    }
    public List<ImovelResponseDTO> getAllActiveAndByCorretorId(UUID corretorId){
        return repository.findAllByCorretores_IdAndExcluidoEmNull(corretorId).stream().map(ImovelResponseDTO::new).toList();
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldAndByCorretorId(UUID corretorId){
        return repository.findAllByCorretores_IdAndExcluidoEmNullAndDataVendaNull(corretorId).stream().map(ImovelResponseDTO::new).toList();
    }
    public List<ImovelResponseDTO> getAllActiveAndSoldAndByCorretorId(UUID corretorId){
        return repository.findAllByCorretores_IdAndExcluidoEmNullAndDataVendaNotNull(corretorId).stream().map(ImovelResponseDTO::new).toList();
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByTipoAndByCorretorId(UUID corretorId, Tipo tipo){
        return repository.findAllByCorretores_IdAndTipoAndExcluidoEmNullAndDataVendaNull(corretorId, tipo).stream().map(ImovelResponseDTO::new).toList();
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByCaracteristicaAndByCorretorId(UUID corretorId, CaracteristicaListRequest caracteristicas){
        List<Caracteristica> caracteristicasList = caracteristicas.caracteristicas();
        List<ImovelResponseDTO> imoveis = getAllActiveAndNotSoldAndByCorretorId(corretorId);
        return imoveis.stream().filter(imovel -> imovel.caracteristicas().containsAll(caracteristicasList)).toList();
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByBairroAndByCorretorId(UUID corretorId, String bairro){
        return repository.findAllByCorretores_IdAndEnderecoId_BairroLikeIgnoreCaseAndExcluidoEmNullAndDataVendaNull(corretorId, bairro).stream().map(ImovelResponseDTO::new).toList();
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByMetragemGreaterThanOrEqualAndByCorretorId(UUID corretorId, double metragem){
        return repository.findAllByCorretores_IdAndMetragemGreaterThanEqualAndExcluidoEmNullAndDataVendaNull(corretorId, metragem).stream().map(ImovelResponseDTO::new).toList();
    }
    public List<ImovelResponseDTO> getAllActiveAndNotSoldByValueBetweenAndByCorretorId(UUID corretorId, double valorInicial, double valorFinal){
        return repository.findAllByCorretores_IdAndValorBetweenAndExcluidoEmNullAndDataVendaNull(corretorId, valorInicial, valorFinal).stream().map(ImovelResponseDTO::new).toList();
    }
    public Imovel save(ImovelRequestDTO data){
        List<Imovel> imoveis = repository.findAllByExcluidoEmNull();
        if(imoveis.size() > 0){
            Optional<Imovel> optionalImovel = imoveis.stream().filter(imovel -> (imovel.getMatricula() != null && imovel.getMatricula().equals(data.matricula())) || imovel.getEndereco().equals(data.endereco())).findFirst();
            if (optionalImovel.isPresent()) {
                Imovel imovel = optionalImovel.get();
                return imovel;
            }
        }
        Imovel imovel = new Imovel(data);
        repository.save(imovel);
        return imovel;
    }
    public ImovelResponseDTO update(ImovelRequestDTO data){
        Imovel imovel = getImovelById(data.id());
        if(imovel != null){
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
        Imovel imovel = getImovelById(id);
        if(imovel != null){
            imovel.setExcluidoEm(OffsetDateTime.now());
            repository.save(imovel);
            return true;
        }
        return false;
    }
    public void addToCorretor(Imovel imovel, UUID corretorId){
        Corretor corretor = corretorService.getCorretorById(corretorId);
        corretor.getImoveis().add(imovel);
        CorretorRequestDTO corretorRequestDTO = new CorretorRequestDTO(
            corretor.getId(),
            corretor.getParceiros(),
            corretor.getEndereco(),
            corretor.getNome(),
            corretor.getEmail(),
            corretor.getTelefone(),
            corretor.getCpf(),
            corretor.getCreci(),
            corretor.getImobiliaria(),
            corretor.getSenha(),
            corretor.getRedesSociais(),
            corretor.getClientes(),
            corretor.getImoveis(),
            corretor.getExcluidoEm()
        );
        corretorService.update(corretorRequestDTO);
    }
    public void addToFotos(Imovel imovel, List<Foto> fotos){
        for (Foto foto : fotos.stream().map(foto -> fotoService.getFotoById(foto.getId())).toList()) {
            foto.setImovel(imovel);
            FotoRequestDTO fotoRequestDTO = new FotoRequestDTO(foto.getId(), foto.getImovel(), foto.getCaminho());
            fotoService.update(fotoRequestDTO);
        }
    }
}
