package com.heladeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Venta;
import com.heladeria.repository.VentaRepository;

@Service
public class VentaService {


    @Autowired
    private VentaRepository ventaRepository;


    // Listar todas las ventas
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }


    // Buscar venta por ID
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }


    // Registrar venta
    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }


    // Actualizar venta
    public Venta actualizar(Long id, Venta ventaActualizada) {

        return ventaRepository.findById(id)
                .map(venta -> {

                    venta.setFecha(ventaActualizada.getFecha());
                    venta.setTotal(ventaActualizada.getTotal());
                    venta.setCliente(ventaActualizada.getCliente());

                    return ventaRepository.save(venta);

                })
                .orElseThrow(() ->
                    new RuntimeException(
                        "Venta no encontrada con ID: " + id
                    )
                );
    }


    // Eliminar venta
    public void eliminar(Long id) {

        if (!ventaRepository.existsById(id)) {

            throw new RuntimeException(
                "Venta no encontrada con ID: " + id
            );
        }

        ventaRepository.deleteById(id);
    }
}