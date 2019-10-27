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

import com.alves.models.Estado;
import com.alves.models.dto.EstadoDTO;
import com.alves.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = service.listar();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{estado}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Estado obj, @PathVariable Integer estado) {
		obj.setId(estado);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{estadoId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer estadoId) {
		service.delete(estadoId);
		return ResponseEntity.noContent().build();
	}
	
}
