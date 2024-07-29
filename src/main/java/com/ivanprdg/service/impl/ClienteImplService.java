package com.ivanprdg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanprdg.model.dao.ClienteDAO;
import com.ivanprdg.model.dto.ClienteDto;
import com.ivanprdg.model.entity.Cliente;
import com.ivanprdg.service.IClienteService;

import org.springframework.transaction.annotation.Transactional;

@Service //Indica que esta clase es un servicio
public class ClienteImplService implements IClienteService{

    @Autowired //Inyección de dependencias
    private ClienteDAO clienteDAO; //Interfaz que extiende de CrudRepository

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder()
            .id_cliente(clienteDto.getId_cliente())
            .nombre(clienteDto.getNombre())
            .apellido(clienteDto.getApellido())
            .correo(clienteDto.getCorreo())
            .fecha_registro(clienteDto.getFecha_registro())
            .build();
        return clienteDAO.save(cliente);
    }

    /*Usamos readOnly solo en operaciones de búsqueda o recuperación de datos para asegurarnos
    de que solo podemos realizar la operacion de lectura y no de escritura*/
    @Transactional(readOnly = true) //Usada para evitar la excepción LazyInitializationException
    @Override
    public Cliente findById(Integer id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDAO.delete(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Integer id) {
        return clienteDAO.existsById(id);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listAll() {
        return (List) clienteDAO.findAll();
    }

}
