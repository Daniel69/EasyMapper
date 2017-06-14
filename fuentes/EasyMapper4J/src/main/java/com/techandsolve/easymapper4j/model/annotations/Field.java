package com.techandsolve.easymapper4j.model.annotations;

import com.techandsolve.easymapper4j.types.MappingType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * La finalidad de esta anotación es que sea usada en las clases del modelo de la aplicación cliente para definir 
 * el mapeo correspondiente entre las propiedades del objeto objetivo y los campos de los conjuntos de resultados
 * del procedimiento almacenado.
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 *  public class Persona {
 * 
 *    &#064;Field(name="NOMBRE")
 *    private String nombre;
 * 
 *    &#064;Field(name="NOMBRE2")
 *    private String segundoNombre;
 * 
 *    &#064;Field(name="FECHA_NACIMIENTO")
 *    private Date fechaNacimiento;
 * 
 *    // Constructor, gettter, setters...
 *  }
 * 
 * </pre>
 * 
 * Es un requisito que las propiedades anotadas sigan el estandar JavaBean y proporcionen los correspondientes
 * metodos getters y setters. De lo contrario los valores de las propiedades no podran ser seteados al momento de 
 * realizar el mapeo del resultado del procedimiento ya que no se utiliza instrumentación del bytecode.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Field {
    
    /**
     * Indica el nombre del campo (en el resultSet, Cursor o Map de resultados) del cual debe obtenerse el valor 
     * para la propiedad anotada del objeto.
     * @return 
     */
    String name();
    
    /**
     * Indica el tipo de mapeo a realizar, el MappingType.DEFAULT es el adecuado la mayoria de los casos.
     * Cuando se requieren mapeos desde tipos Blob o Clob, se debe utilizar MappingType.BLOB o MappingType.CLOB
     * respectivamente.
     * @return 
     */
    MappingType type() default MappingType.DEFAULT;
}
