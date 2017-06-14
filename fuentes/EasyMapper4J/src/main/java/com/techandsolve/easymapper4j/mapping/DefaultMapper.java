package com.techandsolve.easymapper4j.mapping;

import com.techandsolve.easymapper4j.jdbc.ResultSetSupport;
import com.techandsolve.easymapper4j.descriptors.FieldMappingDescriptor;
import com.techandsolve.easymapper4j.exceptions.MappingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Mapper encargado de los mapeos y las conversiones entre tipos de dato 'normales'.
 * Entiendase por 'normales', los tipos de dato JDBC numericos, de caracteres y fechas.
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class DefaultMapper extends Mapper{

    @Override
    public void mapToObject(Object target, FieldMappingDescriptor fieldMapping, ResultSetSupport resultSetSupport) {
        try {
            performMapToObject(target, fieldMapping, resultSetSupport);
        } catch (Exception ex) {
            throw new MappingException(ex);
        } 
    }
    
    protected void performMapToObject(Object target, FieldMappingDescriptor fieldMapping, ResultSetSupport resultSetSupport) throws SQLException, IllegalAccessException, InvocationTargetException {
        if(resultSetSupport.isColumnPresent(fieldMapping.getFieldName())){
            ResultSet resultSet = resultSetSupport.getResultSet();
            Object valorObtenido = resultSet.getObject(fieldMapping.getFieldName());
            BeanUtils.setProperty(target, fieldMapping.getPropertyName(), valorObtenido);    
        }        
    }    

    @Override
    public void mapToObject(Object target, FieldMappingDescriptor fieldMapping, Map<String, Object> resultMap) {
        try {
            performMapToObject(target, fieldMapping, resultMap);
        } catch (Exception ex) {
            throw new MappingException(ex);
        }
    }
    
    protected void performMapToObject(Object target, FieldMappingDescriptor fieldMapping,  Map<String, Object> resultMap) throws IllegalAccessException, InvocationTargetException  {
        if(resultMap.containsKey(fieldMapping.getFieldName())){
            Object valorObtenido = resultMap.get(fieldMapping.getFieldName());
            BeanUtils.setProperty(target, fieldMapping.getPropertyName(), valorObtenido);
        }      
    }
}
