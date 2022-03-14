package com.example.goethe_institut;

import android.graphics.Bitmap;

public class ModeloItemImagen {
    Bitmap imagen;
    String titulo, detalle;

    public ModeloItemImagen(Bitmap imagen, String titulo, String detalle) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.detalle = detalle;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
