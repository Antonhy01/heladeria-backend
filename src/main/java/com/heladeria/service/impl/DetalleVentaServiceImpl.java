package com.heladeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.DetalleVenta;
import com.heladeria.model.Producto;
import com.heladeria.model.Venta;
import com.heladeria.repository.DetalleVentaRepository;
import com.heladeria.repository.ProductoRepository;
import com.heladeria.repository.VentaRepository;
import com.heladeria.service.DetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository repository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

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

        if (detalleVenta.getVenta() == null ||
            detalleVenta.getVenta().getId() == null) {
            throw new RuntimeException("Debe seleccionar una venta.");
        }

        if (detalleVenta.getProducto() == null ||
            detalleVenta.getProducto().getId() == null) {
            throw new RuntimeException("Debe seleccionar un producto.");
        }

        Venta venta = ventaRepository
                .findById(detalleVenta.getVenta().getId())
                .orElseThrow(() ->
                        new RuntimeException("La venta no existe."));

        Producto producto = productoRepository
                .findById(detalleVenta.getProducto().getId())
                .orElseThrow(() ->
                        new RuntimeException("El producto no existe."));

        detalleVenta.setVenta(venta);
        detalleVenta.setProducto(producto);

        // Calcular subtotal automáticamente
        detalleVenta.setPrecioUnitario(producto.getPrecio());
        detalleVenta.setSubtotal(
                detalleVenta.getCantidad() * producto.getPrecio());

        return repository.save(detalleVenta);
    }

    @Override
    public DetalleVenta actualizar(Long id, DetalleVenta detalleVenta) {

        DetalleVenta existente = repository.findById(id).orElse(null);

        if (existente != null) {

            Venta venta = ventaRepository
                    .findById(detalleVenta.getVenta().getId())
                    .orElseThrow(() ->
                            new RuntimeException("La venta no existe."));

            Producto producto = productoRepository
                    .findById(detalleVenta.getProducto().getId())
                    .orElseThrow(() ->
                            new RuntimeException("El producto no existe."));

            existente.setCantidad(detalleVenta.getCantidad());
            existente.setVenta(venta);
            existente.setProducto(producto);

            existente.setPrecioUnitario(producto.getPrecio());
            existente.setSubtotal(
                    detalleVenta.getCantidad() * producto.getPrecio());

            return repository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}