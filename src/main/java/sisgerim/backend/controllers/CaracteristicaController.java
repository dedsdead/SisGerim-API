package sisgerim.backend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.caracteristica.CaracteristicaRequestDTO;
import sisgerim.backend.domain.caracteristica.CaracteristicaResponseDTO;
import sisgerim.backend.domain.caracteristica.CaracteristicaService;

@RestController
@RequestMapping("caracteristica")
public class CaracteristicaController {
    @Autowired
    CaracteristicaService service;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<CaracteristicaResponseDTO> getCaracteristicas(){
        return service.getAll();
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> saveCaracteristica(@RequestBody @Valid CaracteristicaRequestDTO data){
        service.save(data);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    public ResponseEntity<Caracteristica> updateCaracteristica(@RequestBody @Valid CaracteristicaRequestDTO data){
        Caracteristica caracteristica = service.update(data);
        if (caracteristica != null) {
            return ResponseEntity.ok(caracteristica);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCaracteristica(@PathVariable("id") UUID id){
        if (service.delete(id)) {
            return ResponseEntity.ok("Deleted");
        }
        return new ResponseEntity<String>("Invalid ID", HttpStatus.NOT_FOUND);
    }
}