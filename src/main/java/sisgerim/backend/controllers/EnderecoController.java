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
        try {
            return service.getAll();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/{cep}")
    public List<EnderecoResponseDTO> getEnderecosPorCep(@PathVariable("cep") String cep){
        try {
            return service.getAllByCep(cep);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/{localidade}")
    public List<EnderecoResponseDTO> getEnderecosPorLocalidade(@PathVariable("localidade") String localidade){
        try {
            return service.getAllByLocalidade(localidade);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/{bairro}")
    public List<EnderecoResponseDTO> getEnderecosPorBairro(@PathVariable("bairro") String bairro){
        try {
            return service.getAllByBairro(bairro);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PostMapping
    public ResponseEntity<String> saveEndereco(@RequestBody @Valid EnderecoRequestDTO data){
        try {
            service.save(data);
            return new ResponseEntity<String>("Created", HttpStatus.CREATED); 
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PutMapping
    public ResponseEntity<EnderecoResponseDTO> updateEndereco(@RequestBody @Valid EnderecoRequestDTO data){
        try {
            EnderecoResponseDTO enderecoResponseDTO = service.update(data);
            if (enderecoResponseDTO != null) {
                return ResponseEntity.ok(enderecoResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEndereco(@PathVariable("id") UUID id){
        try {
            if(service.delete(id)){
                return ResponseEntity.ok("Deleted");
            } else {
                return new ResponseEntity<String>("Invalid ID", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
