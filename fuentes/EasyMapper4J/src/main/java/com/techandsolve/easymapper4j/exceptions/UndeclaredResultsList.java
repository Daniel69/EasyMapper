package com.techandsolve.easymapper4j.exceptions;

/**
 * Indica que se esta realizando una peticion a una lista de resultados no 
 * declarada en la definicion del procedimiento.
 * 
 * Esta excepcion indica por lo general un mal uso del API.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class UndeclaredResultsList extends InvalidAPIUsage{

    public UndeclaredResultsList(String parameterName) {
        super(String.format(ErrorMessages.UNDECLARED_RESULTSLIST, parameterName));
    }
    
}
