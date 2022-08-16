package br.ifpe.mobile.logSolidario.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifpe.mobile.logSolidario.models.Estoque;
import br.ifpe.mobile.logSolidario.models.Usuario;

@Repository
public interface EstoqueDAO extends JpaRepository<Estoque, Integer>{


	public Optional<Estoque> findByUsuario(Usuario usuario);
}
