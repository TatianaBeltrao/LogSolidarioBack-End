package br.ifpe.mobile.logSolidario.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.ifpe.mobile.logSolidario.models.Estoque;
import br.ifpe.mobile.logSolidario.models.Usuario;
import br.ifpe.mobile.logSolidario.persistence.EstoqueDAO;
import br.ifpe.mobile.logSolidario.persistence.UsuarioDAO;

@Service
public class UsuarioService {

	
	private UsuarioDAO usuarioDAO;
	private EstoqueDAO estoqueDAO;
	
	public UsuarioService(UsuarioDAO usuarioDAO, EstoqueDAO estoqueDAO) {
		this.usuarioDAO = usuarioDAO;
		this.estoqueDAO = estoqueDAO;
	}
	
	
	public void cadastrar(Usuario usuario) {
		try {
			usuarioDAO.save(usuario);
			Estoque estoque = new Estoque();
			estoque.setUsuario(usuario);
			estoque.setDataModificacao(LocalDate.now());
			estoqueDAO.save(estoque);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public Usuario buscaPorId(Integer id) {
		try {
			Usuario usuario = usuarioDAO.findById(id).get();
			return usuario;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Usuario buscaPorEmail(String email) {
		try {
			Usuario usuario = usuarioDAO.findByEmail(email).get();
			return usuario;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
}
