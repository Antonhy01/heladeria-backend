package com.heladeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Cliente;
import com.heladeria.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente cliente) {

        Cliente existente = repository.findById(id).orElse(null);

        if (existente != null) {

            existente.setNombres(cliente.getNombres());
            existente.setApellidos(cliente.getApellidos());
            existente.setDni(cliente.getDni());
            existente.setTelefono(cliente.getTelefono());
            existente.setDireccion(cliente.getDireccion());

            return repository.save(existente);
        }

        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}