package com.heladeria.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.DetalleVenta;
import com.heladeria.model.Producto;
import com.heladeria.model.Venta;
import com.heladeria.repository.ProductoRepository;
import com.heladeria.repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Venta> listar() {
        return repository.findAll();
    }

    public Venta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Venta guardar(Venta venta) {

        venta.setFecha(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        if (venta.getDetalles() != null) {

            for (DetalleVenta detalle : venta.getDetalles()) {

                Producto producto = productoRepository
                        .findById(detalle.getProducto().getId())
                        .orElseThrow();

                if (producto.getStock() < detalle.getCantidad()) {

                    throw new RuntimeException(
                            "Stock insuficiente para el producto: "
                                    + producto.getNombre());

                }

                detalle.setVenta(venta);

                detalle.setPrecio(producto.getPrecio());

                BigDecimal subtotal = producto.getPrecio()
                        .multiply(BigDecimal.valueOf(detalle.getCantidad()));

                detalle.setSubtotal(subtotal);

                total = total.add(subtotal);

                producto.setStock(
                        producto.getStock() - detalle.getCantidad());

                productoRepository.save(producto);

            }

        }

        venta.setTotal(total);

        return repository.save(venta);

    }

    public Venta actualizar(Long id, Venta venta) {

        Venta existente = repository.findById(id).orElse(null);

        if (existente != null) {

            existente.setCliente(venta.getCliente());

            return repository.save(existente);

        }

        return null;

    }

    public void eliminar(Long id) {

        repository.deleteById(id);

    }

}