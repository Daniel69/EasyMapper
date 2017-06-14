package com.techandsolve.easymapper4j.mapping;

import com.techandsolve.easymapper4j.jdbc.ResultSetSupport;
import com.techandsolve.easymapper4j.descriptors.FieldMappingDescriptor;
import com.techandsolve.easymapper4j.descriptors.MappingDescriptor;
import com.techandsolve.easymapper4j.exceptions.ErrorMessages;
import com.techandsolve.easymapper4j.exceptions.MappingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementación de RowMapper que realiza los mapeos dinamicos en base a la descripción proporcionada
 * por el objeto MappingDescriptor pasado como argumento del constructor.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class DynamicRowMapper implements RowMapper<Object> {

    private MappingDescriptor mappingDescriptor;

    public DynamicRowMapper(MappingDescriptor mappingDescriptor) {
        this.mappingDescriptor = mappingDescriptor;
    }
    
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Object resultObject = createResultObject(mappingDescriptor);
        ResultSetSupport resultSetSupport = new ResultSetSupport(rs);
        
        for(FieldMappingDescriptor fieldMapping : mappingDescriptor.getFieldMappings()){
            Mapper mapper = Mapper.getMapper(fieldMapping.getMappingType());
            mapper.mapToObject(resultObject, fieldMapping, resultSetSupport);            
        }
              
        return resultObject;
    }

    private Object createResultObject(MappingDescriptor mappingDescriptor) {
        try {
            return mappingDescriptor.getTargetClass().newInstance();
        } catch (Exception ex) {
            throw new MappingException(String.format(ErrorMessages.MAPPING_INSTANTIATION_EXCEPTION, mappingDescriptor.getTargetClass()), ex);
        } 
    }  
}
