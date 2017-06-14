/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techandsolve.easymapper4j.procedures.model;

import com.techandsolve.easymapper4j.types.MappingType;
import com.techandsolve.easymapper4j.model.annotations.Embedded;
import com.techandsolve.easymapper4j.model.annotations.Field;
import com.techandsolve.easymapper4j.model.annotations.MappedType;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
@MappedType
public class Persona {
    
    @Field(name="NOMbre_db")
    private String nombre;
    
    @Field(name="ape__")
    private String apellido;
    
    @Field(name="hist__", type = MappingType.CLOB)
    private String historial;
    
    @Embedded
    private Institucion institucion;
    
    @Embedded
    private Empresa empresa;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
