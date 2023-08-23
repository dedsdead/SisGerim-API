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
import sisgerim.backend.domain.tipo.Tipo;
import sisgerim.backend.domain.tipo.TipoRequestDTO;
import sisgerim.backend.domain.tipo.TipoResponseDTO;
import sisgerim.backend.domain.tipo.TipoService;

@RestController
@RequestMapping("tipo")
public class TipoController {
    @Autowired
    private TipoService service;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<TipoResponseDTO> getTipos(){
        return service.getAll();
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> saveTipo(@RequestBody @Valid TipoRequestDTO data){
        service.save(data);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping()
    public ResponseEntity<Tipo> updateTipo(@RequestBody @Valid TipoRequestDTO data){
        Tipo tipo = service.update(data);
        if (tipo != null) {
            return ResponseEntity.ok(tipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTipo(@PathVariable("id") UUID id){
        service.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
