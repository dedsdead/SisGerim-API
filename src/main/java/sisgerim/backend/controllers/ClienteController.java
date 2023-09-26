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
import jakarta.validation.constraints.NotBlank;
import sisgerim.backend.domain.caracteristica.CaracteristicaListRequest;
import sisgerim.backend.domain.pessoa.cliente.Cliente;
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
    @GetMapping("/{id}")
    public List<ClienteResponseDTO> getClientesAtivos(@PathVariable("id") UUID corretorId){
        return service.getAllActiveAndByCorretorId(corretorId);
    }
    @GetMapping("/tipo/{id}")
    public List<ClienteResponseDTO> getClientesAtivosPorTipo(@PathVariable("id") UUID corretorId, @RequestBody @Valid Tipo tipo){
        try {
            return service.getAllActiveAndByCorretorIdAndByTipo(corretorId, tipo);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/caracteristica/{id}")
    public List<ClienteResponseDTO> getClientesAtivosPorCaracteristicas(@PathVariable("id") UUID corretorId, @RequestBody @Valid CaracteristicaListRequest caracteristicas){
        try {
            return service.getAllActiveAndByCorretorIdAndByCaracteristicas(corretorId, caracteristicas);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/bairro/{id}")
    public List<ClienteResponseDTO> getClientesAtivosPorBairro(@PathVariable("id") UUID corretorId, @RequestBody @NotBlank String bairro){
        try {
            return service.getAllActiveAndByCorretorIdAndByBairro(corretorId, bairro);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }  
    }
    @PostMapping
    public ResponseEntity<String> saveCliente(@RequestBody @Valid ClienteRequestDTO data){
        try {
            Cliente cliente = service.save(data);
            service.addToCorretor(cliente, data.corretor().getId());
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PutMapping
    public ResponseEntity<ClienteResponseDTO> updateCliente(@RequestBody @Valid ClienteRequestDTO data){
        try {
            ClienteResponseDTO clienteResponseDTO = service.update(data); 
            if (clienteResponseDTO != null) {
                return ResponseEntity.ok(clienteResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
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
