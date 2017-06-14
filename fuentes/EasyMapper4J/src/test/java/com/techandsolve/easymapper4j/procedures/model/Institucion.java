/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techandsolve.easymapper4j.procedures.model;

import com.techandsolve.easymapper4j.types.MappingType;
import com.techandsolve.easymapper4j.model.annotations.Embedded;
import com.techandsolve.easymapper4j.model.annotations.Field;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class Institucion {
    
    @Field(name="_nom")
    private String nombre;
    
    @Field(name="dire__")
    private String direccion;
    
    @Field(name="sad", type= MappingType.BLOB)
    private String historial;
    
    @Embedded
    private Director director = new Director();

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
