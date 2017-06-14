/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techandsolve.easymapper4j.testSP;

import com.techandsolve.easymapper4j.model.annotations.Field;
import com.techandsolve.easymapper4j.model.annotations.MappedType;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */


public class Persona {
    private static final Logger logger = Logger.getLogger("loggerModelo");
 
    @Field(name="NOMBRE")
    private String nombre;
    
    @Field(name="NOMBRE2")
    private String segundoNombre;
    
    @Field(name="APELLIDO")
    private String apellido;
    
    @Field(name="CEDULA")
    private String cedula;
    
    @Field(name="EMAIL")
    private String email;
    
    @Field(name="FECHA_NACIMIENTO")
    private Date fechaNacimiento; 

    public Persona() {
        logger.log(Level.INFO, "Creando instancia de: {0}", getClass().getName());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    @Override
    public String toString() {
        return String.format("Persona: %s %s %s, Email: %s", nombre, segundoNombre, apellido, email);
    }
    
    
}
