/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techandsolve.easymapper4j.mapping;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class ClaseMapear {
   
    private String nombre;
    private String fechaString;
    private String numeroString;
    
    private Integer numero;
    private BigDecimal numeroGrande;
    
    private Date fecha;
    
    private double numeroFlotante;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public double getNumeroFlotante() {
        return numeroFlotante;
    }

    public void setNumeroFlotante(double numeroFlotante) {
        this.numeroFlotante = numeroFlotante;
    }

    public BigDecimal getNumeroGrande() {
        return numeroGrande;
    }

    public void setNumeroGrande(BigDecimal numeroGrande) {
        this.numeroGrande = numeroGrande;
    }

    public String getNumeroString() {
        return numeroString;
    }

    public void setNumeroString(String numeroString) {
        this.numeroString = numeroString;
    }
        
}
