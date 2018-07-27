package com.dpsac.crud.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USUARIO", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "NOMBRE_USUARIO"
        }),
        @UniqueConstraint(columnNames = {
                "EMAIL"
        })
})
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer codigoUsuario;
    @Column(name = "APE_PATERNO", length = 50)
    private String apePaterno;
    @Column(name = "APE_MATERNO", length = 50)
    private String apeMaterno;
    @Column(name = "NOMBRES", length = 100)
    private String nombres;
    @Column(name = "NOMBRE_USUARIO", length = 50)
    private String nombreUsuario;
    @Column(name = "PASS", length = 1000)
    private String contraseña;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_FIN")
    private Date fechaFin;
    @Column(name = "EMAIL", length = 100)
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ROL_USUARIO",
            joinColumns = @JoinColumn(name = "ID_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
    private Set<Rol> roles = new HashSet<>();
    @Column(name = "ESTADO")
    private Integer estado;

    public Usuario(String apePaterno, String apeMaterno, String nombres, String nombreUsuario, String contraseña, Date fechaInicio, Date fechaFin, String email, Integer estado) {
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.nombres = nombres;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.email = email;
        this.estado = estado;
    }
}
