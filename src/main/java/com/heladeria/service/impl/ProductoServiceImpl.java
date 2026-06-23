package com.heladeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Producto;
import com.heladeria.repository.ProductoRepository;
import com.heladeria.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<Producto> listar() {
        return repository.findAll();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {

        Producto existente = repository.findById(id).orElse(null);

        if (existente != null) {

            existente.setNombre(producto.getNombre());
            existente.setSabor(producto.getSabor());
            existente.setPrecio(producto.getPrecio());
            existente.setStock(producto.getStock());
            existente.setCategoria(producto.getCategoria());

            return repository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}