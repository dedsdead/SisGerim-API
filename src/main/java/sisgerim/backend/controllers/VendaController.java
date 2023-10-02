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
import sisgerim.backend.domain.venda.VendaRequestDTO;
import sisgerim.backend.domain.venda.VendaResponseDTO;
import sisgerim.backend.domain.venda.VendaService;

@RestController
@RequestMapping("/api/venda")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VendaController {
    @Autowired
    VendaService service;

    @GetMapping("{id}")
    public List<VendaResponseDTO> getVendasAtivasPorCorretor(@PathVariable("id") UUID corretorId){
        try {
            return service.getAllActiveAndByCorretorId(corretorId);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/status/{id}")
    public List<VendaResponseDTO> getVendasAtivasPorCorretorEStatus(@PathVariable("id") UUID corretorId, @RequestParam("status") String status){
        try {
            return service.getAllActiveAndByCorretorIdAndStatus(corretorId, status);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PostMapping
    public ResponseEntity<String> saveVenda(@RequestBody @Valid VendaRequestDTO data){
        try {
            service.save(data);
            service.setImovelDataVenda(data.imovel().getId());
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PutMapping
    public ResponseEntity<Object> updateVenda(@RequestBody @Valid VendaRequestDTO data){
        try {
            VendaResponseDTO vendaResponseDTO = service.update(data);
            if(vendaResponseDTO != null){
                return ResponseEntity.ok(vendaResponseDTO);
            } else {
                return new ResponseEntity<Object>("Invalid ID", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> saveVenda(@PathVariable("id") UUID id){
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
