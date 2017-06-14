package com.techandsolve.easymapper4j.parameters;

import com.techandsolve.easymapper4j.exceptions.ExpectedException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.LobHandler;

/**
 *
 * @author user
 */
public abstract class LobParameterExtractor extends ParameterExtractor{
    
    private LobHandler lobHandler;

    public LobParameterExtractor(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }
    
    protected SqlLobValue createSqlLobValue(InputStream inputStream){
        SqlLobValue lobValue = null;
        try {
            if(inputStream != null){
                lobValue = new SqlLobValue(IOUtils.toByteArray(inputStream), getLobHandler());
            }else{
                lobValue = createEmptySqlLobValue();
            }
        } catch (IOException ex) {
            throw new ExpectedException("Ocurrio un error al leer el valor del Lob como InputStream", ex);
        }       
        return lobValue;
    }    
    
    protected SqlLobValue createEmptySqlLobValue(){
        return new SqlLobValue(new byte[0], getLobHandler());
    }   
    
    public LobHandler getLobHandler() {
        return lobHandler;
    }    
}
