package com.techandsolve.easymapper4j.mapping;

import com.techandsolve.easymapper4j.descriptors.FieldMappingDescriptor;
import com.techandsolve.easymapper4j.descriptors.MappingDescriptor;
import com.techandsolve.easymapper4j.exceptions.ErrorMessages;
import com.techandsolve.easymapper4j.exceptions.InvalidMappingDeclaration;
import com.techandsolve.easymapper4j.model.annotations.Embedded;
import com.techandsolve.easymapper4j.procedures.annotations.MapDefinition;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Clase encargada de crear los descriptores de mapeo en base a los metadatos proporcionados.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public final class MappingDescriptorFactory {

    //Se debe esconder el constructor de las clases utilitarias.
    private MappingDescriptorFactory() {
    }
    
    /**
     * Crea el descriptor de mapeo en base a las anotaciones @Field y @Embedded de la clase objetivo.
     * @param targetClass
     * @return 
     */
    public static MappingDescriptor createMappingDescriptor(Class<?> targetClass){
        MappingDescriptor descriptor = new MappingDescriptor(targetClass);
        descriptor.setFieldMappings(getMetaDataFieldMapping(targetClass));
        return descriptor;
    }
    
    /**
     * Crea el descriptor de mapeo en base a los datos proporcionados con las anotaciones @MapDefinition en la declaración
     * del procedimiento.
     * @param mapDefinitions
     * @param mappedType
     * @return 
     */
    public static MappingDescriptor createMappingDescriptor(MapDefinition[] mapDefinitions, Class<?> mappedType){
        MappingDescriptor mappingDescriptor = new MappingDescriptor(mappedType);
        mappingDescriptor.setFieldMappings(getCustomFieldMapping(mapDefinitions, mappedType));
        return mappingDescriptor;
    }
    
    private static List<FieldMappingDescriptor> getCustomFieldMapping(MapDefinition[] mapDefinitions, Class<?> mappedType){
        Object mappedObject = null;
        
        try {
            mappedObject = mappedType.newInstance();
        } catch (InstantiationException ex) {
            throw new InvalidMappingDeclaration(String.format(ErrorMessages.MAPPING_INSTANTIATION_EXCEPTION, mappedType), ex);
        } catch (IllegalAccessException ex) {
            throw new InvalidMappingDeclaration(String.format(ErrorMessages.MAPPING_INSTANTIATION_ILLEGAL_ACCESS, mappedType), ex);
        } 
        
        List<FieldMappingDescriptor> fieldMappings = new ArrayList<FieldMappingDescriptor>();
        for(MapDefinition mapDefinition : mapDefinitions){
            FieldMappingDescriptor fieldMapping = createFieldMapping(mapDefinition, mappedObject);
            fieldMappings.add(fieldMapping);
        }        
        return fieldMappings;        
    }    
    
    private static FieldMappingDescriptor createFieldMapping(MapDefinition mapDefinition, Object mappedObject) {
        FieldMappingDescriptor fieldMapping = new FieldMappingDescriptor();
        fieldMapping.setFieldName(mapDefinition.fieldName());
        fieldMapping.setPropertyName(mapDefinition.propertyName());
        fieldMapping.setMappedClass(mappedObject.getClass());
        fieldMapping.setMappingType(mapDefinition.type());
        
        try {
            fieldMapping.setPropertyClass(PropertyUtils.getPropertyType(mappedObject, mapDefinition.propertyName()));
        } catch (Exception ex) {
            throw new InvalidMappingDeclaration(ErrorMessages.ERROR_GETTING_MAPPED_PROPERTY_TYPE,ex);
        } 

        return fieldMapping;
    }    
    
    private static List<FieldMappingDescriptor> getMetaDataFieldMapping(Class<?> mappedClass){       
        List<FieldMappingDescriptor> mappingDescriptors = new LinkedList<FieldMappingDescriptor>();
        while(!mappedClass.equals(Object.class)){
            mappingDescriptors.addAll(getMetaDataFieldMappingInternal(mappedClass));
            mappedClass = mappedClass.getSuperclass();
        }
        return mappingDescriptors;
    }
    
    private static List<FieldMappingDescriptor> getMetaDataFieldMappingInternal(Class<?> mappedClass){       
        List<FieldMappingDescriptor> fieldMappings = new ArrayList<FieldMappingDescriptor>();
        Field[] fields = mappedClass.getDeclaredFields();
        Class<com.techandsolve.easymapper4j.model.annotations.Field> fieldATClass = com.techandsolve.easymapper4j.model.annotations.Field.class;
        for(Field field : fields){
            if(field.isAnnotationPresent(fieldATClass)){
                com.techandsolve.easymapper4j.model.annotations.Field fieldAT = field.getAnnotation(fieldATClass);
                FieldMappingDescriptor fieldMapping = new FieldMappingDescriptor();
                fieldMapping.setFieldName(fieldAT.name());
                fieldMapping.setPropertyName(field.getName());
                fieldMapping.setMappingType(fieldAT.type());
                fieldMapping.setPropertyClass(field.getType());
                fieldMapping.setMappedClass(mappedClass);
                fieldMappings.add(fieldMapping);
            }else if(field.isAnnotationPresent(Embedded.class)){
                fieldMappings.addAll(getFielMappingEmbedded(field, mappedClass, null));
            }
        }
        
        return fieldMappings;
    }    
    
    private static List<FieldMappingDescriptor> getFielMappingEmbedded(Field field, Class<?> mappedClass, String prefijo){
        Class<?> fieldClass = field.getType();
        List<FieldMappingDescriptor> fieldMappings = new ArrayList<FieldMappingDescriptor>();
        prefijo = generatePrefix(prefijo, field.getName());
        Class<com.techandsolve.easymapper4j.model.annotations.Field> fieldATClass = com.techandsolve.easymapper4j.model.annotations.Field.class;
        for(Field internalField : fieldClass.getDeclaredFields()){
            if(internalField.isAnnotationPresent(fieldATClass)){
                com.techandsolve.easymapper4j.model.annotations.Field fieldAT = internalField.getAnnotation(fieldATClass);
                FieldMappingDescriptor fieldMapping = new FieldMappingDescriptor();
                fieldMapping.setFieldName(fieldAT.name());
                fieldMapping.setPropertyName(prefijo+"."+internalField.getName());
                fieldMapping.setMappingType(fieldAT.type());
                fieldMapping.setPropertyClass(internalField.getType());
                fieldMapping.setMappedClass(mappedClass);
                fieldMappings.add(fieldMapping);
            }else if(internalField.isAnnotationPresent(Embedded.class)){
                fieldMappings.addAll(getFielMappingEmbedded(internalField, mappedClass, prefijo));
            }            
        }
        return fieldMappings;
    }  
    
    private static String generatePrefix(String prefijo, String nombreProp) {
        if(prefijo == null || prefijo.isEmpty()){
            return nombreProp;
        }
        return prefijo+"."+nombreProp;
    }    
}
