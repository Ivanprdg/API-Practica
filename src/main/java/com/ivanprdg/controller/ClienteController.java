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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivanprdg.model.dto.ClienteDto;
import com.ivanprdg.model.entity.Cliente;
import com.ivanprdg.model.payload.MensajeResponse;
import com.ivanprdg.service.IClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {

        Cliente clienteSave = null;
        try {

            clienteSave = clienteService.save(clienteDto);
            logger.info("Creating cliente: " + clienteSave);

            clienteDto = ClienteDto.builder()
            .id_cliente(clienteSave.getId_cliente())
            .nombre(clienteSave.getNombre())
            .apellido(clienteSave.getApellido())
            .correo(clienteSave.getCorreo())
            .fecha_registro(clienteSave.getFecha_registro())
            .build();

            return  new ResponseEntity<>(MensajeResponse.builder()
            .mensaje("Creado correctamente")
            .object(clienteDto)
            .build(), HttpStatus.CREATED);

        } catch (DataAccessException exDt) {

            return new ResponseEntity<>(
                MensajeResponse.builder()
                .mensaje(exDt.getMessage()).
                object(null).
                build()
                , HttpStatus.METHOD_NOT_ALLOWED); //Devolvemos un mensaje de error
        }
    }

    @PutMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id) {

        /* Se realiza con el id ya que en el CRUD repository al guardar 
        si el id no existe lo crea igual */

        Cliente clienteUpdate = null;
        try {    
            //Verificamos si el registro que se intenta actualizar existe        
            if (clienteService.existsById(id)) {
                clienteDto.setId_cliente(id); //Seteamos el id porque el cliente se puede equivocar y enviar otro id en el body diferente al de la url
                clienteUpdate = clienteService.save(clienteDto);
                logger.info("Updating cliente: " + clienteUpdate);
    
                clienteDto = ClienteDto.builder()
                .id_cliente(clienteUpdate.getId_cliente())
                .nombre(clienteUpdate.getNombre())
                .apellido(clienteUpdate.getApellido())
                .correo(clienteUpdate.getCorreo())
                .fecha_registro(clienteUpdate.getFecha_registro())
                .build();
    
                return  new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Actualizado correctamente")
                .object(clienteDto)
                .build(), HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(
                    MensajeResponse.builder()
                    .mensaje("El registro que se intenta actualizar no existe").
                    object(null).
                    build()
                    , HttpStatus.NOT_FOUND); //Devolvemos un mensaje de error
            }

        } catch (DataAccessException exDt) {

            return new ResponseEntity<>(
                MensajeResponse.builder()
                .mensaje(exDt.getMessage()).
                object(null).
                build()
                , HttpStatus.METHOD_NOT_ALLOWED); //Devolvemos un mensaje de error
        }
        
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) { //ResponseEntity nos permite devolver un mensaje personalizado

        try {

            logger.info("Deleting cliente with id: " + id);
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt) {

            return new ResponseEntity<>(
                MensajeResponse.builder()
                .mensaje(exDt.getMessage()).
                object(null).
                build()
                , HttpStatus.METHOD_NOT_ALLOWED); //Devolvemos un mensaje de error
        }
    }

    @GetMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id) {

        Cliente cliente = clienteService.findById(id);
        logger.info("Showing cliente with id: " + id);

        if (cliente == null) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                .mensaje("El registro que se intenta buscar no existe").
                object(null).
                build()
                , HttpStatus.NOT_FOUND); //Devolvemos un mensaje de error
        }

        return new ResponseEntity<>(
            MensajeResponse.builder()
            .mensaje("Consulta exitosa").
            object(ClienteDto.builder()
            .id_cliente(cliente.getId_cliente())
            .nombre(cliente.getNombre())
            .apellido(cliente.getApellido())
            .correo(cliente.getCorreo())
            .fecha_registro(cliente.getFecha_registro())
            .build())
            , HttpStatus.OK);
    }

    @GetMapping("/clientes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showAll() {

        List<Cliente> clientesList = clienteService.listAll();
        logger.info("Showing clientes");

        if (clientesList == null) {
            return new ResponseEntity<>(
                MensajeResponse.builder()
                .mensaje("No hay registros").
                object(null).
                build()
                , HttpStatus.OK); //Devolvemos un mensaje de error
        }

        return new ResponseEntity<>(
            MensajeResponse.builder()
            .mensaje("Consulta exitosa").
            object(clientesList).
            build()
            , HttpStatus.OK);
    }
}
