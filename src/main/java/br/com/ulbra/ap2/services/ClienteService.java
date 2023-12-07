package br.com.ulbra.ap2.services;

import br.com.ulbra.ap2.dto.ClienteResponseDTO;
import br.com.ulbra.ap2.exception.NenhumClienteEncontradoException;
import br.com.ulbra.ap2.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteResponseDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()) {
            throw new NenhumClienteEncontradoException("Nenhum cliente encontrado na base de dados.");
        }

        return clientes.stream().map(cliente -> {
            ClienteResponseDTO dto = new ClienteResponseDTO();
            dto.setNome(cliente.getNome());
            dto.setIdade(cliente.getIdade());
            dto.setProfissao(cliente.getProfissao());
            return dto;
        }).collect(Collectors.toList());
    }

    public ClienteResponseDTO getClienteById(long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NenhumClienteEncontradoException("Cliente não encontrado com o ID: " + id));

        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setNome(cliente.getNome());
        dto.setIdade(cliente.getIdade());
        dto.setProfissao(cliente.getProfissao());

        return dto;
    }

    public List<ClienteResponseDTO> getClientesByName(String filterName) {
        List<Cliente> clientes = clienteRepository.findByNome(filterName);

        if (clientes.isEmpty()) {
            throw new NenhumClienteEncontradoException("Nenhum cliente encontrado com o nome: " + filterName);
        }

        return clientes.stream().map(cliente -> {
            ClienteResponseDTO dto = new ClienteResponseDTO();
            dto.setNome(cliente.getNome());
            dto.setIdade(cliente.getIdade());
            dto.setProfissao(cliente.getProfissao());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ClienteResponseDTO> getClientesByAge(int idade) {
        List<Cliente> clientes = clienteRepository.findByIdade(idade);

        if (clientes.isEmpty()) {
            throw new NenhumClienteEncontradoException("Nenhum cliente encontrado com a idade: " + idade);
        }

        return clientes.stream().map(cliente -> {
            ClienteResponseDTO dto = new ClienteResponseDTO();
            dto.setNome(cliente.getNome());
            dto.setIdade(cliente.getIdade());
            dto.setProfissao(cliente.getProfissao());
            return dto;
        }).collect(Collectors.toList());
    }

    public Cliente addCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public ClienteResponseDTO updateCliente(long id, Cliente updatedCliente) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NenhumClienteEncontradoException("Cliente não encontrado com o ID: " + id));

        updatedCliente.setId(id);
        clienteRepository.save(updatedCliente);

        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setNome(updatedCliente.getNome());
        dto.setIdade(updatedCliente.getIdade());
        dto.setProfissao(updatedCliente.getProfissao());

        return dto;
    }

    public boolean deleteCliente(long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NenhumClienteEncontradoException("Cliente não encontrado com o ID: " + id));

        clienteRepository.delete(cliente);
        return true;
    }
}
