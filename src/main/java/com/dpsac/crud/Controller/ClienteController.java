package com.dpsac.crud.Controller;

import com.dpsac.crud.Model.Cliente;
import com.dpsac.crud.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> clientes = clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") int id){
        Cliente cliente = clienteService.getClienteById(id);
        if(cliente!=null){
            System.out.println("distinto de null");
            return new ResponseEntity<>(cliente,HttpStatus.OK );
        }
        else{
            return new ResponseEntity<>(new Cliente(), HttpStatus.NO_CONTENT );
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> insertCliente(@RequestBody Cliente cliente){
        Cliente c = clienteService.insertCliente(cliente);
        return new ResponseEntity<Cliente>( c ,HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> updateCliente(@RequestBody Cliente cliente){
        Integer i = clienteService.updateCliente(cliente);
        if(i>0)
            return new ResponseEntity<Integer>( i ,HttpStatus.OK);
        else
            return new ResponseEntity<Integer>( i ,HttpStatus.OK);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> deleteCliente(@RequestBody Cliente cliente){
        Integer i = clienteService.deleteCliente(cliente);
        if(i>0)
            return new ResponseEntity<Integer>(i,HttpStatus.OK);
        else
            return new ResponseEntity<Integer>(i,HttpStatus.BAD_REQUEST);
    }

    /*
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Cliente> getAllClientes(){
        return clienteService.getAllClientes();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Cliente getClienteById(@PathVariable("id") int id){
        return clienteService.getClienteById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteClienteById(@PathVariable("id") int id){
        clienteService.deleteClienteById(id);
    }

    @RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCliente(@RequestBody Cliente cliente){
        clienteService.updateCliente(cliente);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertCliente(@RequestBody Cliente cliente){
        clienteService.insertCliente(cliente);
    }
    */

}
