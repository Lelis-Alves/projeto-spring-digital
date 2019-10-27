package com.alves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.exceptions.ObjectNotFoundException;
import com.alves.models.Pedido;
import com.alves.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;

	public List<Pedido> listar() {
		return pedidoRepo.findAll();
	}

	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = pedidoRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		return pedidoRepo.save(obj);
	}

	public Pedido update(Pedido obj) {
		buscar(obj.getId());
		return pedidoRepo.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		pedidoRepo.deleteById(id);
	}

}
