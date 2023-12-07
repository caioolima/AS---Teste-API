package br.com.ulbra.ap2.services;
import br.com.ulbra.ap2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String nome);
    List<Cliente> findByIdade(int idade);
}
