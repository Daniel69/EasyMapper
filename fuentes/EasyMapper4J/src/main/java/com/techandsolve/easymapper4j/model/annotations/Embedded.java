package com.techandsolve.easymapper4j.model.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Esta anotacion define que debe realizarce una busqueda de reglas de mapeo al interior de la clase de 
 * la propiedad anotada.
 * Es util cuando se desea mapear de los resultados hacia propiedades anidadas en el modelo de datos.
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
 *    &#064;Embedded
 *    private Institucion institucion = new Institucion();
 * 
 *    // Constructor, gettter, setters...
 *  }
 * </pre>
 * 
 * No sobra decir que 'institucion' debe tener los getter y setter correspondientes para poder realizar el mapeo.
 * Y no puede ser null al momento de realizar el mapeo, por eso es conveniente inicializar el objeto en su declaracion.
 * @author Daniel Bustamante <daniel.bustamante>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Embedded {
    
}
