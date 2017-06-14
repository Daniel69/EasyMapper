package com.techandsolve.easymapper4j.exceptions;

/**
 * Indica que ocurrio un error lanzado desde la ejecucion del procedimiento en base de datos.
 * 
 * Tambien es una posible causa de este tipo de errores es que la definicion del procedimiento en los metadatos 
 * no concuerde con la definición en base de datos.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class ProcedureExecutionException extends ExpectedException{

    public ProcedureExecutionException(String procedureName, Throwable cause) {
        super(String.format(ErrorMessages.PROCEDURE_EXECUTION_EXCEPTION, procedureName) ,cause);
    }
    
}
