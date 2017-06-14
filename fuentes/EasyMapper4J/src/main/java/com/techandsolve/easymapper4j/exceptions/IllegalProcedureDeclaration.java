package com.techandsolve.easymapper4j.exceptions;

/**
 * Indica una declaracion invalida o incompleta por parte del usuario de los metadatos que describen 
 * el procedimiento almacenado que se quiere ejecutar.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class IllegalProcedureDeclaration extends ExpectedException{

    public IllegalProcedureDeclaration() {
    }

    public IllegalProcedureDeclaration(String s) {
        super(s);
    }

    public IllegalProcedureDeclaration(Throwable cause) {
        super(cause);
    }

    public IllegalProcedureDeclaration(String message, Throwable cause) {
        super(message, cause);
    }
}   
