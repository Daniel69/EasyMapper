package com.techandsolve.easymapper4j.testSP;

import com.techandsolve.easymapper4j.model.annotations.Field;
import com.techandsolve.easymapper4j.types.MappingType;


public class Historico {
    
    @Field(name="NOMBRE")
    private String nombre;
    
    @Field(name="HISTORICO", type= MappingType.CLOB)
    private String historia;

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
