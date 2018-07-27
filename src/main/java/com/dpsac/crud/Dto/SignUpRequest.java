package com.dpsac.crud.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequest {

    private Integer codigoUsuario;

    private String apePaterno;

    private String apeMaterno;

    private String nombres;

    private String nombreUsuario;

    private String email;

    private Date fechaInicio;

    private Date fechaFin;

    private Integer estado;

    private String contraseña;
}