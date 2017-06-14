package com.techandsolve.easymapper4j.mapping;

import com.techandsolve.easymapper4j.jdbc.ResultSetSupport;
import com.techandsolve.easymapper4j.descriptors.FieldMappingDescriptor;
import com.techandsolve.easymapper4j.exceptions.MappingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

/**
 * Clase delegada para realizar los mapeos de los tipos Clob, el mapeo de los Clob se 
 * realiza a objetos String.
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class ClobMapper extends Mapper{

    @Override
    public void mapToObject(Object target, FieldMappingDescriptor fieldMapping, ResultSetSupport resultSetSupport) {
        try {           
            performMapToObject(target, fieldMapping, resultSetSupport);            
        } catch (IllegalAccessException e){
            throw new MappingException(e);
        } catch (InvocationTargetException ex) {
            throw new MappingException(ex);
        } catch (IOException ex) {
            throw new MappingException(ex);
        } catch (SQLException ex) {
            throw new MappingException(ex);
        } 
    }
    
    protected void performMapToObject(Object target, FieldMappingDescriptor fieldMapping, ResultSetSupport resultSetSupport) throws SQLException, IOException, IllegalAccessException, InvocationTargetException{
        if(resultSetSupport.isColumnPresent(fieldMapping.getFieldName())){
            ResultSet rs = resultSetSupport.getResultSet();
            Clob clob = rs.getClob(fieldMapping.getFieldName());
            String clobValue = IOUtils.toString(clob.getCharacterStream());
            BeanUtils.setProperty(target, fieldMapping.getPropertyName(), clobValue);                
        }        
    }

    @Override
    public void mapToObject(Object target, FieldMappingDescriptor fieldMapping, Map<String, Object> resultMap) {
        throw new UnsupportedOperationException("Operacion no soportada para los tipos Clob.");
    }
}
