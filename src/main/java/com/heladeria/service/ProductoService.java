package com.heladeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Producto;
import com.heladeria.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    // Listar todos los productos
    public List<Producto> listar() {
        return productoRepository.findAll();
    }


    // Buscar producto por ID
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }


    // Guardar producto
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }


    // Actualizar producto
    public Producto actualizar(Long id, Producto productoActualizado) {

        return productoRepository.findById(id)
                .map(producto -> {

                    producto.setNombre(productoActualizado.getNombre());
                    producto.setSabor(productoActualizado.getSabor());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setStock(productoActualizado.getStock());
                    producto.setCategoria(productoActualizado.getCategoria());

                    return productoRepository.save(producto);

                })
                .orElseThrow(() -> 
                    new RuntimeException(
                        "Producto no encontrado con ID: " + id
                    )
                );
    }


    // Eliminar producto
    public void eliminar(Long id) {

        if (!productoRepository.existsById(id)) {

            throw new RuntimeException(
                "Producto no encontrado con ID: " + id
            );
        }

        productoRepository.deleteById(id);
    }
}