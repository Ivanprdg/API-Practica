package com.ivanprdg.service;
import com.ivanprdg.model.dto.ClienteDto;
import com.ivanprdg.model.entity.Cliente;

public interface IClienteService {

    Cliente save(ClienteDto clienteDto);

    Cliente findById(Integer id);

    void delete(Cliente cliente); //No hay problema con que no sea dto porque miramos por id para eliminar

    boolean existsById(Integer id);
}
