package com.ivanprdg.service;
import com.ivanprdg.model.entity.Cliente;

public interface ICliente {

    Cliente save(Cliente cliente);

    Cliente findById(int id);

    void delete(Cliente cliente);

}
