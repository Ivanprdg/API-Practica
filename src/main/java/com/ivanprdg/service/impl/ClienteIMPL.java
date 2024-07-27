package com.ivanprdg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanprdg.model.dao.ClienteDAO;
import com.ivanprdg.model.entity.Cliente;
import com.ivanprdg.service.ICliente;

import org.springframework.transaction.annotation.Transactional;

@Service //Indica que esta clase es un servicio
public class ClienteIMPL implements ICliente{

    @Autowired //Inyección de dependencias
    private ClienteDAO clienteDAO; //Interfaz que extiende de CrudRepository

    @Transactional
    @Override
    public Cliente save(Cliente cliente) {
        return clienteDAO.save(cliente);
    }

    /*Usamos readOnly solo en operaciones de búsqueda o recuperación de datos para asegurarnos
    de que solo podemos realizar la operacion de lectura y no de escritura*/
    @Transactional(readOnly = true) //Usada para evitar la excepción LazyInitializationException
    @Override
    public Cliente findById(int id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDAO.delete(cliente);
    }

}
