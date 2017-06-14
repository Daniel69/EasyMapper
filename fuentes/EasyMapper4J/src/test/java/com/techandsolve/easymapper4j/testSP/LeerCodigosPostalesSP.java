package com.techandsolve.easymapper4j.testSP;

import com.techandsolve.easymapper4j.procedures.annotations.FunctionReturnValue;
import com.techandsolve.easymapper4j.procedures.annotations.StoredProcedure;
import com.techandsolve.easymapper4j.types.ReturnType;


@StoredProcedure(name="f_lee_codigos_postales", packageName="em_k_web_colectivos_csg", isFunction=true, schemaName="TRON2000")
@FunctionReturnValue(returnType= ReturnType.CURSOR_REF, mappedClass=Ciudad.class)
public class LeerCodigosPostalesSP {
    
}
