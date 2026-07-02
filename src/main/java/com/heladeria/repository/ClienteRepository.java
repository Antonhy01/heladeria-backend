package com.heladeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heladeria.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}