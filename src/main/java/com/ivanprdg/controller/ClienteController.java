package com.ivanprdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanprdg.model.entity.Cliente;
import com.ivanprdg.service.ICliente;

//Controlador de solicitudes HTTP
@RestController
//Se utiliza para asignar solicitudes web a métodos o clases del controlador
@RequestMapping("/api/v1") //Se asigna una URL base
public class ClienteController {

    //Aqui se implementarán los métodos para el CRUD de clientes

    //Llamamos al servicio
    @Autowired
    private ICliente clienteService;

    @PostMapping("cliente") //Los create siempre serán POST
    public Cliente create(@RequestBody Cliente cliente) { //RequestBody se utiliza para recibir un objeto JSON
        //Método para crear un cliente
        return clienteService.save(cliente);
    }

    @PutMapping("cliente") //Los update siempre serán PUT
    public Cliente update(@RequestBody Cliente cliente) {
        //Método para actualizar un cliente
        return clienteService.save(cliente);
    }

    @DeleteMapping("cliente/{id}") //Los delete siempre serán DELETE
    public void delete(@PathVariable int id) {
        //Método para eliminar un cliente
        Cliente clienteDelete = clienteService.findById(id);
        clienteService.delete(clienteDelete);
    }

    @GetMapping("cliente/{id}") //Los read siempre serán GET
    public Cliente showById(@PathVariable int id) {
        //Método para mostrar un cliente por su id
        return clienteService.findById(id);
    }

}
