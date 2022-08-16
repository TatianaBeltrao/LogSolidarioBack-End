package br.ifpe.mobile.logSolidario.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.ifpe.mobile.logSolidario.enums.Status;
import br.ifpe.mobile.logSolidario.models.Entrega;
import br.ifpe.mobile.logSolidario.models.Estoque;
import br.ifpe.mobile.logSolidario.models.Item;
import br.ifpe.mobile.logSolidario.models.Usuario;
import br.ifpe.mobile.logSolidario.persistence.EntregaDAO;
import br.ifpe.mobile.logSolidario.persistence.EstoqueDAO;
import br.ifpe.mobile.logSolidario.persistence.ItemDAO;

@Service
public class EntregaService {

	private EntregaDAO entregaDao;
	private EstoqueDAO estoqueDao;

	private ItemDAO itemDao;

	
	public EntregaService(EntregaDAO entregaDao,ItemDAO itemDao,EstoqueDAO estoqueDao) {
		this.entregaDao = entregaDao;
		this.itemDao = itemDao;
		this.estoqueDao = estoqueDao;
	}


	public void cadastrar(Entrega entrega) {
		try {
			entrega.setStatus(Status.EM_SEPARACAO);
			entrega.setData(LocalDate.now());
			Estoque estoque = estoqueDao.findByUsuario(entrega.getUsuario()).get();
			List<Item> itensAux = new ArrayList<Item>();
			for (Item itemEntrega: entrega.getItens()) {
				for (Item itemEstoque: estoque.getItens()) {
					if(itemEntrega.getNome().toUpperCase().equals(itemEstoque.getNome().toUpperCase())) {
						if(itemEstoque.getQuantidade() - itemEntrega.getQuantidade() == 0) {
							itemEstoque.setQuantidade((itemEstoque.getQuantidade() - itemEntrega.getQuantidade()));
							itensAux.add(itemEstoque);
						}else {
							itemEstoque.setQuantidade((itemEstoque.getQuantidade() - itemEntrega.getQuantidade()));
						}
					}
				}
				for (Item item : itensAux) {
					if(estoque.getItens().contains(item)) {
						estoque.getItens().remove(item);
						estoqueDao.save(estoque);
					}
				}
				estoqueDao.save(estoque);
				itemDao.save(itemEntrega);
			}
			entregaDao.save(entrega);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Entrega> listar(Usuario usuario) {
		try {
			return entregaDao.findAllByUsuario(usuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void alterarStatus(Entrega entrega) {
		try {
			entregaDao.save(entrega);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Entrega buscaPorId(Integer id) {
		try {
			Entrega entrega = entregaDao.findById(id).get();
			return entrega;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


}
