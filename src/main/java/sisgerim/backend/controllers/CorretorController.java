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
import sisgerim.backend.domain.pessoa.corretor.CorretorRequestDTO;
import sisgerim.backend.domain.pessoa.corretor.CorretorResponseDTO;
import sisgerim.backend.domain.pessoa.corretor.CorretorService;

@RestController
@RequestMapping("/api/corretor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CorretorController {
    @Autowired
    CorretorService service;
    @GetMapping("/{id}")
    public List<CorretorResponseDTO> getCorretoresAtivos(@PathVariable("id") UUID corretorId){
        return service.getAllParceirosActive(corretorId); 
    }
    // @GetMapping("/email")
    // public ResponseEntity<CorretorResponseDTO> getCorretorParceiroAtivoPorEmail(@RequestBody @NotBlank String email){
    //     CorretorResponseDTO parceiro = service.getParceiroActiveByEmail(email);
    //     if(parceiro != null){
    //         return ResponseEntity.ok(parceiro);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
    @PostMapping("/parceiro/{id}")
    public ResponseEntity<String> saveParceiro(@PathVariable("id") UUID corretorId, @RequestBody @NotBlank String email){
        try {
            CorretorResponseDTO corretorResponseDTO = service.saveParceiroByEmail(corretorId, email);
            if(corretorResponseDTO != null){
                return new ResponseEntity<String>("Created", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("Invalid ID", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PostMapping
    public ResponseEntity<String> saveCorretor(@RequestBody @Valid CorretorRequestDTO data){
        try {
            service.save(data);
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PutMapping
    public ResponseEntity<CorretorResponseDTO> updateCorretor(@RequestBody @Valid CorretorRequestDTO data){
        try {
            CorretorResponseDTO corretor = service.update(data);
            if (corretor != null) {
                return ResponseEntity.ok(corretor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
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
