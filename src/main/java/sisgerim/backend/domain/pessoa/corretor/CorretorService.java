package sisgerim.backend.domain.pessoa.corretor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisgerim.backend.repositories.CorretorRepository;

@Service
public class CorretorService {
    @Autowired
    private CorretorRepository repository;
    
    public Corretor getCorretorById(UUID id){
        Optional<Corretor> optionalCorretor = repository.findById(id);
        if(optionalCorretor.isPresent()){
            Corretor corretor = optionalCorretor.get();
            return corretor;
        } else {
            return null;
        }
    }
    public List<CorretorResponseDTO> getAllParceirosActive(UUID idCorretor){
        Optional<Corretor> optionalCorretor = repository.findById(idCorretor);
        if (optionalCorretor.isPresent()) {
            Corretor corretorFound = optionalCorretor.get();
            List<CorretorResponseDTO> parceiros = new ArrayList<CorretorResponseDTO>();
            for(Corretor corretor : corretorFound.getParceiros()){
                CorretorResponseDTO corretorResponseDTO = new CorretorResponseDTO(corretor);
                parceiros.add(corretorResponseDTO);
            }
            return parceiros;
        } 
        return null;
    }
    public Corretor getCorretorActiveByEmail(String email){
        Optional<Corretor> optionalParceiro = repository.findByEmailLikeIgnoreCaseAndExcluidoEmNull(email);
        if (optionalParceiro.isPresent()) {
            Corretor parceiro = optionalParceiro.get();
            System.out.println(parceiro);
            return parceiro;
        }
        return null;
    }
    public CorretorResponseDTO saveParceiroByEmail(UUID corretorId, String email){
        Corretor corretor = getCorretorById(corretorId);
        Corretor parceiro = getCorretorActiveByEmail(email);
        if(corretor != null && parceiro != null) {
            corretor.getParceiros().add(parceiro);
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
            return update(corretorRequestDTO);
        }
        return null;
    }
    public void save(CorretorRequestDTO data){
        if (data.cpf() != null) {
            Optional<Corretor> optionalCpf = repository.findByCpf(data.cpf());
            if (optionalCpf.isPresent()) {
                throw new RuntimeException("CPF já cadastrado");
            }
        }
        Optional<Corretor> optionalEmail = repository.findByEmail(data.email());
        if (optionalEmail.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        Optional<Corretor> optionalCreci = repository.findByCreci(data.creci());
        if (optionalCreci.isPresent()) {
            throw new RuntimeException("CRECI já cadastrado");
        }
        Corretor corretor = new Corretor(data);
        repository.save(corretor);
        return;
    }
    public CorretorResponseDTO update(CorretorRequestDTO data){
        Optional<Corretor> optionalCorretor = repository.findById(data.id());
        if (optionalCorretor.isPresent()) {
            Corretor corretor = optionalCorretor.get();
            if(data.parceiros() != null){
                corretor.setParceiros(data.parceiros());
            }
            if (data.endereco() != null) {
                corretor.setEndereco(data.endereco());
            }
            corretor.setNome(data.nome().toUpperCase());
            corretor.setTelefone(data.telefone());
            if (data.imobiliaria() != null) {
                corretor.setImobiliaria(data.imobiliaria().toUpperCase());
            }
            if (data.redesSociais() != null && data.redesSociais().size() > 0) {
                corretor.setRedesSociais(data.redesSociais());
            }
            corretor.setClientes(data.clientes());
            corretor.setImoveis(data.imoveis());
            repository.save(corretor);
            return new CorretorResponseDTO(corretor);
        }
        return null;
    }
    public boolean delete(UUID id){
        Optional<Corretor> optionalCorretor = repository.findById(id);
        if (optionalCorretor.isPresent()) {
            Corretor corretor = optionalCorretor.get();
            corretor.setExcluidoEm(OffsetDateTime.now());
            repository.save(corretor);
            return true;
        }
        return false;
    }
}
