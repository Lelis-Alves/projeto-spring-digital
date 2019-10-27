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

import com.alves.models.Cidade;
import com.alves.models.Cliente;
import com.alves.models.Estado;
import com.alves.models.dto.EstadoDTO;
import com.alves.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = estadoService.listar();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Estado> find(@PathVariable Integer id) {
		Estado obj = estadoService.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Estado obj) {
		obj = estadoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{estado}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Estado obj, @PathVariable Integer estado) {
		obj.setId(estado);
		obj = estadoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{estadoId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer estadoId) {
		estadoService.delete(estadoId);
		return ResponseEntity.noContent().build();
	}
	
}
