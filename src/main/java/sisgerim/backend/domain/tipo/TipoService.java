package sisgerim.backend.domain.tipo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisgerim.backend.repositories.TipoRepository;

@Service
public class TipoService {
    @Autowired
    private TipoRepository repository;
    public List<TipoResponseDTO> getAll(){
        return repository.findAll().stream().map(TipoResponseDTO::new).toList();
    }
    public void save(TipoRequestDTO data){
        Tipo tipo = new Tipo(data);
        repository.save(tipo);
        return;
    }
    public Tipo findTipoById(UUID id) throws Exception {
        Optional<Tipo> optional = repository.findTipoById(id);
        if (optional.isPresent()) {
            Tipo tipo = optional.get();
            return tipo;
        }
        return null;
    }
    public Tipo update(TipoRequestDTO data){
        Optional<Tipo> optional = repository.findById(data.id());
        if (optional.isPresent()) {
            Tipo tipo = optional.get();
            tipo.setNome(data.nome().toUpperCase());
            repository.save(tipo);
            return tipo;
        }
        return null;
    }
    public void delete(UUID id){
        repository.deleteById(id);
        return;
    }
}