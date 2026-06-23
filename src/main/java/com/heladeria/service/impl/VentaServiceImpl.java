package com.heladeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Venta;
import com.heladeria.repository.VentaRepository;
import com.heladeria.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository repository;

    @Override
    public List<Venta> listar() {
        return repository.findAll();
    }

    @Override
    public Venta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Venta guardar(Venta venta) {
        return repository.save(venta);
    }

    @Override
    public Venta actualizar(Long id, Venta venta) {

        Venta existente = repository.findById(id).orElse(null);

        if (existente != null) {

            existente.setFecha(venta.getFecha());
            existente.setTotal(venta.getTotal());
            existente.setCliente(venta.getCliente());

            return repository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
