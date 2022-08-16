package br.ifpe.mobile.logSolidario.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ifpe.mobile.logSolidario.models.Estoque;
import br.ifpe.mobile.logSolidario.models.Item;
import br.ifpe.mobile.logSolidario.models.Usuario;
import br.ifpe.mobile.logSolidario.service.EstoqueService;
import br.ifpe.mobile.logSolidario.service.UsuarioService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {


	private UsuarioService usuarioService;
	private EstoqueService estoqueService;
	
	

	public EstoqueController(UsuarioService usuarioService, EstoqueService estoqueService) {
		this.usuarioService = usuarioService;
		this.estoqueService = estoqueService;
	}



	@RequestMapping(method=RequestMethod.GET, value="/listar")
	public ResponseEntity<List<Item>> listarItensEstoque(@PathParam(value = "usuarioId") Integer usuarioId){
		try {
			Usuario usuario = usuarioService.buscaPorId(usuarioId);
			Estoque estoque = estoqueService.buscaPorUsuario(usuario);
			return ResponseEntity.ok().body(estoque.getItens());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
}
