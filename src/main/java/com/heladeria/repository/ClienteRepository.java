package com.heladeria.repository;

import org.springframework.stereotype.Repository;
import com.heladeria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}