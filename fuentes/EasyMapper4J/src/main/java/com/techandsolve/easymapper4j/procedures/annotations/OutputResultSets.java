package com.techandsolve.easymapper4j.procedures.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Permite declarar uno o más parametro de salida que retornan cursores y los cuales deben mapearse a objetos del
 * modelo de la aplicación cliente. Ver {@link OutputResultSet}.
 * 
 * <p>Ejemplo de uso: </p>
 * <pre>
 *  &#064;StoredProcedure(name="ENCONTRAR_PERSONAS_CIUDADES", packageName="PERSONAS_PKG")
 *  &#064;OutputResultSets({
 *      &#064;OutputResultSet(name="PERSONAS_C", mappedClass=Persona.class),
 *      &#064;OutputResultSet(name="CIUDADES_C", mappedClass=Ciudad.class)
 *  })
 *  public class consultarPersonasCiudadesSP {    
 *  //..
 *  }
 * </pre>
 * @author Daniel Bustamante <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OutputResultSets {
    
    /**
     * Define uno o más OutputResultSet.
     * @return 
     */
    OutputResultSet[] value();
}
