package com.heladeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Categoria;
import com.heladeria.model.Producto;
import com.heladeria.repository.CategoriaRepository;
import com.heladeria.repository.ProductoRepository;
import com.heladeria.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

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

        if (producto.getCategoria() == null ||
            producto.getCategoria().getId() == null) {

            throw new RuntimeException("Debe seleccionar una categoría.");
        }

        Categoria categoria = categoriaRepository
                .findById(producto.getCategoria().getId())
                .orElseThrow(() ->
                        new RuntimeException("La categoría no existe."));

        producto.setCategoria(categoria);

        return repository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {

        Producto existente = repository.findById(id).orElse(null);

        if (existente != null) {

            Categoria categoria = categoriaRepository
                    .findById(producto.getCategoria().getId())
                    .orElseThrow(() ->
                            new RuntimeException("La categoría no existe."));

            existente.setNombre(producto.getNombre());
            existente.setSabor(producto.getSabor());
            existente.setPrecio(producto.getPrecio());
            existente.setStock(producto.getStock());
            existente.setCategoria(categoria);

            return repository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}