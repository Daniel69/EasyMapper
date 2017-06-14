package com.techandsolve.easymapper4j.mapping;

import com.techandsolve.easymapper4j.jdbc.ResultSetSupport;
import com.techandsolve.easymapper4j.descriptors.FieldMappingDescriptor;
import com.techandsolve.easymapper4j.exceptions.MappingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.OracleLobHandler;

/**
 * Mapper encargado de los mapeos y las conversiones del tipo de dato Blob.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class BlobMapper extends Mapper{

    @Override
    public void mapToObject(Object target, FieldMappingDescriptor fieldMapping, ResultSetSupport resultSet) {
        try {
            performMapToObject(target, fieldMapping, resultSet);
        } catch (Exception ex) {
            throw new MappingException(ex);
        } 
    }
    
    private void performMapToObject(Object target, FieldMappingDescriptor fieldMapping, ResultSetSupport resultSetSupport) throws SQLException, IOException, IllegalAccessException, InvocationTargetException {
        if(resultSetSupport.isColumnPresent(fieldMapping.getFieldName())){
            byte[] bytes = null;
            ResultSet rs = resultSetSupport.getResultSet();
            Blob blob = rs.getBlob(fieldMapping.getFieldName());
            if(blob != null){
                bytes = IOUtils.toByteArray(blob.getBinaryStream());
            }
            BeanUtils.setProperty(target, fieldMapping.getPropertyName(), bytes);                
        }        
    }    

    @Override
    public void mapToObject(Object target, FieldMappingDescriptor fieldMapping, Map<String, Object> resultMap) {
        throw new UnsupportedOperationException("El mapeo automatico de parametros de salida no esta soportado para tipos Blob");
    }
    
}
