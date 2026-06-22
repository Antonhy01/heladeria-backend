package com.heladeria.repository;

import org.springframework.stereotype.Repository;
import com.heladeria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}