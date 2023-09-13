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
import sisgerim.backend.domain.imovel.ImovelRequestDTO;
import sisgerim.backend.domain.imovel.ImovelResponseDTO;
import sisgerim.backend.domain.imovel.ImovelService;
import sisgerim.backend.domain.tipo.Tipo;

@RestController
@RequestMapping("/api/imovel")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImovelController {
    @Autowired
    ImovelService service;
    @GetMapping
    public List<ImovelResponseDTO> getImoveisAtivos(){
        return service.getAllActive();
    }
    @GetMapping("/naovendido")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidos(){
        return service.getAllActiveAndNotSold();
    }
    @GetMapping("/vendido")
    public List<ImovelResponseDTO> getImoveisAtivosVendidos(){
        return service.getAllActiveAndSold();
    }
    @GetMapping("/tipo")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorTipo(@RequestBody @Valid Tipo tipo){
        return service.getAllActiveAndNotSoldByTipo(tipo);
    }
    @GetMapping("/caracteristica")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorCaracteristica(@RequestBody @Valid Caracteristica caracteristica){
        return service.getAllActiveAndNotSoldByCaracteristica(caracteristica);
    }
    @GetMapping("/bairro/{bairro}")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorBairro(@PathVariable("bairro") String bairro){
        return service.getAllActiveAndNotSoldByBairro(bairro);
    }
    @GetMapping("/metragem/{metragem}")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorMetragemMaiorOuIgual(@PathVariable("metragem") double metragem){
        return service.getAllActiveAndNotSoldByMetragemGreaterThanOrEqual(metragem);
    }
    @GetMapping("/valor")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorValorEntre(@RequestBody @Valid double valorInicial, double valorFinal){
        return service.getAllActiveAndNotSoldByValueBetween(valorInicial, valorFinal);
    }
    @PostMapping
    public ResponseEntity<String> saveImovel(@RequestBody @Valid ImovelRequestDTO data){
        service.save(data);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ImovelResponseDTO> updateImovel(@RequestBody @Valid ImovelRequestDTO data){
        ImovelResponseDTO imovelResponseDTO = service.update(data); 
        if (imovelResponseDTO != null) {
            return ResponseEntity.ok(imovelResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImovel(@PathVariable("id") UUID id){
        if(service.delete(id)){
            return ResponseEntity.ok("Deleted");
        } else {
            return new ResponseEntity<String>("Invalid ID", HttpStatus.NOT_FOUND);
        }
    }
}
