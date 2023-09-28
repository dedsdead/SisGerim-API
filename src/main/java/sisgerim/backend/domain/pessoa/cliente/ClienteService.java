package sisgerim.backend.domain.pessoa.cliente;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.caracteristica.CaracteristicaListRequest;
import sisgerim.backend.domain.pessoa.corretor.Corretor;
import sisgerim.backend.domain.pessoa.corretor.CorretorRequestDTO;
import sisgerim.backend.domain.pessoa.corretor.CorretorService;
import sisgerim.backend.domain.tipo.Tipo;
import sisgerim.backend.repositories.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private CorretorService corretorService;

    public List<ClienteResponseDTO> getAllActiveAndByCorretorId(UUID corretorId){
        List<ClienteResponseDTO> clientes = new ArrayList<ClienteResponseDTO>();
        for (Cliente cliente : repository.findAllByCorretores_IdAndExcluidoEmNull(corretorId)){
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente);
            clientes.add(clienteResponseDTO);
        }
        return clientes;
    }
    public List<ClienteResponseDTO> getAllActiveAndByCorretorIdAndByTipo(UUID corretorId, Tipo tipo){
        List<ClienteResponseDTO> clientes = new ArrayList<ClienteResponseDTO>();
        for(Cliente cliente : repository.findAllByCorretores_IdAndTipoAndExcluidoEmNull(corretorId, tipo)){
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente);
            clientes.add(clienteResponseDTO);
        }
        return clientes;
    }
    public List<ClienteResponseDTO> getAllActiveAndByCorretorIdAndByCaracteristicas(UUID corretorId, CaracteristicaListRequest caracteristicas){
        List<Caracteristica> caracteristicasList = caracteristicas.caracteristicas();
        List<ClienteResponseDTO> clientes = new ArrayList<ClienteResponseDTO>();
        for(Cliente cliente : repository.findAllByCorretores_IdAndCaracteristicasInAndExcluidoEmNull(corretorId, caracteristicasList)){
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente);
            clientes.add(clienteResponseDTO);
        }
        return clientes;
    }
    public List<ClienteResponseDTO> getAllActiveAndByCorretorIdAndByBairro(UUID corretorId, String bairro){
        List<ClienteResponseDTO> clientes = new ArrayList<ClienteResponseDTO>();
        for(Cliente cliente : repository.findAllByCorretores_IdAndBairroLikeIgnoreCaseAndExcluidoEmNull(corretorId, bairro)){
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente);
            clientes.add(clienteResponseDTO);
        }
        return clientes;
    }
    public Cliente save(ClienteRequestDTO data){
        List<Cliente> clientes = repository.findAllByExcluidoEmNull();
        Optional<Cliente> optionalCliente;
        if(clientes.size() > 0){
            optionalCliente = clientes.stream().filter(cliente -> (data.cpf() != null && cliente.getCpf().equals(data.cpf())) || cliente.getEmail().equals(data.email())).findFirst();
            if (optionalCliente.isPresent()) {
                Cliente cliente = optionalCliente.get();
                return cliente;
            }
        }
        Cliente cliente = new Cliente(data);
        repository.save(cliente);
        return cliente;
    }
    public void addToCorretor(Cliente cliente, UUID corretorId){
        Corretor corretor = corretorService.getCorretorById(corretorId);
        corretor.getClientes().add(cliente);
        CorretorRequestDTO corretorRequestDTO = new CorretorRequestDTO(
            corretor.getId(),
            corretor.getParceiros(),
            corretor.getEndereco(),
            corretor.getNome(),
            corretor.getEmail(),
            corretor.getTelefone(),
            corretor.getCpf(),
            corretor.getCreci(),
            corretor.getImobiliaria(),
            corretor.getSenha(),
            corretor.getRedesSociais(),
            corretor.getClientes(),
            corretor.getImoveis(),
            corretor.getExcluidoEm()
        );
        corretorService.update(corretorRequestDTO);
    }
    public ClienteResponseDTO update(ClienteRequestDTO data){
        Optional<Cliente> optionalCliente = repository.findById(data.id());
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            if (data.endereco() != null) {
                cliente.setEndereco(data.endereco());
            }
            if (data.tipo() != null) {
                cliente.setTipo(data.tipo());
            }
            if (data.caracteristicas() != null) {
                cliente.setCaracteristicas(data.caracteristicas());
            }
            if (data.bairro() != null) {
                cliente.setBairro(data.bairro());
            }
            cliente.setNome(data.nome().toUpperCase());
            cliente.setTelefone(data.telefone());
            repository.save(cliente);
            return new ClienteResponseDTO(cliente);
        }
        return null;
    }
    public boolean delete(UUID id){
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setExcluidoEm(OffsetDateTime.now());
            repository.save(cliente);
            return true;
        }
        return false;
    }
}
