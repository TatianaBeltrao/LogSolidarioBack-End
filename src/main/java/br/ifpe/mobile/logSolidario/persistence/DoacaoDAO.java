package br.ifpe.mobile.logSolidario.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifpe.mobile.logSolidario.models.Doacao;
import br.ifpe.mobile.logSolidario.models.Usuario;

@Repository
public interface DoacaoDAO extends JpaRepository<Doacao, Integer> {


	List<Doacao> findAllByUsuario(Usuario usuario);

}
