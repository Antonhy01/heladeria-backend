package com.heladeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heladeria.model.Cliente;
import com.heladeria.repository.ClienteRepository;


@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;



    // Listar todos los clientes
    public List<Cliente> listar() {

        return clienteRepository.findAll();

    }



    // Buscar cliente por ID
    public Optional<Cliente> buscarPorId(Long id) {

        return clienteRepository.findById(id);

    }



    // Guardar cliente
    public Cliente guardar(Cliente cliente) {

        return clienteRepository.save(cliente);

    }



    // Actualizar cliente
    public Cliente actualizar(Long id, Cliente clienteActualizado) {


        return clienteRepository.findById(id)
                .map(cliente -> {


                    cliente.setNombres(
                        clienteActualizado.getNombres()
                    );


                    cliente.setApellidos(
                        clienteActualizado.getApellidos()
                    );


                    cliente.setDni(
                        clienteActualizado.getDni()
                    );


                    cliente.setEmail(
                        clienteActualizado.getEmail()
                    );


                    cliente.setTelefono(
                        clienteActualizado.getTelefono()
                    );


                    cliente.setDireccion(
                        clienteActualizado.getDireccion()
                    );


                    return clienteRepository.save(cliente);


                })
                .orElseThrow(() ->

                    new RuntimeException(
                        "Cliente no encontrado con ID: " + id
                    )

                );

    }



    // Eliminar cliente
    public void eliminar(Long id) {


        if (!clienteRepository.existsById(id)) {


            throw new RuntimeException(
                "Cliente no encontrado con ID: " + id
            );

        }


        clienteRepository.deleteById(id);

    }

}