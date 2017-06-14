package com.techandsolve.easymapper4j.parameters;

import com.techandsolve.easymapper4j.descriptors.InputParameterDescriptor;
import com.techandsolve.easymapper4j.exceptions.ExpectedException;
import com.techandsolve.easymapper4j.exceptions.IllegalProcedureDeclaration;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.LobHandler;

/**
 *
 * @author user
 */
public class ClobParameterExtractor extends LobParameterExtractor{

    public ClobParameterExtractor(LobHandler lobHandler) {
        super(lobHandler);
    }
    
    @Override
    public Object getInputParameterValue(Object procedure, InputParameterDescriptor descriptor) {
        Object value = getSimpleInputParameterValue(procedure, descriptor);
        Class<?> parameterClass = getPropertyType(procedure, descriptor);
        
        Object lobValue = null;
        
        if(parameterClass.equals(String.class)){
            lobValue = createSqlLobValue((String)value);
        }else if(InputStream.class.isAssignableFrom(parameterClass)){
            lobValue = createSqlLobValue((InputStream)value);
        }else if(Reader.class.isAssignableFrom(parameterClass)){
            lobValue = createSqlLobValue((Reader)value);
        }else if(SqlLobValue.class.isAssignableFrom(parameterClass)){
            lobValue = value != null ? (SqlLobValue)value : createEmptySqlLobValue();
        }else{
            throw new IllegalProcedureDeclaration("El tipo del parametro de entrada declarado como CLOB no es valido!");
        }
               
        return lobValue;
    }
    
    private SqlLobValue createSqlLobValue(String string){
        SqlLobValue lobValue = new SqlLobValue(string, getLobHandler());
        return lobValue;
    }     
    
    private SqlLobValue createSqlLobValue(Reader reader){
        SqlLobValue lobValue = null;
        try {
            if(reader != null){
                lobValue = new SqlLobValue(IOUtils.toString(reader), getLobHandler());
            }else{
                lobValue = createEmptySqlLobValue();
            }
        } catch (IOException ex) {
            throw new ExpectedException("Ocurrio un error al leer el valor del Lob como Reader", ex);
        }       
        return lobValue;
    }     
    
}
