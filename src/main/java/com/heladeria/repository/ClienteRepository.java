package com.heladeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heladeria.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}