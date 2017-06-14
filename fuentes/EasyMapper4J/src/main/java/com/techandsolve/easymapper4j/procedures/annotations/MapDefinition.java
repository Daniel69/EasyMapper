package com.techandsolve.easymapper4j.procedures.annotations;

import com.techandsolve.easymapper4j.types.MappingType;

/**
 * Es usado cuando se requiere definir un mapeo personalizado.
 * Es util cuando no se tiene acceso al codigo fuente del objeto del modelo para poder incluir las anotaciones
 * de mapeo {@link Field} o se quiere definir un mapeo especifico para el procedimiento.
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 *  &#064;StoredProcedure(name="LISTAR_REGIONES", packageName="PERSONAS_PKG")
 *  &#064;OutputResultSet(name="REGIONES", mappedType=Regions.class,
 *    customMapDefinitions={
 *      &#064;MapDefinition(fieldName="REGION_NAME", propertyName="regionName"),
 *      &#064;MapDefinition(fieldName="REGION_ID", propertyName="regionId")
 *    }
 *  )
 *  public class ListarRegionesSP {    
 *  //..
 *  }
 * </pre>
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public @interface MapDefinition {
    
    /**
     * Nombre de la propiedad del javaBean objetivo del mapeo
     * @return 
     */
    String propertyName();
    
    /**
     * Nombre del campo en la lista de resultados
     * @return 
     */
    String fieldName();
    
    /**
     * Tipo de mapeo, para ver los diferentes tipos de mapeos, refierase a: {@link MappingType}
     * @return 
     */
    MappingType type() default MappingType.DEFAULT;
}
