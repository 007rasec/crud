package com.dpsac.crud.Service;

import com.dpsac.crud.Model.Cliente;
import com.dpsac.crud.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes(){
        return (List<Cliente>)clienteRepository.findAll();
    }

    public Cliente getClienteById(int id){
        return clienteRepository.findById(id).get();
    }

    public Cliente insertCliente(Cliente cliente){
        clienteRepository.save(cliente);
        return cliente;
    }

    public Integer updateCliente(Cliente cliente){
        try{
            clienteRepository.save(cliente);
            return 1;
        }catch(Exception ex){
            return -1;
        }

    }

    public Integer deleteCliente(Cliente cliente){
        try{
            clienteRepository.delete(cliente);
            return 1;
        }catch (Exception ex){
            return -1;
        }
    }
    /*
    public List<Cliente> getAllClientes(){
        return clienteRepository.getAllClientes();
    }

    public Cliente getClienteById(int id){
        return clienteRepository.getClienteById(id);
    }

    public void deleteClienteById(int id) {
        clienteRepository.deleteClienteById(id);
    }

    public void updateCliente(Cliente cliente) {
        clienteRepository.updateCliente(cliente);
    }

    public void insertCliente(Cliente cliente) {
        clienteRepository.insertCliente(cliente);
    }

    */
}
