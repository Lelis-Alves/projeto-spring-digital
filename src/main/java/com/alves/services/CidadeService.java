package com.alves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exceptions.ObjectNotFoundException;
import com.alves.models.Cidade;
import com.alves.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepo;

	public List<Cidade> listar() {
		return cidadeRepo.findAll();
	}

	public Cidade buscar(Integer id) {
		Optional<Cidade> obj = cidadeRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}

	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return cidadeRepo.save(obj);
	}

	public Cidade update(Cidade obj) {
		buscar(obj.getId());
		return cidadeRepo.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		cidadeRepo.deleteById(id);
	}

}