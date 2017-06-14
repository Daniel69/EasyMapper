package com.techandsolve.easymapper4j.procedures.annotations;

import com.techandsolve.easymapper4j.types.MappingType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Se usa para definir los parametros de entrada del procedimiento almacenado.
 * 
 * Para definir los parametros de entrada del procedimiento simplemente se anota(n) el o los campos que van a 
 * contener los valores de los parametros en la clase que define el procedimiento y se indica el nombre del parametro
 * tal y como fue definido en el procedimiento en base de datos mediante el atributo name().
 * 
 * Ejemplo de uso:
 * 
 * <pre>
 * &#064;StoredProcedure(name="INGRESAR_PERSONA", packageName="PERSONAS_PKG")
 * public class IngresarPersonaSP {
 *   
 *  &#064;InputParameter(name="P_NOMBRE")
 *  private String nombre;
 *  
 *  &#064;InputParameter(name="P_NOMBRE2")
 *  private String segundoNombre;
 *  
 *  &#064;InputParameter(name="P_APELLIDOS")
 *  private String apellidos;
 *  
 *  // Constructor, Getters y Setters
 *  }
 * </pre>
 * 
 * Es un requisito que las propiedades anotadas sigan el estandar JavaBean y proporcionen los correspondientes
 * metodos getter y setter, o por lo menos los getters. De lo contrario los valores de los parametros no podran 
 * ser extraidos al momento de realizar la ejecución del procedimiento ya que no se utiliza instrumentación del
 * bytecode.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InputParameter {
    
    /**
     * Nombre del parametro tal y como fue definido en el procedimiento almacenado.
     * @return 
     */
    String name();
    
    /**
     * El tipo del parametro de entrada, es necesario especificarlo cuando es de tipo CLOB o BLOB
     * @return 
     */
    MappingType type() default MappingType.DEFAULT;
    
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
