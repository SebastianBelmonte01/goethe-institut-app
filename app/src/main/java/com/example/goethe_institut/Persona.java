package com.example.goethe_institut;

import java.util.Date;

public class Persona {
    private String ci;
    private String name;
    private String lastName;
    private  String direction;
    private String telefono;
    private String bornDate;
    private String usuario;
    private int password;
    private int admin;

    public Persona(String ci, String name, String lastName, String direction, String telefono, String bornDate, String usuario, int password, int admin) {
        this.ci = ci;
        this.name = name;
        this.lastName = lastName;
        this.direction = direction;
        this.telefono = telefono;
        this.bornDate = bornDate;
        this.usuario = usuario;
        this.password = password;
        this.admin = admin;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
