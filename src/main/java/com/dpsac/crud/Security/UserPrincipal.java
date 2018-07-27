package com.dpsac.crud.Security;

import com.dpsac.crud.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private Integer codigoUsuario;

    private String apePaterno;

    private String apeMaterno;

    private String nombres;

    private String nombreUsuario;

    @JsonIgnore
    private String email;

    private Date fechaInicio;

    private Date fechaFin;

    private Integer estado;

    @JsonIgnore
    private String contraseña;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Integer codigoUsuario, String apePaterno, String apeMaterno, String nombres, String nombreUsuario, String email, String contraseña, Date fechaInicio, Date fechaFin, Collection<? extends GrantedAuthority> authorities) {
        this.codigoUsuario = codigoUsuario;
        this.apeMaterno = apeMaterno;
        this.apePaterno = apePaterno;
        this.nombres = nombres;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.contraseña = contraseña;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(role
                -> new SimpleGrantedAuthority(role.getNombreRol().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                usuario.getCodigoUsuario(),
                usuario.getApePaterno(),
                usuario.getApeMaterno(),
                usuario.getNombres(),
                usuario.getNombreUsuario(),
                usuario.getEmail(),
                usuario.getContraseña(),
                usuario.getFechaInicio(),
                usuario.getFechaFin(),
                authorities
        );
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public String getEmail() {
        return email;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Integer getEstado() {
        return estado;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public String getPassword() {
        return contraseña;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(codigoUsuario, that.codigoUsuario);
    }

    @Override
    public int hashCode() {

        return Objects.hash(codigoUsuario);
    }
}