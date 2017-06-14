package com.techandsolve.easymapper4j.types;

import java.sql.Types;

/**
 * Representa uno de los tipo de LOB, ya sea CLOB o BLOB.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public enum LobType {
    CLOB(Types.CLOB),
    BLOB(Types.BLOB);
    
    private int sqlType;

    private LobType(int sqlType) {
       this.sqlType = sqlType;
    }
    
    /**
     * 
     * @return El tipo JDBC asociado al tipo.
     */
    public int getSqlType(){
        return sqlType;
    }
}
