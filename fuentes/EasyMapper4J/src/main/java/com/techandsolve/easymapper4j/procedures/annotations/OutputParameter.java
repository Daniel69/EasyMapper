package com.techandsolve.easymapper4j.procedures.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Es necesario usar esta anotacion cuando se declara el procedimiento con la opcion withoutMetaDataAccess = true.
 * Para declarar cada uno de los parametros de salida del procedimiento, que no sean del tipo LOB o cursores. (Para ellos se debe 
 * usar la anotacion OutputLobParameter y OutputResultSet respectivamente).
 * @author Daniel Bustamante Ospina <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface OutputParameter {
    
    /**
     * Especifica el nombre del parametro tal y como esta definido en el procedimiento en base de datos.
     * @return 
     */
    String name();
    
    /**
     * Necesario especificar el tipo sql cuando el procedimiento esta definido con la opcion withoutMetaDataAccess = true.
     * @return 
     */
    int sqlType() default -1;
    
    /**
     * Es necesario especificar el indice del parametro cuando el procedimiento esta definido con la opcion withoutMetaDataAccess = true.
     * @return 
     */
    int paramIndex() default -1;     
}
