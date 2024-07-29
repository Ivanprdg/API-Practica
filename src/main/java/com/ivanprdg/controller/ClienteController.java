package com.ivanprdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivanprdg.model.dto.ClienteDto;
import com.ivanprdg.model.entity.Cliente;
import com.ivanprdg.service.ICliente;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ICliente clienteService;

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto create(@RequestBody ClienteDto clienteDto) {

        Cliente clienteSave = clienteService.save(clienteDto);
        logger.info("Creating cliente: " + clienteSave);

        return ClienteDto.builder()
        .id_cliente(clienteSave.getId_cliente())
        .nombre(clienteSave.getNombre())
        .apellido(clienteSave.getApellido())
        .correo(clienteSave.getCorreo())
        .fecha_registro(clienteSave.getFecha_registro())
        .build();
    }

    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto update(@RequestBody ClienteDto clienteDto) {

        Cliente clienteUpdate = clienteService.save(clienteDto);
        logger.info("Creating cliente: " + clienteUpdate);
        
        return ClienteDto.builder()
        .id_cliente(clienteUpdate.getId_cliente())
        .nombre(clienteUpdate.getNombre())
        .apellido(clienteUpdate.getApellido())
        .correo(clienteUpdate.getCorreo())
        .fecha_registro(clienteUpdate.getFecha_registro())
        .build();
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) { //ResponseEntity nos permite devolver un mensaje personalizado

        Map<String, Object> response = new HashMap<>(); //Mapa para almacenar la respuesta
        try {

            logger.info("Deleting cliente with id: " + id);
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt) {

            response.put("mensaje", exDt.getMessage());
            response.put("cliente", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); //Devolvemos un mensaje de error
        }
    }

    @GetMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto showById(@PathVariable Integer id) {

        Cliente cliente = clienteService.findById(id);
        logger.info("Showing cliente with id: " + id);

        return ClienteDto.builder()
        .id_cliente(cliente.getId_cliente())
        .nombre(cliente.getNombre())
        .apellido(cliente.getApellido())
        .correo(cliente.getCorreo())
        .fecha_registro(cliente.getFecha_registro())
        .build();
    }
}
