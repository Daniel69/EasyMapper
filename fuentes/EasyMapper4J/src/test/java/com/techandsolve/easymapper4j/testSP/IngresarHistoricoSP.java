
package com.techandsolve.easymapper4j.testSP;

import com.techandsolve.easymapper4j.procedures.annotations.InputParameter;
import com.techandsolve.easymapper4j.procedures.annotations.StoredProcedure;
import com.techandsolve.easymapper4j.types.MappingType;


@StoredProcedure(name="ingresar_historico", packageName="PERSONAS_PKG")
public class IngresarHistoricoSP {
    
    @InputParameter(name="NOMBRE_HIST")
    private String nombre;
    
    @InputParameter(name="HISTORICO_DATA", type=MappingType.CLOB)
    private String historial;

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
