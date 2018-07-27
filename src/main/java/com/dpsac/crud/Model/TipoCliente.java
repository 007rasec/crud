package com.dpsac.crud.Model;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "Tipo_Cliente")
@Data
public class TipoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdTipo")
    private int idTipo;

    @Column(name="Descripcion")
    private String descripcion;

    @Column(name="descripcion2")
    @NonNull
    private String descripcion2;

    @Column(name="Valor")
    private double valor;

    public TipoCliente(int idTipo, String descripcion, String descripcion2, double valor) {
        this.idTipo = idTipo;
        this.descripcion = descripcion;
        this.descripcion2 = descripcion2;
        this.valor = valor;
    }

}
