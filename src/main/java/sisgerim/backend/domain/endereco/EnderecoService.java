package sisgerim.backend.domain.endereco;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisgerim.backend.repositories.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    public EnderecoRepository repository;
    public List<EnderecoResponseDTO> getAll(){
        return repository.findAll().stream().map(EnderecoResponseDTO::new).toList();
    }
    public List<EnderecoResponseDTO> getAllByCep(String cep){
        return repository.findAllByCepLike(cep);
    }
    public List<EnderecoResponseDTO> getAllByLocalidade(String localidade){
        return repository.findAllByLocalidadeLike(localidade);
    }
    public List<EnderecoResponseDTO> getAllByBairro(String bairro){
        return repository.findAllByBairroLike(bairro);
    }
    public void save(EnderecoRequestDTO data){
        Endereco endereco = new Endereco(data);
        repository.save(endereco);
        return;
    }
    public EnderecoResponseDTO update(EnderecoRequestDTO data){
        Optional<Endereco> optionalEndereco = repository.findById(data.id());
        if(optionalEndereco.isPresent()){
            Endereco endereco = optionalEndereco.get();
            endereco.setCep(data.cep().toUpperCase());
            endereco.setUf(data.uf().toUpperCase());
            endereco.setLocalidade(data.localidade().toUpperCase());
            endereco.setBairro(data.bairro().toUpperCase());
            endereco.setLogradouro(data.logradouro().toUpperCase());
            if (data.numero() != 0) {
                endereco.setNumero(data.numero());
            }
            if (data.complemento() != null) {
                endereco.setComplemento(data.complemento());
            }
            repository.save(endereco);
            return new EnderecoResponseDTO(endereco);
        }
        return null;
    }
    public boolean delete(UUID id){
        Optional<Endereco> optionalEndereco = repository.findById(id);
        if(optionalEndereco.isPresent()){
            Endereco endereco = optionalEndereco.get();
            repository.delete(endereco);
            return true;
        }
        return false;
    }
}
