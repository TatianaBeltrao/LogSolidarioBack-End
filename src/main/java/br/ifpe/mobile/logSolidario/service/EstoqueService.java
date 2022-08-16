package br.ifpe.mobile.logSolidario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ifpe.mobile.logSolidario.models.Estoque;
import br.ifpe.mobile.logSolidario.models.Item;
import br.ifpe.mobile.logSolidario.models.Usuario;
import br.ifpe.mobile.logSolidario.persistence.EstoqueDAO;

@Service
public class EstoqueService {

	
	private EstoqueDAO estoqueDAO;
	
	public EstoqueService(EstoqueDAO estoqueDAO) {
		this.estoqueDAO = estoqueDAO;
	}
	
	public Estoque buscaPorUsuario(Usuario usuario) {
		try {
			Estoque estoque = estoqueDAO.findByUsuario(usuario).get();
			return estoque;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void adicionaItens(List<Item> itens, Estoque estoque) {
		try {
			List<Item> itensAtuais = estoque.getItens();
			if(itensAtuais.isEmpty()) {
				for (Item item : itens) {
						itensAtuais.add(item);		
				}
			}else {		
				for (Item item : itens) {
					for (Item item2 : itensAtuais) {	
						if(item.getNome().toUpperCase().equals(item2.getNome().toUpperCase())) {
							item2.setQuantidade((item2.getQuantidade() + item.getQuantidade()));
						}
					}
					Optional<Item> itemIgual = itensAtuais.stream().filter(item2 -> item2.getNome().equals(item.getNome())).findFirst();
					if(itemIgual.isEmpty()) {
						itensAtuais.add(item);
					}
				}
			}
			estoque.setItens(itensAtuais);
			estoqueDAO.save(estoque);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
