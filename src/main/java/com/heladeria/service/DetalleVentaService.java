package com.heladeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.DetalleVenta;
import com.heladeria.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository repository;

    public List<DetalleVenta> listar() {
        return repository.findAll();
    }

    public DetalleVenta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public DetalleVenta guardar(DetalleVenta detalle) {

        // El subtotal se calcula automáticamente
        // gracias al @PrePersist de la entidad.

        return repository.save(detalle);

    }

    public DetalleVenta actualizar(Long id, DetalleVenta detalle) {

        DetalleVenta existente = repository.findById(id).orElse(null);

        if (existente != null) {

            existente.setCantidad(detalle.getCantidad());
            existente.setPrecio(detalle.getPrecio());
            existente.setProducto(detalle.getProducto());
            existente.setVenta(detalle.getVenta());

            return repository.save(existente);

        }

        return null;

    }

    public void eliminar(Long id) {

        repository.deleteById(id);

    }

}