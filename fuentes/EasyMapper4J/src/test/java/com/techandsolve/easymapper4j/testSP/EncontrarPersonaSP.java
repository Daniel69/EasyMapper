package com.techandsolve.easymapper4j.testSP;


import com.techandsolve.easymapper4j.types.ReturnType;
import com.techandsolve.easymapper4j.procedures.annotations.FunctionReturnValue;
import com.techandsolve.easymapper4j.procedures.annotations.InputParameter;
import com.techandsolve.easymapper4j.procedures.annotations.OutputLobParameter;
import com.techandsolve.easymapper4j.procedures.annotations.OutputParameter;
import com.techandsolve.easymapper4j.procedures.annotations.OutputParameters;
import com.techandsolve.easymapper4j.procedures.annotations.OutputResultSet;
import com.techandsolve.easymapper4j.procedures.annotations.StoredProcedure;
import java.sql.Types;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
@StoredProcedure(name="encontrar_persona", packageName="PERSONAS_PKG", isFunction=true, withoutMetaDataAccess=true)
@FunctionReturnValue(returnType= ReturnType.CURSOR_REF, mappedClass=Persona.class)
@OutputParameters({
    @OutputParameter(name= "COD_ERROR", paramIndex=2, sqlType=Types.NUMERIC),
    @OutputParameter(name= "MSJ_ERROR", paramIndex=3, sqlType=Types.VARCHAR)
})
public class EncontrarPersonaSP {
    
    @InputParameter(name="PV_CEDULA", paramIndex= 1, sqlType=Types.VARCHAR)
    private String cedula;

    public EncontrarPersonaSP(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
