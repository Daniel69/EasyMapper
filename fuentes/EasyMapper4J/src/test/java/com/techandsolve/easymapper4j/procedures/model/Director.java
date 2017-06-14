/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techandsolve.easymapper4j.procedures.model;

import com.techandsolve.easymapper4j.types.MappingType;
import com.techandsolve.easymapper4j.model.annotations.Field;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class Director {
    
    @Field(name="sad_nombre")
    private String nombre;
    
    @Field(name="tel__")
    private String telefono;
    
    @Field(name= "dato_", type= MappingType.CLOB)
    private String dato;

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
