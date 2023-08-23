package sisgerim.backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sisgerim.backend.domain.tipo.Tipo;
import sisgerim.backend.repositories.TipoRepository;

@Service
public class TipoService {
    @Autowired
    private TipoRepository repository;
    public Tipo findTipoById(UUID id) throws Exception {
        return this.repository.findTipoById(id).orElseThrow(() -> new Exception("Couldn't find tipo by id " + id));
    }
}
