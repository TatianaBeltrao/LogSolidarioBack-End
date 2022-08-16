package br.ifpe.mobile.logSolidario.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifpe.mobile.logSolidario.models.Item;

@Repository
public interface ItemDAO extends JpaRepository<Item, Integer>{

	Item findByNomeIgnoreCase(String nome);

}
