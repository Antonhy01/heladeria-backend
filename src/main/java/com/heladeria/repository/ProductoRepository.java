package com.heladeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heladeria.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}