package sisgerim.backend.domain.foto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisgerim.backend.repositories.FotoRepository;

@Service
public class FotoService {
    @Autowired
    FotoRepository repository;

    public List<FotoResponseDTO> getAll(){
        return repository.findAll().stream().map(FotoResponseDTO::new).toList();
    }
    public List<FotoResponseDTO> getAllByCaminho(String caminho){
        return repository.findByCaminhoLikeIgnoreCase(caminho).stream().map(FotoResponseDTO::new).toList();
    }
    public Foto getFotoById(UUID fotoId){
        Optional<Foto> optional = repository.findById(fotoId);
        if(optional.isPresent()){
            Foto foto = optional.get();
            return foto;
        }
        return null;
    }
    public void save(FotoRequestDTO data){
        Foto foto = new Foto(data);
        repository.save(foto);
        return;
    }
    public FotoResponseDTO update(FotoRequestDTO data){
        Optional<Foto> optional = repository.findById(data.id());
        if(optional.isPresent()){
            Foto foto = optional.get();
            foto.setImovel(data.imovel());
            foto.setCaminho(data.caminho());
            repository.save(foto);
            return new FotoResponseDTO(foto);
        }
        return null;
    }
    public boolean delete(UUID id){
        Optional<Foto> optional = repository.findById(id);
        if(optional.isPresent()){
            Foto foto = optional.get();
            repository.delete(foto);
            return true;
        }
        return false;
    }
}
