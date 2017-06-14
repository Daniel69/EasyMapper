package com.techandsolve.easymapper4j.testSP;

import com.techandsolve.easymapper4j.types.LobType;
import com.techandsolve.easymapper4j.types.ReturnType;
import com.techandsolve.easymapper4j.procedures.annotations.FunctionReturnValue;
import com.techandsolve.easymapper4j.procedures.annotations.InputParameter;
import com.techandsolve.easymapper4j.procedures.annotations.OutputLobParameter;
import com.techandsolve.easymapper4j.procedures.annotations.OutputResultSet;
import com.techandsolve.easymapper4j.procedures.annotations.StoredProcedure;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
@StoredProcedure(name="SIMPLE_CLOB_VAL", packageName="PERSONAS_PKG", isFunction=true)
@FunctionReturnValue(returnType= ReturnType.CURSOR_REF, mappedClass=PersonaEspecial.class)
@OutputResultSet(name=ListaPersonasRegionesClob.REGIONES_C, mappedClass=Regions.class)
@OutputLobParameter(name=ListaPersonasRegionesClob.OUT_HISTORICO_CLOB, type = LobType.CLOB)
public class ListaPersonasRegionesClobHerencia {
    
    public static final String REGIONES_C = "REGIONES_C";
    public static final String COD_ENTRADA = "COD_ENTRADA";
    public static final String OUT_HISTORICO_CLOB = "HISTORICO_CLOB";
    
    @InputParameter(name=COD_ENTRADA)
    private String parametro1;
    
    public String getParametro1() {
        return parametro1;
    }

    public void setParametro1(String parametro1) {
        this.parametro1 = parametro1;
    }   
}