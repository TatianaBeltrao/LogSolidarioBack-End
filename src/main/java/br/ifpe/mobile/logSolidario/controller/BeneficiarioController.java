package br.ifpe.mobile.logSolidario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ifpe.mobile.logSolidario.models.Beneficiario;
import br.ifpe.mobile.logSolidario.persistence.BeneficiarioDAO;

@RestController
@RequestMapping("/beneficiario")
public class BeneficiarioController {

	@Autowired
	private BeneficiarioDAO dao;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Beneficiario> criarBeneficiario(@RequestBody Beneficiario beneficiario){
		try {
			dao.save(beneficiario);
			return ResponseEntity.ok().body(beneficiario);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Beneficiario>> listarBeneficiarios(){
		try {
			List<Beneficiario> beneficiarios = dao.findAll();
			return ResponseEntity.ok().body(beneficiarios);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
}
