package br.edu.infnet.tp3microservices.repository;

import br.edu.infnet.tp3microservices.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}