package com.heladeria.dto;

public class LoginResponse {

    private String token;
    private Long id;
    private String nombre;
    private String usuario;
    private String rol;
    private String mensaje;

    public LoginResponse() {
    }

    public LoginResponse(String token,
                         Long id,
                         String nombre,
                         String usuario,
                         String rol,
                         String mensaje) {

        this.token = token;
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.rol = rol;
        this.mensaje = mensaje;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}