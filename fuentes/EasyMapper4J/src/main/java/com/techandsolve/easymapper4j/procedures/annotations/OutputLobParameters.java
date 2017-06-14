package com.techandsolve.easymapper4j.procedures.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Cuando se tenga más de un parametro de salida de tipo LOB debera usarse esta anotación ya que permite declarar 
 * varios parametros de salida de tipo LOB.
 * 
 * <p>Ejemplo de uso: </p>
 * <pre>
 *  &#064;StoredProcedure(name="SIMPLE_CLOB_VAL", packageName="PERSONAS_PKG")
 *  &#064;OutputLobParameters({
 *      &#064;OutputLobParameter(name="SALIDA_1", type = LobType.CLOB),
 *      &#064;OutputLobParameter(name="SALIDA_2", type = LobType.BLOB)
 *  })
 *  public class consultarHistoricoSP {    
 *  //..
 *  }
 * </pre>
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface OutputLobParameters {
    
    /**
     * Especifica uno o mas parametros de salida tipo LOB.
     * @return 
     */
    OutputLobParameter[] value();
}
