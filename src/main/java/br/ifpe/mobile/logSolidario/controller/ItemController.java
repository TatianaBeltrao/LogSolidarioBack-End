package br.ifpe.mobile.logSolidario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ifpe.mobile.logSolidario.models.Item;
import br.ifpe.mobile.logSolidario.persistence.ItemDAO;



@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemDAO dao;
	
	@RequestMapping(method=RequestMethod.POST)
	public void criarItem(@RequestBody Item item){
		try {
			boolean verificador = dao.findByNomeIgnoreCase(item.getNome()) == null;
			if(verificador) {
				dao.save(item);
			}else {
				System.out.println("ja existe no banco");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
}
