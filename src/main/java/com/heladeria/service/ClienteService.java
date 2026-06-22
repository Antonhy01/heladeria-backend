package com.heladeria.service;

import java.util.List;
import com.heladeria.model.Cliente;

public interface ClienteService {

    List<Cliente> listar();

    Cliente buscarPorId(Long id);

    Cliente guardar(Cliente cliente);

    Cliente actualizar(Long id, Cliente cliente);

    void eliminar(Long id);
}