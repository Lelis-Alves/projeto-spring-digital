package com.alves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exceptions.ObjectNotFoundException;
import com.alves.models.Categoria;
import com.alves.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired(required = true)
	private CategoriaRepository categoriaRepo;

	public List<Categoria> listar() {
		return categoriaRepo.findAll();
	}

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = categoriaRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepo.save(obj);
	}

	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return categoriaRepo.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		categoriaRepo.deleteById(id);
	}

}
