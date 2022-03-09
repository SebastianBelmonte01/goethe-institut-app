package com.example.goethe_institut;

import java.util.Date;

public class Persona {
    private int ci;
    private String name;
    private String lastName;
    private  String direction;
    private int telefono;
    private Date bornDate;
    private String usuario;
    private int password;
    private boolean admin;

    public Persona(int ci, String name, String lastName, String direction, int telefono, Date bornDate, String usuario, int password, boolean admin) {
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

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


}
