package br.ifpe.mobile.logSolidario.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ifpe.mobile.logSolidario.models.Usuario;
import br.ifpe.mobile.logSolidario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;
	
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
		try {
			usuarioService.cadastrar(usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Usuario> buscaUsuarioPorId(@PathParam(value = "id") Integer id){
		try {
			Usuario usuario = usuarioService.buscaPorId(id);
			return ResponseEntity.ok().body(usuario);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/buscaPorEmail")
	public ResponseEntity<Usuario> buscaUsuarioPorEmail(@PathParam(value = "email") String email){
		try {
			Usuario usuario = usuarioService.buscaPorEmail(email);
			return ResponseEntity.ok().body(usuario);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
}
