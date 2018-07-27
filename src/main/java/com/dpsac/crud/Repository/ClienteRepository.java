package com.dpsac.crud.Repository;

import com.dpsac.crud.Model.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public interface ClienteRepository extends CrudRepository<Cliente,Integer> {

    /*
    List<Cliente> clientes = new ArrayList<>();

    public ClienteRepository(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ClienteRepository() {
        clientes.add(new Cliente(1,"cesar","medina",1));
        clientes.add(new Cliente(2,"juan","feliciano",2));
        clientes.add(new Cliente(3,"felix","solis",1));
    }

    public List<Cliente> getAllClientes(){
        return clientes;
    }

    public Cliente getClienteById(int id){
        Cliente cli = getAllClientes().stream().filter(c -> c.getId() == id).findAny().orElse(null);
        return cli;
    }

    public void deleteClienteById(int id){
        clientes.remove(getClienteById(id));
    }


    public void updateCliente(Cliente cliente) {

        clientes.set(clientes.indexOf(getClienteById(cliente.getId())),cliente);
    }

    public void insertCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    */
}
