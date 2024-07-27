package com.ivanprdg.model.dao;
import com.ivanprdg.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {
    
}
