package sisgerim.backend.domain.venda;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisgerim.backend.domain.imovel.Imovel;
import sisgerim.backend.domain.imovel.ImovelRequestDTO;
import sisgerim.backend.domain.imovel.ImovelService;
import sisgerim.backend.repositories.VendaRepository;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;
    @Autowired
    private ImovelService imovelService;

    public Venda getVendaById(UUID id){
        Optional<Venda> optionalVenda = repository.findById(id);
        if(optionalVenda.isPresent()){
            Venda venda = optionalVenda.get();
            return venda;
        } else {
            return null;
        }
    }
    public List<VendaResponseDTO> getAllActiveAndByCorretorId(UUID corretorId){
        return repository
            .findAllByCorretorIdAndExcluidaEmNull(corretorId)
            .stream()
            .map(VendaResponseDTO::new)
            .toList();
    }
    public List<VendaResponseDTO> getAllActiveAndByCorretorIdAndStatus(UUID corretorId, String status){
        return repository
            .findAllByCorretorIdAndStatusLikeIgnoreCaseAndExcluidaEmNull(corretorId, status)
            .stream()
            .map(VendaResponseDTO::new)
            .toList();
    }
    public void save(VendaRequestDTO data){
        Venda venda = new Venda(data);
        repository.save(venda);
        return;
    }
    public VendaResponseDTO update(VendaRequestDTO data){
        if (data.id() == null) {
            return null;
        }
        Venda venda = getVendaById(data.id());
        if (venda != null) {
            if (!venda.getImovel().equals(data.imovel())) {
                if (venda.getImovel().getDataVenda() != null) {
                    resetImovelDataVenda(venda.getImovel().getId(), data.imovel().getId(), venda.getImovel().getDataVenda());
                } else {
                    setImovelDataVenda(data.imovel().getId());
                }
            }
            venda.setImovel(data.imovel());
            if(data.comprador() != null){
                venda.setComprador(data.comprador());
            }
            if (data.parceiro() != null) {
                venda.setParceiro(data.parceiro());
            }
            venda.setValor(data.valor());
            venda.setStatus(data.status());
            venda.setDescricao(data.descricao());
            repository.save(venda);
            return new VendaResponseDTO(venda);
        }
        return null;
    }
    public boolean delete(UUID id){
        Venda venda = getVendaById(id);
        if(venda != null){
            venda.setExcluidaEm(OffsetDateTime.now());
            repository.save(venda);
            return true;
        }
        return false;
    }
    public void setImovelDataVenda(UUID imovelId){
        Imovel imovel = imovelService.getImovelById(imovelId);
        imovel.setDataVenda(LocalDate.now());
        ImovelRequestDTO imovelRequestDTO = new ImovelRequestDTO(
            imovel.getId(), 
            imovel.getEndereco(), 
            imovel.getTipo(), 
            imovel.getCaracteristicas(), 
            imovel.getProprietario(), 
            imovel.getMetragem(), 
            imovel.getValor(), 
            imovel.getDataVenda(), 
            imovel.getDescricao(), 
            imovel.getMatricula(), 
            imovel.getFotos(), 
            imovel.getExcluidoEm()
        );
        imovelService.save(imovelRequestDTO);
    }
    public void resetImovelDataVenda(UUID oldImovelId, UUID imovelId, LocalDate date){
        Imovel oldImovel = imovelService.getImovelById(oldImovelId);
        oldImovel.setDataVenda(null);
        ImovelRequestDTO oldImovelRequestDTO = new ImovelRequestDTO(
            oldImovel.getId(), 
            oldImovel.getEndereco(), 
            oldImovel.getTipo(), 
            oldImovel.getCaracteristicas(), 
            oldImovel.getProprietario(), 
            oldImovel.getMetragem(), 
            oldImovel.getValor(), 
            oldImovel.getDataVenda(), 
            oldImovel.getDescricao(), 
            oldImovel.getMatricula(), 
            oldImovel.getFotos(), 
            oldImovel.getExcluidoEm()
        );
        imovelService.save(oldImovelRequestDTO);

        Imovel imovel = imovelService.getImovelById(imovelId);
        imovel.setDataVenda(date);
        ImovelRequestDTO imovelRequestDTO = new ImovelRequestDTO(
            imovel.getId(), 
            imovel.getEndereco(), 
            imovel.getTipo(), 
            imovel.getCaracteristicas(), 
            imovel.getProprietario(), 
            imovel.getMetragem(), 
            imovel.getValor(), 
            imovel.getDataVenda(), 
            imovel.getDescricao(), 
            imovel.getMatricula(), 
            imovel.getFotos(), 
            imovel.getExcluidoEm()
        );
        imovelService.save(imovelRequestDTO);
    }
}
