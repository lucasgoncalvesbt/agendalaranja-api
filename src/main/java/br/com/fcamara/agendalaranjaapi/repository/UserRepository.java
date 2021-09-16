package br.com.fcamara.agendalaranjaapi.repository;

import br.com.fcamara.agendalaranjaapi.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String username);
}
