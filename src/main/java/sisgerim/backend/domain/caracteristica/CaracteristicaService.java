package sisgerim.backend.domain.caracteristica;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisgerim.backend.repositories.CaracteristicaRepository;

@Service
public class CaracteristicaService {
    @Autowired
    private CaracteristicaRepository repository;
    public List<CaracteristicaResponseDTO> getAll(){
        return repository.findAll().stream().map(CaracteristicaResponseDTO::new).toList();
    }
    public void save(CaracteristicaRequestDTO data){
        Caracteristica caracteristica = new Caracteristica(data);
        repository.save(caracteristica);
        return;
    }
    public CaracteristicaResponseDTO update(CaracteristicaRequestDTO data){
        Optional<Caracteristica> optional = repository.findById(data.id());
        if (optional.isPresent()) {
            Caracteristica caracteristica = optional.get();
            if (data.quantidade() != 0) {
                caracteristica.setQuantidade(data.quantidade());
            }
            caracteristica.setDescricao(data.descricao().toUpperCase());
            repository.save(caracteristica);
            return new CaracteristicaResponseDTO(caracteristica); 
        }
        return null;
    }
    public boolean delete(UUID id){
        Optional<Caracteristica> optional = repository.findById(id);
        if (optional.isPresent()) {
            Caracteristica caracteristica = optional.get();
            repository.delete(caracteristica);
            return true;
        }
        return false;
    }
}
