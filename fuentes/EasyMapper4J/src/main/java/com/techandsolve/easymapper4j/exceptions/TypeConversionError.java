package com.techandsolve.easymapper4j.exceptions;

/**
 * Indica que ocurrio un error al tratar de realizar una conversion de tipos.
 * Una de las posibles causas de este error es que alla incompatibilidad entre el tipo de dato origen y el
 * tipo de dato objetivo.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class TypeConversionError extends ExpectedException{

    public TypeConversionError(Object object, Class<?> targetClass, Throwable cause) {
        super(String.format(ErrorMessages.TYPE_CONVERSION_ERROR, 
                object.getClass().getName(), targetClass.getName()), cause);
    }
    
    public TypeConversionError(String message) {
        super(message);
    }

    public TypeConversionError(Throwable cause) {
        super(cause);
    }

    public TypeConversionError(String message, Throwable cause) {
        super(message, cause);
    }
    
}
