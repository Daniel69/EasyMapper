package com.techandsolve.easymapper4j.types;

/**
 * Representa los tipos de mapeo, los cuales difieren en cuando al mapper utilizado para cada uno.
 * El tipo DEFAULT define el mapeo entre tipos de datos 'normales', es decir, Dates, Numbers, Varchars...
 * El tipo CLOB define mapeos desde o hacia tipos de dato CLOB.
 * El tipo BLOB define mapeos desde o hacia tipos de dato BLOB.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public enum MappingType {
    /**
     * El tipo DEFAULT define el mapeo entre tipos de datos 'normales', es decir, Dates, Numbers, Varchars...
     */
    DEFAULT,
    
    /**
     * El tipo CLOB define mapeos desde o hacia tipos de dato CLOB.
     */
    CLOB,
    
    /**
     * El tipo BLOB define mapeos desde o hacia tipos de dato BLOB.
     */
    BLOB
}
