package com.techandsolve.easymapper4j.procedures.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Esta anotación declara un parametro de salida que retorna una lista de resultados (Cursor)
 * y proporciona la información necesaria para realizar el mapeo de los resultados devueltos en el parametro de 
 * salida a objetos del modelo de la aplicación cliente.</p>
 * 
 * <p>Cuando no se proporcionan definiciones de mapeo personalizadas, se utilizan las anotaciones en la clase objetivo
 * (mappedClass) para realizar el mapeo.</p>
 * 
 * <p>Las definiciones de mapeo personalizadas (customMapDefinitions) tienen prioridad, es decir si se especifican,
 * se utilizaran estas reglas para el mapeo, ingnorando las anotaciones que pudiera tener la clase objetivo (mappedClass).</p>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 *  &#064;StoredProcedure(name="ENCONTRAR_PERSONAS", packageName="PERSONAS_PKG")
 *  &#064;OutputResultSet(name="PERSONAS_C", mappedClass=Persona.class)
 *  public class ConsultarPersonasSP {
 *   // ...
 *  }
 * </pre>
 * 
 * Cuando se debe declarar más un parametro de salida debe usarse la anotación {@link OutputResultSets} la cual 
 * permite declarar varios OutputResultSets.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OutputResultSet {
    
    /**
     * Especifica el nombre del parametro de salida tal y como esta definido en el procedimiento almacenado.
     * @return 
     */
    String name();
    
    /**
     * Especifica la clase objetivo del mapeo
     * @return 
     */
    Class<?> mappedClass();
    
    /**
     * Definiciones especificas de mapeo, en caso de especificarse una o más reglas, se utilizaran estas en lugar
     * de cualquier anotación que pudiera o no tener la clase objetivo (mappedClass).
     * @return 
     */
    MapDefinition [] customMapDefinitions() default {};
    
    /**
     * Es necesario especificar el indice del parametro cuando el procedimiento esta definido con la opcion withoutMetaDataAccess = true.
     * @return 
     */
    int paramIndex() default -1;    
}
