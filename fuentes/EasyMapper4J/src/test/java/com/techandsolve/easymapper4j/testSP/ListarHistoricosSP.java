package com.techandsolve.easymapper4j.testSP;

import com.techandsolve.easymapper4j.procedures.annotations.FunctionReturnValue;
import com.techandsolve.easymapper4j.procedures.annotations.InputParameter;
import com.techandsolve.easymapper4j.procedures.annotations.StoredProcedure;
import com.techandsolve.easymapper4j.types.ReturnType;

@StoredProcedure(name="LISTAR_HISTORICOS", packageName="PERSONAS_PKG", isFunction=true)
@FunctionReturnValue(returnType= ReturnType.CURSOR_REF, mappedClass=Historico.class)
public class ListarHistoricosSP {
    
    public static final String COD_ERROR = "COD_ERROR";
    public static final String MSJ_ERROR = "MSJ_ERROR";
    
    @InputParameter(name="cod_consulta")
    private Integer codConsulta;

    public ListarHistoricosSP(Integer codConsulta) {
        this.codConsulta = codConsulta;
    }

    public Integer getCodConsulta() {
        return codConsulta;
    }

    public void setCodConsulta(Integer codConsulta) {
        this.codConsulta = codConsulta;
    }
}
