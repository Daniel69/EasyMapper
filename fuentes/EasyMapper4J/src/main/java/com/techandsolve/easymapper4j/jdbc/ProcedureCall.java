package com.techandsolve.easymapper4j.jdbc;

import com.techandsolve.easymapper4j.exceptions.ProcedureExecutionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 * Clase que encapsula la llamada jdbc al procedimiento en base de datos.
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class ProcedureCall extends SimpleJdbcCall{
    
    private Map<Integer, SqlParameterSupport> parameterSupports = new HashMap<Integer, SqlParameterSupport>();
    
    ProcedureCall(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Map<String, Object> execute(Map<String, ?> args) {
        try{
            return super.execute(args);
        }catch(DataAccessException ex){        
           throw new ProcedureExecutionException(getProcedureName(), ex); 
        }   
    }

    Map<Integer, SqlParameterSupport> getParameterSupports() {
        return parameterSupports;
    }

    void setParameterSupports(Map<Integer, SqlParameterSupport> parameterSupports) {
        this.parameterSupports = parameterSupports;
    }

    void processDeclaredParameters() {
        List<SqlParameterSupport> parameters = new ArrayList<SqlParameterSupport>(parameterSupports.values());
        Collections.sort(parameters);
        for(SqlParameterSupport parameter : parameters){
            declareParameters(parameter.getParameter());
        }
    }
     
}
