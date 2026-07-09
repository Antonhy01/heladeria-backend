package com.heladeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Categoria;
import com.heladeria.model.Producto;
import com.heladeria.repository.CategoriaRepository;
import com.heladeria.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // ===========================
    // LISTAR TODOS LOS PRODUCTOS
    // ===========================
    public List<Producto> listar() {

        return productoRepository.findAll();

    }

    // ===========================
    // BUSCAR POR ID
    // ===========================
    public Optional<Producto> buscarPorId(Long id) {

        return productoRepository.findById(id);

    }

    // ===========================
    // GUARDAR PRODUCTO
    // ===========================
    public Producto guardar(Producto producto) {

        if (producto.getCategoria() == null
                || producto.getCategoria().getId() == null) {

            throw new RuntimeException(
                    "Debe enviar el ID de la categoría."
            );

        }

        Categoria categoria = categoriaRepository
                .findById(producto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException(
                        "La categoría no existe."
                ));

        producto.setCategoria(categoria);

        return productoRepository.save(producto);

    }

    // ===========================
    // ACTUALIZAR PRODUCTO
    // ===========================
    public Producto actualizar(Long id, Producto productoActualizado) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Producto no encontrado con ID: " + id
                ));

        producto.setNombre(productoActualizado.getNombre());
        producto.setSabor(productoActualizado.getSabor());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());

        if (productoActualizado.getCategoria() != null
                && productoActualizado.getCategoria().getId() != null) {

            Categoria categoria = categoriaRepository
                    .findById(productoActualizado.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "La categoría no existe."
                    ));

            producto.setCategoria(categoria);

        }

        return productoRepository.save(producto);

    }

    // ===========================
    // ELIMINAR PRODUCTO
    // ===========================
    public void eliminar(Long id) {

        if (!productoRepository.existsById(id)) {

            throw new RuntimeException(
                    "Producto no encontrado con ID: " + id
            );

        }

        productoRepository.deleteById(id);

    }

}