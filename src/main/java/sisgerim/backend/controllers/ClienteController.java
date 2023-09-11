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
import sisgerim.backend.domain.pessoa.cliente.ClienteRequestDTO;
import sisgerim.backend.domain.pessoa.cliente.ClienteResponseDTO;
import sisgerim.backend.domain.pessoa.cliente.ClienteService;
import sisgerim.backend.domain.tipo.Tipo;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
    @Autowired
    ClienteService service;
    @GetMapping
    public List<ClienteResponseDTO> getClientesAtivos(){
        return service.getAllActive();
    }
    @GetMapping("/tipo")
    public List<ClienteResponseDTO> getClientesAtivosByTipo(@RequestBody @Valid Tipo tipo){
        return service.getAllActiveAndByTipo(tipo);
    }
    @GetMapping("/caracteristica")
    public List<ClienteResponseDTO> getClientesAtivosByCaracteristica(@RequestBody @Valid Caracteristica caracteristica){
        return service.getAllActiveAndByCaracteristica(caracteristica);
    }
    @GetMapping("/{bairro}")
    public List<ClienteResponseDTO> getClientesAtivosByBairro(@PathVariable("bairro") String bairro){
        return service.getAllActiveAndByBairro(bairro);
    }
    @PostMapping
    public ResponseEntity<String> saveCliente(@RequestBody @Valid ClienteRequestDTO data){
        service.save(data);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ClienteResponseDTO> updateCliente(@RequestBody @Valid ClienteRequestDTO data){
        ClienteResponseDTO clienteResponseDTO = service.update(data); 
        if (clienteResponseDTO != null) {
            return ResponseEntity.ok(clienteResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable("id") UUID id){
        if(service.delete(id)){
            return ResponseEntity.ok("Deleted");
        } else {
            return new ResponseEntity<String>("Invalid ID", HttpStatus.NOT_FOUND);
        }
    }
}
