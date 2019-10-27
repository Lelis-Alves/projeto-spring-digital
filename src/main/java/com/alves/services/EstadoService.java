package com.alves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exceptions.ObjectNotFoundException;
import com.alves.models.Estado;
import com.alves.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepo;

	public List<Estado> listar() {
		return estadoRepo.findAllByOrderByNome();
	}
	
	public Estado buscar(Integer id) {
		Optional<Estado> obj = estadoRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}
	
	public Estado insert(Estado obj) {
		obj.setId(null);
		return estadoRepo.save(obj);
	}

	public Estado update(Estado obj) {
		buscar(obj.getId());
		return estadoRepo.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		estadoRepo.deleteById(id);
	}
}
