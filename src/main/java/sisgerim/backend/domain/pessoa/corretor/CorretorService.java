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
    public List<CorretorResponseDTO> getAllActive(){
        List<CorretorResponseDTO> corretores = new ArrayList<CorretorResponseDTO>();
        for(Corretor corretor : repository.findAllByExcluidoEmNull()){
            CorretorResponseDTO corretorResponseDTO = new CorretorResponseDTO(corretor);
            corretores.add(corretorResponseDTO);
        }
        return corretores;
    }
    public CorretorResponseDTO getParceiroActiveByEmail(String email){
        Optional<Corretor> optionalParceiro = repository.findByExcluidoEmNullAndEmailLikeIgnoreCase(email);
        if (optionalParceiro.isPresent()) {
            CorretorResponseDTO parceiro = new CorretorResponseDTO(optionalParceiro.get());
            return parceiro;
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
            throw new RuntimeException("Email já cadastrado");
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
            if (data.endereco() != null) {
                corretor.setEndereco(data.endereco());
            }
            corretor.setNome(data.nome().toUpperCase());
            corretor.setEmail(data.email());
            corretor.setTelefone(data.telefone());
            if (data.cpf() != null) {
                corretor.setCpf(data.cpf());
            }
            corretor.setCreci(data.creci().toUpperCase()); 
            if (data.imobiliaria() != null) {
                corretor.setImobiliaria(data.imobiliaria().toUpperCase());
            }
            if (data.senha() != null) {
                corretor.setSenha(data.senha());
            }
            if (data.redesSociais() != null && data.redesSociais().size() > 0) {
                corretor.setRedesSociais(data.redesSociais());
            }
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
