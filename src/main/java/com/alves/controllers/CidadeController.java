package com.alves.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alves.models.Cidade;
import com.alves.models.Pedido;
import com.alves.models.dto.CidadeDTO;
import com.alves.services.CidadeService;



@RestController
@RequestMapping(value="/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findAll() {
		List<Cidade> list = service.listar();
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cidade> find(@PathVariable Integer id) {
		Cidade obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{cidade}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cidade obj, @PathVariable Integer cidade) {
		obj.setId(cidade);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{cidadeId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer cidadeId) {
		service.delete(cidadeId);
		return ResponseEntity.noContent().build();
	}
	
}
