package com.example.goethe_institut;

import android.graphics.Bitmap;

public class Book {
    private String codigo;
    private String titulo;
    private String autor;
    private int cantidad;
    private String direccion;
    private Bitmap image;

    public Book(String codigo, String titulo, String autor, int cantidad, String direccion, Bitmap image) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.image = image;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Book{" +
                "codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", cantidad=" + cantidad +
                ", direccion='" + direccion + '\'' +
                ", image=" + image +
                '}';
    }
}
