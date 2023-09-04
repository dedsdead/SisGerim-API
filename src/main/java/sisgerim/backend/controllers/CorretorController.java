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
import sisgerim.backend.domain.pessoa.corretor.Corretor;
import sisgerim.backend.domain.pessoa.corretor.CorretorRequestDTO;
import sisgerim.backend.domain.pessoa.corretor.CorretorResponseDTO;
import sisgerim.backend.domain.pessoa.corretor.CorretorService;

@RestController
@RequestMapping("/api/corretor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CorretorController {
    @Autowired
    CorretorService service;
    @GetMapping
    public List<CorretorResponseDTO> getCorretoresAtivos(){
        return service.getAllActive();
    }
    @PostMapping
    public ResponseEntity<String> saveCorretor(@RequestBody @Valid CorretorRequestDTO data){
        service.save(data);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Corretor> updateCorretor(@RequestBody @Valid CorretorRequestDTO data){
        Corretor corretor = service.update(data);
        if (corretor != null) {
            return ResponseEntity.ok(corretor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCorretor(@PathVariable("id") UUID id){
        if(service.delete(id)){
            return ResponseEntity.ok("Deleted");
        } else {
            return new ResponseEntity<String>("Invalid ID", HttpStatus.NOT_FOUND);
        }
    }
}
