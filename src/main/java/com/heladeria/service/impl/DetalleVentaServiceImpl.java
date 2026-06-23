package com.heladeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.DetalleVenta;
import com.heladeria.repository.DetalleVentaRepository;
import com.heladeria.service.DetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository repository;

    @Override
    public List<DetalleVenta> listar() {
        return repository.findAll();
    }

    @Override
    public DetalleVenta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        return repository.save(detalleVenta);
    }

    @Override
    public DetalleVenta actualizar(Long id, DetalleVenta detalleVenta) {

        DetalleVenta existente = repository.findById(id).orElse(null);

        if (existente != null) {

            existente.setCantidad(detalleVenta.getCantidad());
            existente.setPrecioUnitario(detalleVenta.getPrecioUnitario());
            existente.setSubtotal(detalleVenta.getSubtotal());
            existente.setVenta(detalleVenta.getVenta());
            existente.setProducto(detalleVenta.getProducto());

            return repository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}