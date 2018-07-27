package com.dpsac.crud.Model;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "ROL")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ROL")
    private long idRol;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "DESCRIPCION", length = 50)
    private NombreRol nombreRol;
}