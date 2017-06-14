package com.techandsolve.easymapper4j.procedures.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Esta anotación es la base de la definición de los procedimientos almacenados mediantes meta datos.
 * Es la unica anotación obligatoria para que una clase sea reconocida como la definción de un procedimiento
 * almacenado.</p>
 * 
 * Con esta anotación se defines tres datos fundamentales que son: 
 * <ol>
 *  <li>El nombre de la función o procedimiento almacenado.</li>
 *  <li>El nombre del paquete en el que se encuentra la función o procedimiento, en caso de encontrarse en un paquete.</li>
 *  <li>Un valor booleano que indica si se esta definiendo una función o un procedimiento como tal.</li>
 * </ol>
 * 
 * En caso de que se especifique que se esta definiendo una función, se hace tambien obligatoria la presencia de la
 * anotación {@link FunctionReturnValue}.
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 *  &#064;StoredProcedure(name="encontrar_persona", packageName="PERSONAS_PKG", isFunction=true)
 *  &#064;FunctionReturnValue(returnType= ReturnType.CURSOR_REF, mappedClass=Persona.class)
 *  public class ConsultarPersonaSP {
 *   
 *   &#064;InputParameter(name="PV_CEDULA")
 *   private String cedula;
 * 
 *   // Getters, setters...
 *  }
 * </pre>
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StoredProcedure {
    
    /**
     * Especifica el nombre del procedimiento almacenado tal y como esta definido en base de datos.
     * @return 
     */
    String name();
    
    /**
     * Especifica el nombre del paquete en el que esta definido el procedimiento almacenado en caso de estar 
     * definido en un paquete.
     * @return 
     */
    String packageName() default "";
    
    /**
     * Indica si lo que se esta definendo es como tal una función (retorna un valor) o un procedimiento.
     * @return 
     */
    boolean isFunction() default false;
    
    /**
     * Indica el nombre del esquema (Usuario) dueño del procedimiento almacenado.
     * @return 
     */
    String schemaName() default "";
    
    /**
     * Indica que debe construirse la llamada al procedimiento sin acceder a los metadatos en BD, es decir no se realiza
     * ninguna automatizacion de tareas y debe definirse el procedimiento en forma mucho mas detallada.
     * @return 
     */
    boolean withoutMetaDataAccess() default false;
}
