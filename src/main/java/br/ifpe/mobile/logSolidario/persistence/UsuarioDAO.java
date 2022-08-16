package br.ifpe.mobile.logSolidario.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifpe.mobile.logSolidario.models.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByEmail(String email);

}
