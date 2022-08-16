package br.ifpe.mobile.logSolidario.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifpe.mobile.logSolidario.models.Entrega;
import br.ifpe.mobile.logSolidario.models.Usuario;

@Repository
public interface EntregaDAO extends JpaRepository<Entrega, Integer>{

	List<Entrega> findAllByUsuario(Usuario usuario);

}
