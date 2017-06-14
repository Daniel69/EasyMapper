package com.techandsolve.easymapper4j.descriptors;

import com.techandsolve.easymapper4j.types.MappingType;

/**
 * Describe el mapeo deseado entre uno de los campos resultado y una de las propiedades de un objeto JavaBean.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class FieldMappingDescriptor {
    private String fieldName;
    private String propertyName;
    private Class<?> mappedClass;
    private Class<?> propertyClass;
    private MappingType mappingType;

    /**
     * 
     * @return El tipo de mapeo a realizar
     */
    public MappingType getMappingType() {
        return mappingType;
    }

    public void setMappingType(MappingType mappingType) {
        this.mappingType = mappingType;
    }
    
    /**
     * 
     * @return El nombre que identifica al campo en el conjunto de resultados.
     */
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * 
     * @return La clase hacia la cual se va a realizar el mapeo.
     */
    public Class<?> getMappedClass() {
        return mappedClass;
    }

    public void setMappedClass(Class<?> mappedClass) {
        this.mappedClass = mappedClass;
    }

    /**
     * 
     * @return El tipo de dato objetivo al cual se realiza la conversion del tipo de dato del resultado.
     */
    public Class<?> getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(Class<?> propertyClass) {
        this.propertyClass = propertyClass;
    }

    /**
     * 
     * @return El nombre de la propiedad del JavaBean al cual se va a 'setear' el valor mapeado.
     */
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }  
}
