package br.edu.infnet.tp3microservices.service;

import br.edu.infnet.tp3microservices.model.Cliente;
import br.edu.infnet.tp3microservices.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }
}