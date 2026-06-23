package com.heladeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Categoria;
import com.heladeria.repository.CategoriaRepository;
import com.heladeria.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<Categoria> listar() {
        return repository.findAll();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public Categoria actualizar(Long id, Categoria categoria) {

        Categoria existente = repository.findById(id).orElse(null);

        if (existente != null) {
            existente.setNombre(categoria.getNombre());
            existente.setDescripcion(categoria.getDescripcion());

            return repository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}