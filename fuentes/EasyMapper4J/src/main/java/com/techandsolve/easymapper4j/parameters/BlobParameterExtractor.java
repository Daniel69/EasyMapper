package com.techandsolve.easymapper4j.parameters;

import com.techandsolve.easymapper4j.descriptors.InputParameterDescriptor;
import com.techandsolve.easymapper4j.exceptions.IllegalProcedureDeclaration;
import java.io.InputStream;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.LobHandler;

/**
 *
 * @author user
 */
public class BlobParameterExtractor extends LobParameterExtractor{

    public BlobParameterExtractor(LobHandler lobHandler) {
        super(lobHandler);
    }
    
    @Override
    public Object getInputParameterValue(Object procedure, InputParameterDescriptor descriptor) {
        Object value = getSimpleInputParameterValue(procedure, descriptor);
        Class<?> parameterClass = getPropertyType(procedure, descriptor);
        
        SqlLobValue lobValue = null;
        
        if(isByteArray(parameterClass)){
            lobValue = createSqlLobValue((byte[])value); 
        } else if(InputStream.class.isAssignableFrom(parameterClass)){
            lobValue = createSqlLobValue((InputStream)value);
        }else if(SqlLobValue.class.isAssignableFrom(parameterClass)){
            lobValue = value != null ? (SqlLobValue)value : createEmptySqlLobValue();
        }else{
            throw new IllegalProcedureDeclaration("El tipo del parametro de entrada declarado como BLOB no es valido!");
        }
                   
        return lobValue;
    }
    
    private SqlLobValue createSqlLobValue(byte [] bytes){
        SqlLobValue lobValue = null;
        if(bytes != null){
            lobValue = new SqlLobValue((byte[])bytes, getLobHandler());
        }else{
            lobValue = createEmptySqlLobValue();
        }        
        return lobValue;
    }

}
