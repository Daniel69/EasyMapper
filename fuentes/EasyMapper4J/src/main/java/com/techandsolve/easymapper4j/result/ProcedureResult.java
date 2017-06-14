package com.techandsolve.easymapper4j.result;

import com.techandsolve.easymapper4j.exceptions.ErrorMessages;
import com.techandsolve.easymapper4j.descriptors.StoredProcedureDescriptor;
import com.techandsolve.easymapper4j.exceptions.InvalidAPIUsage;
import com.techandsolve.easymapper4j.exceptions.MappingException;
import com.techandsolve.easymapper4j.exceptions.OutputParameterNotFound;
import com.techandsolve.easymapper4j.exceptions.UndeclaredLobObject;
import com.techandsolve.easymapper4j.exceptions.UndeclaredResultsList;
import com.techandsolve.easymapper4j.descriptors.FieldMappingDescriptor;
import com.techandsolve.easymapper4j.descriptors.MappingDescriptor;
import com.techandsolve.easymapper4j.mapping.Mapper;
import com.techandsolve.easymapper4j.mapping.MappingUtils;
import com.techandsolve.easymapper4j.descriptors.OutputResultSetDescriptor;
import com.techandsolve.easymapper4j.mapping.MappingDescriptorFactory;
import com.techandsolve.easymapper4j.procedures.annotations.OutputLobParameter;
import com.techandsolve.easymapper4j.procedures.annotations.OutputResultSets;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/**
 * Las instancias de esta clase proporcionan acceso a los resultados de la ejecucion de los procedimientos almacenados.
 * Mediante los diferentes metodos proporcionados se obtiene la información de salida del procedimiento despues de
 * haber sido convenientemente procesada y mapeada a los tipos adecuados.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class ProcedureResult {
    
    protected StoredProcedureDescriptor descriptor;
    protected Map<String, Object> resultMap;

    //No debe ser instanciado fuera de SPMapper
    public ProcedureResult(StoredProcedureDescriptor descriptor, Map<String, Object> resultMap) {
        this.descriptor = descriptor;
        this.resultMap = resultMap;
    }

    /**
     * Obtiene el parametro de salida tal y como se devolvio en el conjunto de resultados, es decir sin realizar
     * ninguna conversión de tipos adicional.
     * @param name
     * @return 
     */
    public Object getOutputParameter(String name){
        if(!resultMap.containsKey(name)){
            throw new OutputParameterNotFound(name);
        }
        return resultMap.get(name);
    }
    
    /**
     * Obtiene el parametro de salida de tipo CLOB en forma de un String.
     * <p>Para utilizar este metodo debe haberse declarado ({@link OutputLobParameter}) el parametro de tipo Clob en 
     * los metadatos de la  definición del procedimiento.</p>
     * @param parameterName
     * @return 
     */
    public String getClobAsString(String parameterName){
        if(!descriptor.hasOutputLobParameter(parameterName)){
            throw new UndeclaredLobObject(parameterName);
        }
        
        Clob clob = getOutputParameter(parameterName, Clob.class);
        
        try {
            return IOUtils.toString(clob.getCharacterStream());
        } catch (SQLException ex) {
            throw new MappingException(ex);
        }catch (IOException ex) {
            throw new MappingException(ex);
        }
    }
    
    public byte[] getBlobAsByteArray(String parameterName){
       if(!descriptor.hasOutputLobParameter(parameterName)){
            throw new UndeclaredLobObject(parameterName);
       }
       
        Blob blob = getOutputParameter(parameterName, Blob.class);
        
        try {
            return IOUtils.toByteArray(blob.getBinaryStream());
        } catch (SQLException ex) {
            throw new MappingException(ex);
        } catch (IOException ex) {
            throw new MappingException(ex);
        }
    }
    
    /**
     * Obtiene el parametro de salida realizando la conversión al tipo indicado.
     * @param <E>
     * @param name
     * @param clazz
     * @return 
     */
    public <E> E getOutputParameter(String name, Class<E> clazz){
        return MappingUtils.convert(getOutputParameter(name), clazz);
    }
    
    /**
     * Obtiene el parametro de salida de tipo cursor en forma de una lista de resultados.
     * Para utilizar este metodo se debe haber definido previamente el parametro de salida en los metadatos de la
     * definición del procedimiento ({@link OutputResultSets}).
     * @param <E>
     * @param name
     * @param clazz
     * @return 
     */
    public <E> List<E> getOutputResultList(String name, Class<E> clazz){
        Class<?> resultSetClass = getOutputResultSetDescriptor(name).getTargetClass();
        
        if(!resultSetClass.equals(clazz)){
            throw new InvalidAPIUsage(String.format(ErrorMessages.DIFFERENT_RESULTSLIST_TYPE, clazz.getName(), resultSetClass.getName()));
        }
        
        return (List<E>)getOutputResultList(name);
    }
    
    /**
     * Obtiene el parametro de salida de tipo cursor en forma de una lista de resultados.
     * Para utilizar este metodo se debe haber definido previamente el parametro de salida en los metadatos de la
     * definición del procedimiento ({@link OutputResultSets}).
     * @param name
     * @return 
     */
    public List<Object> getOutputResultList(String name){
        getOutputResultSetDescriptor(name);        
        return getOutputParameter(name, List.class);
    }    
    
    /**
     * Caracteristica en fase de pruebas.
     * @param <E>
     * @param object
     * @return 
     */
    public <E> E mapOutputParameterTo(E object){
        MappingDescriptor mappingDescriptor = MappingDescriptorFactory.createMappingDescriptor(object.getClass());
        for(FieldMappingDescriptor fieldMapping : mappingDescriptor.getFieldMappings()){
            if(resultMap.containsKey(fieldMapping.getFieldName())){
                Mapper mapper = Mapper.getMapper(fieldMapping.getMappingType());
                mapper.mapToObject(object, fieldMapping, resultMap);
            }
        }
        return object;
    }    
    
    private OutputResultSetDescriptor getOutputResultSetDescriptor(String name){
        if(!descriptor.getOutputResultSets().containsKey(name)){
            throw new UndeclaredResultsList(name);      
        }
        return descriptor.getOutputResultSets().get(name);
    }    
    
}
