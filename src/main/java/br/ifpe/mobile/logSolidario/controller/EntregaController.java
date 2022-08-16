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

import br.ifpe.mobile.logSolidario.enums.Status;
import br.ifpe.mobile.logSolidario.models.Entrega;
import br.ifpe.mobile.logSolidario.models.Usuario;
import br.ifpe.mobile.logSolidario.service.EntregaService;
import br.ifpe.mobile.logSolidario.service.UsuarioService;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

	private UsuarioService usuarioService;
	private EntregaService entregaService;


	public EntregaController(UsuarioService usuarioService, EntregaService entregaService) {
		this.usuarioService = usuarioService;
		this.entregaService = entregaService;
	}

	@PostMapping("/criar")
	public ResponseEntity<Entrega> criarEntrega(@RequestBody Entrega entrega,@PathParam(value ="usuarioId") Integer usuarioId) {
		try {
			Usuario usuario = usuarioService.buscaPorId(usuarioId);
			entrega.setUsuario(usuario);
			entregaService.cadastrar(entrega);
			return ResponseEntity.ok().body(entrega);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@PostMapping("/alterar")
	public ResponseEntity<Entrega> atualizarEntrega(@PathParam(value = "entregaID")Integer entregaID,@PathParam(value = "status") String status) {
		try {
			Entrega entrega = entregaService.buscaPorId(entregaID);
			if(status.equals("CONCLUIDA")) {
				entrega.setStatus(Status.CONCLUIDA);
				entregaService.alterarStatus(entrega);
				return ResponseEntity.ok().body(entrega);
			} else if(status.equals("ANDAMENTO")) {
				entrega.setStatus(Status.EM_ANDAMENTO);
				entregaService.alterarStatus(entrega);
				return ResponseEntity.ok().body(entrega);
			}else if(status.equals("SEPARACAO")) {
				entrega.setStatus(Status.EM_SEPARACAO);
				entregaService.alterarStatus(entrega);
				return ResponseEntity.ok().body(entrega);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Entrega>> listarEntregas(@PathParam(value = "usuarioId") Integer usuarioId){
		try {
			Usuario usuario = usuarioService.buscaPorId(usuarioId);
			return ResponseEntity.ok().body(entregaService.listar(usuario));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/buscar")
	public ResponseEntity<Entrega> BuscaPorId(@PathParam(value = "entregaId") Integer entregaId){
		try {
			Entrega entrega = entregaService.buscaPorId(entregaId);
			return ResponseEntity.ok().body(entrega);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
}
