package com.alves.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alves.models.Produto;
import com.alves.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> list = produtoService.listar();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = produtoService.buscar(id);
		return obj != null ? ResponseEntity.ok().body(obj) : ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Produto obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = produtoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
