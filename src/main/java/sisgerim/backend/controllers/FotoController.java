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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import sisgerim.backend.domain.foto.FotoRequestDTO;
import sisgerim.backend.domain.foto.FotoResponseDTO;
import sisgerim.backend.domain.foto.FotoService;

@RestController
@RequestMapping("/api/foto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FotoController {
    @Autowired
    FotoService service;

    @GetMapping
    public List<FotoResponseDTO> getFotos(){
        try {
            return service.getAll();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/caminho")
    public List<FotoResponseDTO> getFotosByCaminho(@RequestParam("caminho") String caminho){
        try {
            return service.getAllByCaminho(caminho);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PostMapping
    public ResponseEntity<String> saveFoto(@RequestBody @Valid FotoRequestDTO data){
        try {
            service.save(data);
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PutMapping
    public ResponseEntity<FotoResponseDTO> updateFoto(@RequestBody @Valid FotoRequestDTO data){
        try {
            FotoResponseDTO fotoResponseDTO = service.update(data);
            if (fotoResponseDTO != null) {
                return ResponseEntity.ok(fotoResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }   
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoto(@PathVariable("id") UUID id){
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
