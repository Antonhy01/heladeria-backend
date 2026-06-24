package com.heladeria.service;

import java.util.List;
import com.heladeria.model.Producto;

public interface ProductoService {

    List<Producto> listar();

    Producto buscarPorId(Long id);

    Producto guardar(Producto producto);

    Producto actualizar(Long id, Producto producto);

    void eliminar(Long id);
}