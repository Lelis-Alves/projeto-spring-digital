package com.alves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
