package com.alves.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alves.models.Categoria;
import com.alves.models.Cidade;
import com.alves.models.Pedido;
import com.alves.models.dto.CidadeDTO;
import com.alves.services.CidadeService;



@RestController
@RequestMapping(value="/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findAll() {
		List<Cidade> list = cidadeService.listar();
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Cidade obj) {
		obj = cidadeService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cidade> find(@PathVariable Integer id) {
		Cidade obj = cidadeService.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{cidade}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cidade obj, @PathVariable Integer cidade) {
		obj.setId(cidade);
		obj = cidadeService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{cidadeId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer cidadeId) {
		cidadeService.delete(cidadeId);
		return ResponseEntity.noContent().build();
	}
	
}
