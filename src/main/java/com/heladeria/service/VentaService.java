package com.heladeria.service;

import java.util.List;
import com.heladeria.model.Venta;

public interface VentaService {

    List<Venta> listar();

    Venta buscarPorId(Long id);

    Venta guardar(Venta venta);

    Venta actualizar(Long id, Venta venta);

    void eliminar(Long id);
}