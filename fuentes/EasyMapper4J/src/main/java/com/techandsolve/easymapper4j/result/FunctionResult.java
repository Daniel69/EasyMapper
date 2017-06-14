package com.techandsolve.easymapper4j.result;

import com.techandsolve.easymapper4j.exceptions.ErrorMessages;
import com.techandsolve.easymapper4j.descriptors.StoredProcedureDescriptor;
import com.techandsolve.easymapper4j.types.ReturnType;
import com.techandsolve.easymapper4j.exceptions.InvalidAPIUsage;
import java.util.List;
import java.util.Map;

/**
 * <p>Clase que representa los resultados devueltos por la ejecucion de una funcion almacenada.</p>
 * Esta clase tiene todos los metodos de ProcedureResult más los metodos propios: getReturnedResultList, getReturnedValue.
 * Para acceder al valor de retorno de la función.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class FunctionResult extends ProcedureResult{

    public FunctionResult(StoredProcedureDescriptor descriptor, Map<String, Object> resultMap) {
        super(descriptor, resultMap);
    }
    
    /**
     * Devuelve la lista de resultados que devolvio la ejecución de la función en base de datos.
     * Debe usuarse cuando la función retorne una lista de resultados (ReturnType.CURSOR_REF).
     * @param <E>
     * @param clazz
     * @return 
     */
    public <E> List<E> getReturnedResultList(Class<E> clazz){
        Class<?> resultSetClass = descriptor.getReturnDescriptor().getTargetClass();
        
        if(!resultSetClass.equals(clazz)){
            throw new InvalidAPIUsage(String.format(ErrorMessages.DIFFERENT_RESULTSLIST_TYPE, clazz.getName(), resultSetClass.getName()));        
        }   
        return (List<E>) getReturnedResultList();
    }
    
    /**
     * Devuelve la lista de resultados que devolvio la ejecución de la función en base de datos.
     * Debe usuarse cuando la función retorne una lista de resultados (ReturnType.CURSOR_REF).
     * @return 
     */
    public List<Object> getReturnedResultList(){
        if(descriptor.getReturnType() != ReturnType.CURSOR_REF){
            throw new InvalidAPIUsage(ErrorMessages.NO_CURSOR_RETURN_TYPE);
        }   
        return getOutputParameter(descriptor.getReturnDescriptor().getName(), List.class);
    }    
    
    /**
     * Devuelve el resultado de la ejecución de la función en base de datos, debe usuarse cuando la función
     * retorna un tipo ReturnType.SCALAR.
     * @param <E>
     * @param clazz
     * @return 
     */
    public <E> E getReturnedValue(Class<E> clazz){
        if(descriptor.getReturnType() != ReturnType.SCALAR){
            throw new InvalidAPIUsage(ErrorMessages.NO_SCALAR_RETURN_TYPE);
        }
        return getOutputParameter("return", clazz);        
    }
    
    /**
     * Devuelve el resultado de la ejecución de la función en base de datos, debe usuarse cuando la función
     * retorna un tipo ReturnType.SCALAR.    
     * @return 
     */
    public Object getReturnedValue(){
        if(descriptor.getReturnType() != ReturnType.SCALAR){
            throw new InvalidAPIUsage(ErrorMessages.NO_SCALAR_RETURN_TYPE);
        }
        return getOutputParameter("return");
    }    
}
