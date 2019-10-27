package com.alves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exceptions.ObjectNotFoundException;
import com.alves.models.Produto;
import com.alves.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

	public List<Produto> listar() {
		return produtoRepo.findAll();
	}
	
	public Produto buscar(Integer id) {
		Optional<Produto> obj = produtoRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Produto insert(Produto obj) {
		obj.setId(null);
		return produtoRepo.save(obj);
	}

	public Produto update(Produto obj) {
		buscar(obj.getId());
		return produtoRepo.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		produtoRepo.deleteById(id);
	}
	

}
