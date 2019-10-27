package com.alves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exceptions.ObjectNotFoundException;
import com.alves.models.Cliente;
import com.alves.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;

	public List<Cliente> listar() {
		return clienteRepo.findAll();
	}

	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = clienteRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return clienteRepo.save(obj);
	}

	public Cliente update(Cliente obj) {
		buscar(obj.getId());
		return clienteRepo.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		clienteRepo.deleteById(id);
	}

}
