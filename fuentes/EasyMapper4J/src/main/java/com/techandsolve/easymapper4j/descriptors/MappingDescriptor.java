package com.techandsolve.easymapper4j.descriptors;

import java.util.ArrayList;
import java.util.List;

/**
 * Los objetos de esta clase son descriptores de mapeo, los cuales definen las reglas a aplicar al momento de 
 * mapear los resultados devueltos por el procedimiento a objetos del modelo de datos de la aplicación.
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class MappingDescriptor {
    private Class<?> targetClass;
    private List<FieldMappingDescriptor> fieldMappings = new ArrayList<FieldMappingDescriptor>();

    public List<FieldMappingDescriptor> getFieldMappings() {
        return fieldMappings;
    }
    
    /**
     * Agrega una regla de mapeo que define la relacion entre uno de los campos resultado y una de las propiedades
     * del ojeto objetivo del mapeo.
     * @param fieldMapping 
     */
    public void addFieldMapping(FieldMappingDescriptor fieldMapping){
        fieldMappings.add(fieldMapping);
    }

    /**
     * Define las reglas de mapeo entre los campos resultado y las propiedades del objeto objetivo del mapeo.
     * @param fieldMappings 
     */
    public void setFieldMappings(List<FieldMappingDescriptor> fieldMappings) {
        this.fieldMappings = fieldMappings;
    }
    
    /**
     * 
     * @param targetClass Clase objetivo del mapeo.
     */
    public MappingDescriptor(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * Clase objetivo del mapeo
     * @return 
     */
    public Class<?> getTargetClass() {
        return targetClass;
    }

    /**
     * Clase objetivo del mapeo.
     * @param targetClass 
     */
    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }
}
