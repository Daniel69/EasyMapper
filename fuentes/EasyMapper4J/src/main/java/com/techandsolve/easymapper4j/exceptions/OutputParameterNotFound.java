package com.techandsolve.easymapper4j.exceptions;

/**
 * Indica que el parametro de salida requerido no fue devuelto por el procedimiento en base de datos.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class OutputParameterNotFound extends InvalidAPIUsage{

    public OutputParameterNotFound(String param) {
        super(String.format(ErrorMessages.OUTPUT_PARAMETER_NOT_FOUND, param));
    }
}
