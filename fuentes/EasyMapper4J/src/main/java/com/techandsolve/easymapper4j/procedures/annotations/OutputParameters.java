package com.techandsolve.easymapper4j.procedures.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Debe ser usada para declarar cada uno de los parametros de salida cuando se declara el procedimiento con la opcion.
 * withoutMetaDataAccess = true. Su uso es similar al de la anotacion OutputLobParameters.
 * @author Daniel Bustamante Ospina <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OutputParameters {
    
    /**
     * Especifica uno o mas parametros de salida.
     * @return 
     */
    OutputParameter[] value();    
}
