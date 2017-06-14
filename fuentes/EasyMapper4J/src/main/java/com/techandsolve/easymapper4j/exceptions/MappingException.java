package com.techandsolve.easymapper4j.exceptions;

/**
 * Indica una excepcion ocurrida al momento de realizar el mapeo de los resultados de la 
 * ejecucion de la funcion o procedimiento a los tipos de dato objetivo.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class MappingException extends ExpectedException{

    public MappingException(Throwable cause) {
        super(ErrorMessages.GENERIC_MAPPING_EXCEPTION, cause);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }
 
}
