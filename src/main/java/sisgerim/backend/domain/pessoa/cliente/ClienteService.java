package sisgerim.backend.domain.pessoa.cliente;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sisgerim.backend.domain.caracteristica.Caracteristica;
import sisgerim.backend.domain.tipo.Tipo;
import sisgerim.backend.repositories.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    public List<ClienteResponseDTO> getAllActive(){
        List<ClienteResponseDTO> clientes = new ArrayList<ClienteResponseDTO>();
        for (Cliente cliente : repository.findAllByExcluidoEmNull()){
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente);
            clientes.add(clienteResponseDTO);
        }
        return clientes;
    }
    public List<ClienteResponseDTO> getClientesByTipo(Tipo tipo){
        List<ClienteResponseDTO> clientes = new ArrayList<ClienteResponseDTO>();
        for(Cliente cliente : repository.findAllByTipo(tipo)){
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente);
            clientes.add(clienteResponseDTO);
        }
        return clientes;
    }
    public List<ClienteResponseDTO> getClientesByCaracteristica(Caracteristica caracteristica){
        List<ClienteResponseDTO> clientes = new ArrayList<ClienteResponseDTO>();
        for(Cliente cliente : repository.findAllByCaracteristica(caracteristica)){
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente);
            clientes.add(clienteResponseDTO);
        }
        return clientes;
    }
    public List<ClienteResponseDTO> getClientesByBairro(String bairro){
        List<ClienteResponseDTO> clientes = new ArrayList<ClienteResponseDTO>();
        for(Cliente cliente : repository.findAllByBairro(bairro)){
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente);
            clientes.add(clienteResponseDTO);
        }
        return clientes;
    }
    public void save(ClienteRequestDTO data){
        if (data.cpf() != null) {
            Optional<Cliente> optionalCpf = repository.findByCpf(data.cpf());
            if (optionalCpf.isPresent()) {
                throw new RuntimeException("CPF já cadastrado");
            }
        }
        Optional<Cliente> optionalEmail = repository.findByEmail(data.email());
        if (optionalEmail.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        Cliente cliente = new Cliente(data);
        repository.save(cliente);
        return;
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
            if (data.caracteristica() != null) {
                cliente.setCaracteristica(data.caracteristica());
            }
            if (data.bairro() != null) {
                cliente.setBairro(data.bairro());
            }
            cliente.setNome(data.nome().toUpperCase());
            cliente.setEmail(data.email());
            cliente.setTelefone(data.telefone());
            if (data.cpf() != null) {
                cliente.setCpf(data.cpf());
            }
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
