package com.example.recycleruve;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class Pelicula {
    //atributos
    private String nombre;
    private String imagen;
    private String url;
    private String category;
    private String timerStart;

    //constructor
    public Pelicula(String nombre, String imagen, String url, String category) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.url = url;
        this.category=category;
    }
    public Pelicula(){

    }

    //getYsetters
    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTimerStart() {
        return timerStart;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTimerStart(String timerStart) {
        this.timerStart = timerStart;
    }

    public String getUrl() {
        return url;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
