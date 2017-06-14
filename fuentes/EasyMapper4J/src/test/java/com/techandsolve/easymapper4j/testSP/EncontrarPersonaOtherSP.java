package com.techandsolve.easymapper4j.testSP;


import com.techandsolve.easymapper4j.types.ReturnType;
import com.techandsolve.easymapper4j.procedures.annotations.FunctionReturnValue;
import com.techandsolve.easymapper4j.procedures.annotations.InputParameter;
import com.techandsolve.easymapper4j.procedures.annotations.StoredProcedure;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
@StoredProcedure(name="encontrar_persona", packageName="PERSONAS_PKG", isFunction=true)
@FunctionReturnValue(returnType= ReturnType.CURSOR_REF, mappedClass=Persona.class)
public class EncontrarPersonaOtherSP {
    
    @InputParameter(name="PV_CEDULA")
    private String cedula;

    public EncontrarPersonaOtherSP(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
