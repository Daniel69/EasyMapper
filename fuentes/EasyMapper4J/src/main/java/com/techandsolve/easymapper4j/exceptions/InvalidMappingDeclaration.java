package com.techandsolve.easymapper4j.exceptions;

/**
 * Esta Excepcion indica una declaracion de mapeo invalida o erronea por parte del usuario.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class InvalidMappingDeclaration extends IllegalProcedureDeclaration{

    public InvalidMappingDeclaration() {
    }

    public InvalidMappingDeclaration(String s) {
        super(s);
    }

    public InvalidMappingDeclaration(Throwable cause) {
        super(cause);
    }

    public InvalidMappingDeclaration(String message, Throwable cause) {
        super(message, cause);
    }
    
}
