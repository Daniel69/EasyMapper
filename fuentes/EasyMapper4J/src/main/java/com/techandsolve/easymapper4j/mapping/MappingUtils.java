package com.techandsolve.easymapper4j.mapping;

import com.techandsolve.easymapper4j.exceptions.TypeConversionError;
import org.apache.commons.beanutils.ConvertUtils;

/**
 * Clase utilitaria para centralizar distintas acciones de mapeo y conversion comunes.
 * @author Daniel Bustamante <daniel.bustamante>
 */
public final class MappingUtils {

    private MappingUtils() {
    }
    
    public static <E> E convert(Object object, Class<E> targetClass){
        try{
            return targetClass.cast(ConvertUtils.convert(object, targetClass));
        }catch(RuntimeException e){
            throw new TypeConversionError(object, targetClass, e);
        }    
    }
    
}
