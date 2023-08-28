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
import sisgerim.backend.domain.endereco.Endereco;
import sisgerim.backend.domain.endereco.EnderecoRequestDTO;
import sisgerim.backend.domain.endereco.EnderecoResponseDTO;
import sisgerim.backend.domain.endereco.EnderecoService;

@RestController
@RequestMapping("/api/endereco")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {
    @Autowired
    EnderecoService service;
    @GetMapping
    public List<EnderecoResponseDTO> getEnderecos(){
        return service.getAll();
    }
    @PostMapping
    public ResponseEntity<String> saveEndereco(@RequestBody @Valid EnderecoRequestDTO data){
        service.save(data);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Endereco> updateEndereco(@RequestBody @Valid EnderecoRequestDTO data){
        Endereco endereco = service.update(data);
        if (endereco != null) {
            return ResponseEntity.ok(endereco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEndereco(@PathVariable("id") UUID id){
        if(service.delete(id)){
            return ResponseEntity.ok("Deleted");
        } else {
            return new ResponseEntity<String>("Invalid ID", HttpStatus.NOT_FOUND);
        }
    }
}
