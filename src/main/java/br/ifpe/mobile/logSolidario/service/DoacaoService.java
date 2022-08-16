package br.ifpe.mobile.logSolidario.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.ifpe.mobile.logSolidario.models.Doacao;
import br.ifpe.mobile.logSolidario.models.Item;
import br.ifpe.mobile.logSolidario.models.Usuario;
import br.ifpe.mobile.logSolidario.persistence.DoacaoDAO;
import br.ifpe.mobile.logSolidario.persistence.ItemDAO;

@Service
public class DoacaoService {

	
	private DoacaoDAO doacaoDAO;
	private EstoqueService estoqueService;
	private ItemDAO itemDAO;


	public DoacaoService(DoacaoDAO doacaoDAO, EstoqueService estoqueService, UsuarioService usuarioService, ItemDAO itemDAO) {
		this.doacaoDAO = doacaoDAO;
		this.estoqueService = estoqueService;
		this.itemDAO = itemDAO;
	}
	
	public void cadastrar(Doacao doacao, Usuario usuario) {
		try {
			doacao.setData(LocalDate.now());
			Integer contador = 0;
			for (Item item : doacao.getItens()) {
				contador+=item.getQuantidade();
				itemDAO.save(item);
			}
			doacao.setUsuario(usuario);
			doacao.setTotalDeItens(contador);
			doacaoDAO.save(doacao);
			estoqueService.adicionaItens(doacao.getItens(), estoqueService.buscaPorUsuario(usuario));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Doacao> listar(Usuario usuario) {
		try {
			return doacaoDAO.findAllByUsuario(usuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Doacao buscaPorId(Integer doacaoId) {
		try {
			return doacaoDAO.findById(doacaoId).get();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
