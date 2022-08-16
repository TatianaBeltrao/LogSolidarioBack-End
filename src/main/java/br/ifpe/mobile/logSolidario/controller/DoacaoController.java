package br.ifpe.mobile.logSolidario.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ifpe.mobile.logSolidario.models.Doacao;
import br.ifpe.mobile.logSolidario.models.Usuario;
import br.ifpe.mobile.logSolidario.service.DoacaoService;
import br.ifpe.mobile.logSolidario.service.UsuarioService;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

	private DoacaoService doacaoService;
	private UsuarioService usuarioService;
	
	public DoacaoController(DoacaoService doacaoService, UsuarioService usuarioService) {
		this.doacaoService = doacaoService;
		this.usuarioService = usuarioService;
	}

	@PostMapping("/criar")
	public ResponseEntity<Doacao> criarDoacao(@RequestBody Doacao doacao,@PathParam(value ="id") Integer id) {
		try {
			Usuario usuario = usuarioService.buscaPorId(id);
			doacaoService.cadastrar(doacao, usuario);
			return ResponseEntity.ok().body(doacao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Doacao>> listarDoacoes(@PathParam(value = "usuarioId") Integer usuarioId){
		try {
			Usuario usuario = usuarioService.buscaPorId(usuarioId);
			return ResponseEntity.ok().body(doacaoService.listar(usuario));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/buscar")
	public ResponseEntity<Doacao> BuscaPorId(@PathParam(value = "doacaoId") Integer doacaoId){
		try {
			Doacao doacao = doacaoService.buscaPorId(doacaoId);
			return ResponseEntity.ok().body(doacao);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
}
