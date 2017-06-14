package com.techandsolve.easymapper4j.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de brindar soporte para las operaciones adicionales relacionadas con el ResultSet.
 * Como por ejemplo proveer informacion adicional sobre las columnas retornadas y demas meta información.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class ResultSetSupport {
    private ResultSet resultSet;
    private Map<String, String> returnedColumns = new HashMap<String, String>();

    public ResultSetSupport(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet; 
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for(int i = 1; i <= resultSetMetaData.getColumnCount();i++){
            returnedColumns.put(resultSetMetaData.getColumnName(i), resultSetMetaData.getColumnClassName(i));  
        }

        resultSetMetaData.getColumnCount();
    }
    
    /**
     * Indica si la columna indentificada con el nombre columnName esta presente en el ResultSet
     * @param columnName
     * @return 
     */
    public boolean isColumnPresent(String columnName){
        return returnedColumns.containsKey(columnName);
    }

    /**
     * El result set nativo.
     * @return 
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * El result set nativo.
     * @param resultSet 
     */
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }    
}
