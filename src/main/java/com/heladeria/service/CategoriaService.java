package com.heladeria.service;

import java.util.List;
import com.heladeria.model.Categoria;

public interface CategoriaService {

    List<Categoria> listar();

    Categoria buscarPorId(Long id);

    Categoria guardar(Categoria categoria);

    Categoria actualizar(Long id, Categoria categoria);

    void eliminar(Long id);

}