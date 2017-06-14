package com.techandsolve.easymapper4j.jdbc;

import com.techandsolve.easymapper4j.descriptors.InputParameterDescriptor;
import com.techandsolve.easymapper4j.descriptors.OutputLobParameterDescriptor;
import com.techandsolve.easymapper4j.descriptors.OutputParameterDescriptor;
import com.techandsolve.easymapper4j.descriptors.StoredProcedureDescriptor;
import com.techandsolve.easymapper4j.types.ReturnType;
import com.techandsolve.easymapper4j.mapping.DynamicRowMapper;
import com.techandsolve.easymapper4j.descriptors.OutputResultSetDescriptor;
import com.techandsolve.easymapper4j.types.MappingType;
import java.sql.Types;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
/**
 *
 * @author Daniel Bustamante Ospina <daniel.bustamante>
 */
public class NoMetaDataProcedureCallFactory {

    protected JdbcTemplateSupport jdbcTemplateSupport;
    
    public NoMetaDataProcedureCallFactory(JdbcTemplateSupport jdbcTemplateSupport) {
        this.jdbcTemplateSupport = jdbcTemplateSupport;
    }

    
    public ProcedureCall createCall(StoredProcedureDescriptor descriptor) {
        if(descriptor.isFunction())
            return createFunction(descriptor);
        return createProcedure(descriptor);
    }
    
    
    private ProcedureCall createFunction(StoredProcedureDescriptor descriptor){
        ProcedureCall procedureCall = createProcedureCommon(descriptor);
        procedureCall.withFunctionName(descriptor.getName());
        if(descriptor.getReturnType() == ReturnType.CURSOR_REF){
            SqlOutParameter outParameter = new SqlOutParameter(StoredProcedureDescriptor.RETURN_PARAMETER_NAME, -10, new DynamicRowMapper(descriptor.getReturnDescriptor().getMappingDescriptor()));
            SqlParameterSupport parameter = new SqlParameterSupport(outParameter, 0);
            procedureCall.getParameterSupports().put(parameter.getIndex(), parameter);            
        }else{
            SqlOutParameter outParameter = new SqlOutParameter(StoredProcedureDescriptor.RETURN_PARAMETER_NAME, descriptor.getReturnSqlType());
            SqlParameterSupport parameter = new SqlParameterSupport(outParameter, 0);
            procedureCall.getParameterSupports().put(parameter.getIndex(), parameter);             
        }   
        procedureCall.processDeclaredParameters();
        return procedureCall;
    }
    
    private ProcedureCall createProcedure(StoredProcedureDescriptor descriptor){
        ProcedureCall procedureCall = createProcedureCommon(descriptor);
        procedureCall.withProcedureName(descriptor.getName());
        procedureCall.processDeclaredParameters();
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
        
        procedureCall.withoutProcedureColumnMetaDataAccess();
        
        for(InputParameterDescriptor inputParameterDescriptor : descriptor.getInputParameters()){
            SqlParameter parameter = null;
            if(inputParameterDescriptor.getType() == MappingType.BLOB){
                parameter = new SqlParameter(inputParameterDescriptor.getParameterName(), Types.BLOB);
            } else if(inputParameterDescriptor.getType() == MappingType.CLOB){
                parameter = new SqlParameter(inputParameterDescriptor.getParameterName(), Types.CLOB);
            } else {
                parameter = new SqlParameter(inputParameterDescriptor.getParameterName(), inputParameterDescriptor.getSqlType());
            }
            SqlParameterSupport support = new SqlParameterSupport(parameter, inputParameterDescriptor.getParamIndex());
            procedureCall.getParameterSupports().put(support.getIndex(), support);            
        }        
        
        for(OutputResultSetDescriptor resultSetDescriptor : descriptor.getOutputResultSets().values()){
            SqlOutParameter outParameter = new SqlInOutParameter(resultSetDescriptor.getName(), -10, new DynamicRowMapper(resultSetDescriptor.getMappingDescriptor()));
            SqlParameterSupport parameter = new SqlParameterSupport(outParameter, resultSetDescriptor.getParamIndex());
            procedureCall.getParameterSupports().put(parameter.getIndex(), parameter);
        }        
        
        
        for(OutputLobParameterDescriptor lobParameter : descriptor.getOutputLobParameters()){
            SqlOutParameter outParameter = null;
            if(procedureCall.getParameterSupports().containsKey(lobParameter.getParamIndex())){
                outParameter = new SqlInOutParameter(lobParameter.getName(), lobParameter.getSqlType());
            }else{
                outParameter = new SqlOutParameter(lobParameter.getName(), lobParameter.getSqlType());
            }
            
            SqlParameterSupport support = new SqlParameterSupport(outParameter, lobParameter.getParamIndex());
            procedureCall.getParameterSupports().put(support.getIndex(), support);
        }    
        
        for(OutputParameterDescriptor outputParameterDescriptor : descriptor.getOutputParameters()){
            SqlOutParameter parameter = null;           
            if(procedureCall.getParameterSupports().containsKey(outputParameterDescriptor.getParamIndex())){
                parameter = new SqlInOutParameter(outputParameterDescriptor.getName(), outputParameterDescriptor.getSqlType());
            } else {
                parameter = new SqlOutParameter(outputParameterDescriptor.getName(), outputParameterDescriptor.getSqlType());
            }            
            SqlParameterSupport support = new SqlParameterSupport(parameter, outputParameterDescriptor.getParamIndex());
            procedureCall.getParameterSupports().put(support.getIndex(), support);            
        }

        return procedureCall;
    }
        
    
}

class SqlParameterSupport implements Comparable<SqlParameterSupport> {
    private SqlParameter parameter;
    private Integer index;

    public SqlParameterSupport(SqlParameter parameter, int index) {
        this.parameter = parameter;
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public SqlParameter getParameter() {
        return parameter;
    }

    public void setParameter(SqlParameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public int compareTo(SqlParameterSupport o) {
        return index.compareTo(o.getIndex());
    }
}
