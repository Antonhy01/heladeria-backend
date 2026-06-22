package com.heladeria.service;

import java.util.List;
import com.heladeria.model.DetalleVenta;

public interface DetalleVentaService {

    List<DetalleVenta> listar();

    DetalleVenta buscarPorId(Long id);

    DetalleVenta guardar(DetalleVenta detalleVenta);

    DetalleVenta actualizar(Long id, DetalleVenta detalleVenta);

    void eliminar(Long id);
}