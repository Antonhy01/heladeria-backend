package com.heladeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Cliente;
import com.heladeria.repository.ClienteRepository;
import com.heladeria.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {

        Cliente existente = repository.findById(id).orElse(null);

        if (existente != null) {

            existente.setNombre(cliente.getNombre());
            existente.setApellido(cliente.getApellido());
            existente.setTelefono(cliente.getTelefono());
            existente.setCorreo(cliente.getCorreo());

            return repository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}