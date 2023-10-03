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
import sisgerim.backend.domain.tipo.TipoRequestDTO;
import sisgerim.backend.domain.tipo.TipoResponseDTO;
import sisgerim.backend.services.TipoService;

@RestController
@RequestMapping("/api/tipo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TipoController {
    @Autowired
    private TipoService service;
    
    @GetMapping
    public List<TipoResponseDTO> getTipos(){
        try {
            return service.getAll();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PostMapping
    public ResponseEntity<String> saveTipo(@RequestBody @Valid TipoRequestDTO data){
        try {
            service.save(data);
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PutMapping
    public ResponseEntity<TipoResponseDTO> updateTipo(@RequestBody @Valid TipoRequestDTO data){
        try {
            TipoResponseDTO tipoResponseDTO = service.update(data);
            if (tipoResponseDTO != null) {
                return ResponseEntity.ok(tipoResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTipo(@PathVariable("id") UUID id){
        try {
            if (service.delete(id)) {
                return ResponseEntity.ok("Deleted");
            }
            return new ResponseEntity<String>("Invalid ID", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
