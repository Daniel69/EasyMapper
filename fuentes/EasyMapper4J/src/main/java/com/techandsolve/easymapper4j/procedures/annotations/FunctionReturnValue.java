package com.techandsolve.easymapper4j.procedures.annotations;

import com.techandsolve.easymapper4j.model.annotations.Field;
import com.techandsolve.easymapper4j.types.ReturnType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Esta anotación es necesaria para brindar una definición del tipo de retorno de una función almacenada.
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
public @interface FunctionReturnValue {
    
    /**
     * Inidica si la función retorna un valor individual (SCALAR) o una lista de resultados (CURSOR_REF).
     * @return 
     */
    ReturnType returnType();
    
    /**
     * Inidca la clase a la que se va a mapear el resultado o los resultados devueltos por la función.
     * @return 
     */
    Class<?> mappedClass();
    
    /**
     * Es usado cuando se requiere definir un mapeo personalizado.
     * Es util cuando no se tiene acceso al codigo fuente del objeto del modelo para poder incluir las anotaciones
     * de mapeo {@link Field} o se quiere definir un mapeo especifico para el procedimiento.
     * @return 
     */
    MapDefinition [] customMapDefinitions() default {};
    
    /**
     * Necesario especificar el tipo sql cuando el procedimiento esta definido con la opcion withoutMetaDataAccess = true.
     * @return 
     */
    int sqlType() default -1;    
}
