package com.heladeria.repository;

import org.springframework.stereotype.Repository;
import com.heladeria.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

}