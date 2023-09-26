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
import sisgerim.backend.domain.caracteristica.CaracteristicaRequestDTO;
import sisgerim.backend.domain.caracteristica.CaracteristicaResponseDTO;
import sisgerim.backend.domain.caracteristica.CaracteristicaService;

@RestController
@RequestMapping("/api/caracteristica")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CaracteristicaController {
    @Autowired
    CaracteristicaService service;
    @GetMapping
    public List<CaracteristicaResponseDTO> getCaracteristicas(){
        return service.getAll();
    }
    @PostMapping
    public ResponseEntity<String> saveCaracteristica(@RequestBody @Valid CaracteristicaRequestDTO data){
        try {
            service.save(data);
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PutMapping
    public ResponseEntity<CaracteristicaResponseDTO> updateCaracteristica(@RequestBody @Valid CaracteristicaRequestDTO data){
        try {
            CaracteristicaResponseDTO caracteristicaResponseDTO = service.update(data);
            if (caracteristicaResponseDTO != null) {
                return ResponseEntity.ok(caracteristicaResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }   
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCaracteristica(@PathVariable("id") UUID id){
        if (service.delete(id)) {
            return ResponseEntity.ok("Deleted");
        }
        return new ResponseEntity<String>("Invalid ID", HttpStatus.NOT_FOUND);
    }
}
