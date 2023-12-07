package br.com.ulbra.ap2.controller;

import br.com.ulbra.ap2.dto.ClienteResponseDTO;
import br.com.ulbra.ap2.model.Cliente;
import br.com.ulbra.ap2.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteResponseDTO> getAllClientes(@RequestParam(name = "idade", required = false) Integer filter_age, @RequestParam(name = "nome", required = false) String filter_name) {
        List<ClienteResponseDTO> clientes;

        if (filter_age != null && filter_age > 0) {
            clientes = clienteService.getClientesByAge(filter_age);
        } else if (filter_name != null && !filter_name.isEmpty()) {
            clientes = clienteService.getClientesByName(filter_name);
        } else {
            clientes = clienteService.getAllClientes();
        }

        return clientes;
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO getClienteById(@PathVariable long id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping
    public Cliente addCliente(@RequestBody Cliente cliente) {
        return clienteService.addCliente(cliente);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO updateCliente(@PathVariable int id, @RequestBody Cliente updatedCliente) {
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteCliente(@PathVariable int id) {
        return clienteService.deleteCliente(id);
    }
}
