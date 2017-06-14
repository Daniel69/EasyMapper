package com.techandsolve.easymapper4j.procedures.annotations;

import com.techandsolve.easymapper4j.types.LobType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Es necesario usar esta anotacion cuando se tienen parametros de salida de tipo CLOB o BLOB.
 * Si se tiene un parametro de salida de tipo LOB y no es declarado mediante esta anotación, no se podra ejecutar
 * el procedimiento de forma correcta.
 * 
 * Cuando se tenga más de un parametro de salida de tipo LOB debera usarse la anotación {@link OutputLobParameters}
 * la cual permite declarar varios parametros de salida de tipo LOB.
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 *  &#064;StoredProcedure(name="SIMPLE_CLOB_VAL", packageName="PERSONAS_PKG")
 *  &#064;OutputLobParameter(name="OUT_HISTORICO_CLOB", type = LobType.CLOB)
 *  public class consultarHistoricoSP {    
 *  //..
 *  }
 * </pre>
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface OutputLobParameter {
    
    /**
     * Especifica el tipo de LOB que retorna la base de datos.
     * @return 
     */
    LobType type();
    
    /**
     * Especifica el nombre del parametro tal y como esta definido en el procedimiento en base de datos.
     * @return 
     */
    String name();
    
    
    /**
     * Es necesario especificar el indice del parametro cuando el procedimiento esta definido con la opcion withoutMetaDataAccess = true.
     * @return 
     */
    int paramIndex() default -1;     
}
