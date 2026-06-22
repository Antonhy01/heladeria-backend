package com.heladeria.repository;

import org.springframework.stereotype.Repository;
import com.heladeria.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

}