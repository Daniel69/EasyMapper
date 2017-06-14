package com.techandsolve.easymapper4j.exceptions;

/**
 * Excepcion lanzada cuando el cliente del API requiere un objeto Lob (Ej: getClobAsString) que no ha sido declarado
 * en los metadatos del procedimiento.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class UndeclaredLobObject extends InvalidAPIUsage{

    public UndeclaredLobObject(String parameterName) {
        super(String.format(ErrorMessages.UNDECLARED_LOB_OBJECT, parameterName));
    }
}
