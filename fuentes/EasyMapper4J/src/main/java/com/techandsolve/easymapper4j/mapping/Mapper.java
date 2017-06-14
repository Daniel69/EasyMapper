package com.techandsolve.easymapper4j.mapping;

import com.techandsolve.easymapper4j.jdbc.ResultSetSupport;
import com.techandsolve.easymapper4j.types.MappingType;
import com.techandsolve.easymapper4j.descriptors.FieldMappingDescriptor;
import java.util.EnumMap;
import java.util.Map;

/**
 * Clase base para la implementacion del patron strategy para la realización de los distintos tipos de mapeo.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public abstract class Mapper {
    
    private static final Map<MappingType, Mapper> mappers;
   
    static {
        mappers = new EnumMap<MappingType, Mapper>(MappingType.class);
        mappers.put(MappingType.DEFAULT, new DefaultMapper());
        mappers.put(MappingType.CLOB, new ClobMapper());
        mappers.put(MappingType.BLOB, new BlobMapper());
    }
    
    public static Mapper getMapper(MappingType type){
        return mappers.get(type);
    }
    
    public abstract void mapToObject(Object target, FieldMappingDescriptor fieldMapping, ResultSetSupport resultSetSupport);
    public abstract void mapToObject(Object target, FieldMappingDescriptor fieldMapping, Map<String, Object> resultMap);
}
