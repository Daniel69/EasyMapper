package com.techandsolve.easymapper4j.core;

import com.techandsolve.easymapper4j.exceptions.ErrorMessages;
import com.techandsolve.easymapper4j.jdbc.ProcedureCallFactory;
import com.techandsolve.easymapper4j.descriptors.StoredProcedureDescriptor;
import com.techandsolve.easymapper4j.descriptors.InputParameterDescriptor;
import com.techandsolve.easymapper4j.types.ReturnType;
import com.techandsolve.easymapper4j.exceptions.IllegalProcedureDeclaration;
import com.techandsolve.easymapper4j.descriptors.MappingDescriptor;
import com.techandsolve.easymapper4j.descriptors.OutputLobParameterDescriptor;
import com.techandsolve.easymapper4j.descriptors.OutputParameterDescriptor;
import com.techandsolve.easymapper4j.descriptors.OutputResultSetDescriptor;
import com.techandsolve.easymapper4j.jdbc.JdbcTemplateSupport;
import com.techandsolve.easymapper4j.mapping.MappingDescriptorFactory;
import com.techandsolve.easymapper4j.procedures.annotations.FunctionReturnValue;
import com.techandsolve.easymapper4j.procedures.annotations.InputParameter;
import com.techandsolve.easymapper4j.procedures.annotations.MapDefinition;
import com.techandsolve.easymapper4j.procedures.annotations.OutputLobParameter;
import com.techandsolve.easymapper4j.procedures.annotations.OutputLobParameters;
import com.techandsolve.easymapper4j.procedures.annotations.OutputParameter;
import com.techandsolve.easymapper4j.procedures.annotations.OutputParameters;
import com.techandsolve.easymapper4j.procedures.annotations.OutputResultSet;
import com.techandsolve.easymapper4j.procedures.annotations.OutputResultSets;
import com.techandsolve.easymapper4j.procedures.annotations.StoredProcedure;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase encargada de la creacion de los descriptores de los procedimientos, los cuales son los objetos centrales
 * del procesamiento y ejecucion de los procedimientos almacenados.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
class StoredProcedureFactory {
    
    private ProcedureCallFactory procedureCallFactory;
    private final Map<Class<?>, StoredProcedureDescriptor> compiledProcedures = new HashMap<Class<?>, StoredProcedureDescriptor>();
    private final Lock creationlock = new ReentrantLock();    

    public StoredProcedureFactory(JdbcTemplateSupport jdbcTemplateSupport) {
        procedureCallFactory = new ProcedureCallFactory(jdbcTemplateSupport);
    }
    
    /**
     * Crea u obtiene de cache el descriptor del procedimiento almacenado que la clase spClass representa.
     * @param spClass
     * @return 
     */
    public StoredProcedureDescriptor getProcedureDescriptor(Class<?> spClass){
        if(!compiledProcedures.containsKey(spClass)){
            creationlock.lock();
            try{
                if(!compiledProcedures.containsKey(spClass)){
                    compiledProcedures.put(spClass, createStoredProcedureDescriptor(spClass));
                }                
            }finally{
                creationlock.unlock();
            }
        }
        return compiledProcedures.get(spClass);
    }
    
    /*
     * Realiza el proceso de creacion del descriptor del procedimiento almacenado que la clase 
     * spClass representa.
     */
    private StoredProcedureDescriptor createStoredProcedureDescriptor(Class<?> spClass){
        if(!spClass.isAnnotationPresent(StoredProcedure.class)){
            throw new IllegalProcedureDeclaration(ErrorMessages.NO_STORED_PROCEDURE_AT);
        }
        
        StoredProcedureDescriptor descriptor = null;
        
        if(spClass.getAnnotation(StoredProcedure.class).isFunction()){
            descriptor = createFunctionDescriptor(spClass);
        }else {
            descriptor = createProcedureDescriptor(spClass);
        }
        
        descriptor.setCall(procedureCallFactory.createCall(descriptor));    
        return descriptor;
    }
    
    private StoredProcedureDescriptor createProcedureDescriptor(Class<?> spClass){
        StoredProcedure storedProcedureAT = spClass.getAnnotation(StoredProcedure.class);
        StoredProcedureDescriptor descriptor = new StoredProcedureDescriptor();      
        descriptor.setName(storedProcedureAT.name());
        descriptor.setPackageName(storedProcedureAT.packageName());
        descriptor.setSchemaName(storedProcedureAT.schemaName());
        descriptor.setFunction(storedProcedureAT.isFunction());
        descriptor.setWithoutMetaDataAccess(storedProcedureAT.withoutMetaDataAccess());
        descriptor.setOutputResultSets(getOutputResultSets(spClass));
        descriptor.setOutputLobParameteres(getOutputLobParameteres(spClass));
        descriptor.setOutputParameteres(getOutputParameteres(spClass));
        descriptor.setInputParameters(getDeclaredInputParameters(spClass));        
        return descriptor;
    }
    
    private StoredProcedureDescriptor createFunctionDescriptor(Class<?> spClass){
        if(!spClass.isAnnotationPresent(FunctionReturnValue.class)){
            throw new IllegalProcedureDeclaration(ErrorMessages.NO_FUNCTION_RETURN_DEF);
        }
        
        StoredProcedureDescriptor descriptor = createProcedureDescriptor(spClass);   
        FunctionReturnValue returnValueAT = spClass.getAnnotation(FunctionReturnValue.class);
        descriptor.setReturnType(returnValueAT.returnType());
        descriptor.setReturnSqlType(returnValueAT.sqlType());
        
        if(descriptor.getReturnType() == ReturnType.CURSOR_REF){
            descriptor.setReturnDescriptor(createResultSetDescriptor(returnValueAT));
        }
        
        return descriptor;
    }    
    
    private List<InputParameterDescriptor> getDeclaredInputParameters(Class<?> claseSP){
        List<InputParameterDescriptor> inputParameters = new ArrayList<InputParameterDescriptor>();
        Field[] fields = claseSP.getDeclaredFields();
        for(Field field : fields){
          if(field.isAnnotationPresent(InputParameter.class)){
              InputParameter inputParameter = field.getAnnotation(InputParameter.class);
              InputParameterDescriptor descriptor = new InputParameterDescriptor(field.getName(), inputParameter.name());
              descriptor.setType(inputParameter.type());
              descriptor.setParamIndex(inputParameter.paramIndex());
              descriptor.setSqlType(inputParameter.sqlType());
              inputParameters.add(descriptor);
          }   
        }
        return inputParameters;
    }    
    
    private Map<String, OutputResultSetDescriptor> getOutputResultSets(Class<?> spClass){
        Map<String, OutputResultSetDescriptor> outputResultSets = new HashMap<String, OutputResultSetDescriptor>();
        if(spClass.isAnnotationPresent(OutputResultSets.class)){
            OutputResultSets outputResultSetsAT = spClass.getAnnotation(OutputResultSets.class);
            for(OutputResultSet resultSetAT : outputResultSetsAT.value()){
                OutputResultSetDescriptor resultSetDescriptor = createResultSetDescriptor(resultSetAT);
                outputResultSets.put(resultSetDescriptor.getName(), resultSetDescriptor);
            }
        }else if(spClass.isAnnotationPresent(OutputResultSet.class)){
            OutputResultSet resultSetAT = spClass.getAnnotation(OutputResultSet.class);
            OutputResultSetDescriptor resultSetDescriptor = createResultSetDescriptor(resultSetAT);
            outputResultSets.put(resultSetDescriptor.getName(), resultSetDescriptor);
        }
        return outputResultSets;
    }
    
    private Map<String, OutputLobParameterDescriptor> getOutputLobParameteres(Class<?> spClass){
        Map<String, OutputLobParameterDescriptor> parameters = new HashMap<String, OutputLobParameterDescriptor>();
        if(spClass.isAnnotationPresent(OutputLobParameters.class)){
            for(OutputLobParameter lobParameter : spClass.getAnnotation(OutputLobParameters.class).value()){
                parameters.put(lobParameter.name(), new OutputLobParameterDescriptor(lobParameter));
            }
        }else if(spClass.isAnnotationPresent(OutputLobParameter.class)){
            OutputLobParameter lobParameter = spClass.getAnnotation(OutputLobParameter.class);
            parameters.put(lobParameter.name(), new OutputLobParameterDescriptor(lobParameter));
        }
        return parameters;
    }
    
    private Map<String, OutputParameterDescriptor> getOutputParameteres(Class<?> spClass){
        Map<String, OutputParameterDescriptor> parameters = new HashMap<String, OutputParameterDescriptor>();
        if(spClass.isAnnotationPresent(OutputParameters.class)){
            for(OutputParameter parameter : spClass.getAnnotation(OutputParameters.class).value()){
                parameters.put(parameter.name(), new OutputParameterDescriptor(parameter));
            }
        }else if(spClass.isAnnotationPresent(OutputParameter.class)){
            OutputParameter parameter = spClass.getAnnotation(OutputParameter.class);
            parameters.put(parameter.name(), new OutputParameterDescriptor(parameter));
        }
        return parameters;
    }    

    private OutputResultSetDescriptor createResultSetDescriptor(OutputResultSet resultSetAT) {
        OutputResultSetDescriptor descriptor = new OutputResultSetDescriptor();
        descriptor.setName(resultSetAT.name());
        descriptor.setParamIndex(resultSetAT.paramIndex());
        descriptor.setMappingDescriptor(createMappingDescriptor(resultSetAT));
        return descriptor;
    }
    
    private OutputResultSetDescriptor createResultSetDescriptor(FunctionReturnValue returnValueAT) {
        OutputResultSetDescriptor descriptor = new OutputResultSetDescriptor();
        descriptor.setName(StoredProcedureDescriptor.RETURN_PARAMETER_NAME);
        descriptor.setParamIndex(0);
        descriptor.setMappingDescriptor(createMappingDescriptor(returnValueAT));
        return descriptor;
    }
    
    private MappingDescriptor createMappingDescriptor(FunctionReturnValue returnValueAT){
        return createMappingDescriptor(returnValueAT.customMapDefinitions(), returnValueAT.mappedClass());
    }    
    
    private MappingDescriptor createMappingDescriptor(MapDefinition[] mapDefinitions, Class<?> mappedType){     
        if(mapDefinitions.length > 0) {
            return MappingDescriptorFactory.createMappingDescriptor(mapDefinitions, mappedType);
        }else {
            return MappingDescriptorFactory.createMappingDescriptor(mappedType);
        }
    }    
    
    private MappingDescriptor createMappingDescriptor(OutputResultSet resultSetAT){
        return createMappingDescriptor(resultSetAT.customMapDefinitions(), resultSetAT.mappedClass());
    }
}