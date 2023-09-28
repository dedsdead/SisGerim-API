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
import sisgerim.backend.domain.caracteristica.CaracteristicaListRequest;
import sisgerim.backend.domain.imovel.Imovel;
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

    @GetMapping("{id}")
    public List<ImovelResponseDTO> getImoveisAtivosPorCorretor(@PathVariable("id") UUID corretorId){
        try {
            return service.getAllActiveAndByCorretorId(corretorId);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/naovendido/{id}")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorCorretor(@PathVariable("id") UUID corretorId){
        try {
            return service.getAllActiveAndNotSoldAndByCorretorId(corretorId);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/vendido/{id}")
    public List<ImovelResponseDTO> getImoveisAtivosVendidosPorCorretor(@PathVariable("id") UUID corretorId){
        try {
            return service.getAllActiveAndSoldAndByCorretorId(corretorId);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/tipo/{id}")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorCorretorEPorTipo(@PathVariable("id") UUID corretorId, @RequestBody @Valid Tipo tipo){
        try {
            return service.getAllActiveAndNotSoldByTipoAndByCorretorId(corretorId, tipo);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/caracteristica/{id}")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorCorretorEPorCaracteristica(@PathVariable("id") UUID corretorId, @RequestBody @Valid CaracteristicaListRequest caracteristicas){
        try {
            return service.getAllActiveAndNotSoldByCaracteristicaAndByCorretorId(corretorId, caracteristicas);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/bairro/{id}")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorCorretorEPorBairro(@PathVariable("id") UUID corretorId, @RequestParam("bairro") String bairro){
        try {
            return service.getAllActiveAndNotSoldByBairroAndByCorretorId(corretorId, bairro);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/metragem/{id}")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorCorretorEPorMetragemMaiorOuIgual(@PathVariable("id") UUID corretorId, @RequestParam("metragem") double metragem){
        try {
            return service.getAllActiveAndNotSoldByMetragemGreaterThanOrEqualAndByCorretorId(corretorId, metragem);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @GetMapping("/valor/{id}")
    public List<ImovelResponseDTO> getImoveisAtivosNaoVendidosPorCorretorEPorValorEntre(@PathVariable("id") UUID corretorId, @RequestParam("valorInicial") double valorInicial, @RequestParam("valorFinal") double valorFinal){
        try {
            return service.getAllActiveAndNotSoldByValueBetweenAndByCorretorId(corretorId, valorInicial, valorFinal);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PostMapping("/{id}")
    public ResponseEntity<String> saveImovel(@PathVariable("id") UUID corretorId, @RequestBody @Valid ImovelRequestDTO data){
        try {
            Imovel imovel = service.save(data);
            service.addToCorretor(imovel, corretorId);
            service.addToFotos(imovel, data.fotos());
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @PutMapping
    public ResponseEntity<ImovelResponseDTO> updateImovel(@RequestBody @Valid ImovelRequestDTO data){
        try {
            ImovelResponseDTO imovelResponseDTO = service.update(data); 
            if (imovelResponseDTO != null) {
                return ResponseEntity.ok(imovelResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImovel(@PathVariable("id") UUID id){
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
