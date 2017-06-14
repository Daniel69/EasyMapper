package com.techandsolve.easymapper4j.jdbc;

import com.techandsolve.easymapper4j.descriptors.InputParameterDescriptor;
import com.techandsolve.easymapper4j.descriptors.OutputLobParameterDescriptor;
import com.techandsolve.easymapper4j.descriptors.StoredProcedureDescriptor;
import com.techandsolve.easymapper4j.types.ReturnType;
import com.techandsolve.easymapper4j.mapping.DynamicRowMapper;
import com.techandsolve.easymapper4j.descriptors.OutputResultSetDescriptor;
import com.techandsolve.easymapper4j.types.MappingType;
import java.sql.Types;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;

/**
 * Clase encargada de crear los objetos ProcedureCall que encapsulan la llamada JDBC al procedimiento en 
 * base de datos. Estos objetos se crean en base a los datos contenidos en el 
 * descriptor StoredProcedureDescriptor.
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class ProcedureCallFactory {  
    protected JdbcTemplateSupport jdbcTemplateSupport;
    private NoMetaDataProcedureCallFactory noMetaDataFactory;

    public ProcedureCallFactory(JdbcTemplateSupport jdbcTemplateSupport) {
        this.jdbcTemplateSupport = jdbcTemplateSupport;
        noMetaDataFactory = new NoMetaDataProcedureCallFactory(jdbcTemplateSupport);
    }
    
    /**
     * Crea la llamada JDBC en base al descriptor StoredProcedureDescriptor.
     * @param descriptor
     * @return 
     */
    public ProcedureCall createCall(StoredProcedureDescriptor descriptor){    
        if(descriptor.isWithoutMetaDataAccess())
            return noMetaDataFactory.createCall(descriptor);
        return createCallInternal(descriptor);
    }
    
    private ProcedureCall createCallInternal(StoredProcedureDescriptor descriptor){
        if(descriptor.isFunction())
            return createFunction(descriptor);
        return createProcedure(descriptor);
    }
    
    private ProcedureCall createFunction(StoredProcedureDescriptor descriptor){
        ProcedureCall procedureCall = createProcedureCommon(descriptor);
        procedureCall.withFunctionName(descriptor.getName());
        if(descriptor.getReturnType() == ReturnType.CURSOR_REF){
            procedureCall.returningResultSet(descriptor.getReturnDescriptor().getName(), new DynamicRowMapper(descriptor.getReturnDescriptor().getMappingDescriptor()));
        }         
        return procedureCall;
    }
    
    private ProcedureCall createProcedure(StoredProcedureDescriptor descriptor){
        ProcedureCall procedureCall = createProcedureCommon(descriptor);
        procedureCall.withProcedureName(descriptor.getName());
        return procedureCall;
    }
    
    private ProcedureCall createProcedureCommon(StoredProcedureDescriptor descriptor){
        ProcedureCall procedureCall = new ProcedureCall(jdbcTemplateSupport);
        
        if(!descriptor.getPackageName().isEmpty()){
            procedureCall.withCatalogName(descriptor.getPackageName());
        }
        
        if(!descriptor.getSchemaName().isEmpty()){
            procedureCall.withSchemaName(descriptor.getSchemaName());
        }
        
        for(OutputResultSetDescriptor resultSetDescriptor : descriptor.getOutputResultSets().values()){
            procedureCall.addDeclaredRowMapper(resultSetDescriptor.getName(), 
                    new DynamicRowMapper(resultSetDescriptor.getMappingDescriptor()));
        }        
        
        for(OutputLobParameterDescriptor lobParameter : descriptor.getOutputLobParameters()){
            procedureCall.declareParameters(new SqlInOutParameter(lobParameter.getName(), lobParameter.getSqlType()));
        }    
        
        for(InputParameterDescriptor inputParameterDescriptor : descriptor.getInputParameters()){
            if(inputParameterDescriptor.getType() == MappingType.BLOB){
                procedureCall.declareParameters(new SqlParameter(inputParameterDescriptor.getParameterName(), Types.BLOB));
            } else if(inputParameterDescriptor.getType() == MappingType.CLOB){
                procedureCall.declareParameters(new SqlParameter(inputParameterDescriptor.getParameterName(), Types.CLOB));
            }
        }
        
        return procedureCall;
    }
    
}
