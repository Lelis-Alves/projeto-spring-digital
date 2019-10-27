package com.alves.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alves.models.Categoria;
import com.alves.models.Estado;
import com.alves.models.Pedido;
import com.alves.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> list = pedidoService.listar();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido obj = pedidoService.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Pedido obj) {
		obj = pedidoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Pedido obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = pedidoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		pedidoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
