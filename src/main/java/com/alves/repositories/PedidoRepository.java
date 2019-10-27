package com.alves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
