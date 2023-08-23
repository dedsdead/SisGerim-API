package sisgerim.backend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import sisgerim.backend.domain.dtos.TipoRequestDTO;
import sisgerim.backend.domain.dtos.TipoResponseDTO;
import sisgerim.backend.domain.tipo.Tipo;
import sisgerim.backend.repositories.TipoRepository;
import sisgerim.backend.services.TipoService;

@RestController
@RequestMapping("tipo")
public class TipoController {
    @Autowired
    private TipoRepository repository;
    @Autowired
    private TipoService service;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<TipoResponseDTO> getTipos(){
        List<TipoResponseDTO> tipos = repository.findAll().stream().map(TipoResponseDTO::new).toList();
        return tipos;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void salvarTipo(@RequestBody TipoRequestDTO data){
        Tipo tipo = new Tipo(data);
        repository.save(tipo);
        return;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public Tipo atualizarTipo(@RequestBody TipoRequestDTO data, @PathVariable("id") UUID id){
        try {
            Tipo tipo = service.findTipoById(id);
            tipo.setNome(data.nome().toUpperCase());
            repository.save(tipo);
            return tipo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deletarTipo(@PathVariable("id") UUID id){
        repository.deleteById(id);
        return;
    }
}
