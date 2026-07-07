package com.heladeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Categoria;
import com.heladeria.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    // Listar todas las categorias
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }


    // Buscar categoria por ID
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }


    // Guardar categoria
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }


    // Actualizar categoria
    public Categoria actualizar(Long id, Categoria categoriaActualizada) {

        return categoriaRepository.findById(id)
                .map(categoria -> {

                    categoria.setNombre(categoriaActualizada.getNombre());
                    categoria.setDescripcion(categoriaActualizada.getDescripcion());

                    return categoriaRepository.save(categoria);

                })
                .orElseThrow(() -> 
                    new RuntimeException("Categoria no encontrada con ID: " + id)
                );
    }


    // Eliminar categoria
    public void eliminar(Long id) {

        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException(
                "Categoria no encontrada con ID: " + id
            );
        }

        categoriaRepository.deleteById(id);
    }
}