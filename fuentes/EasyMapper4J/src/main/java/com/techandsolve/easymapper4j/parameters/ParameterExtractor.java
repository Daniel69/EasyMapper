package com.techandsolve.easymapper4j.parameters;

import com.techandsolve.easymapper4j.descriptors.InputParameterDescriptor;
import com.techandsolve.easymapper4j.exceptions.ErrorMessages;
import com.techandsolve.easymapper4j.exceptions.IllegalProcedureDeclaration;
import com.techandsolve.easymapper4j.types.MappingType;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
public abstract class ParameterExtractor {
    
    public static ParameterExtractor getParameterHandler(MappingType mappingType){
        return null;
    }
    
    public abstract Object getInputParameterValue(Object procedure, InputParameterDescriptor descriptor);
    
    public static boolean isByteArray(Class<?> clazz){
       return (
               clazz.isArray() && (
               clazz.getComponentType().equals(Byte.class) ||
               clazz.getComponentType().equals(byte.class)) 
       );
    }
    
    public static Class<?> getPropertyType(Object object, InputParameterDescriptor descriptor){
        try {
            return PropertyUtils.getPropertyType(object, descriptor.getPropertyName()); 
        } catch (IllegalAccessException ex) {
            String msj = String.format(ErrorMessages.NO_ACCESS_GETTER_INPUT_PARAMETER, descriptor.getParameterName(), descriptor.getPropertyName());
            throw new IllegalProcedureDeclaration(msj, ex);
        } catch (InvocationTargetException ex) {
            String msj = String.format(ErrorMessages.GETTER_INVOCATION_ERROR, descriptor.getPropertyName(), descriptor.getProcedureClass().getName());
            throw new IllegalProcedureDeclaration(msj, ex);
        } catch (NoSuchMethodException ex) {
            String msj = String.format(ErrorMessages.NO_GETTER_INPUT_PARAMETER, descriptor.getParameterName(), descriptor.getPropertyName());
            throw new IllegalProcedureDeclaration(msj, ex);
        }         
        
    }
    
    public static Object getSimpleInputParameterValue(Object procedure, InputParameterDescriptor descriptor){
        try {
            return PropertyUtils.getProperty(procedure, descriptor.getPropertyName());
        } catch (IllegalAccessException ex) {
            String msj = String.format(ErrorMessages.NO_ACCESS_GETTER_INPUT_PARAMETER, descriptor.getParameterName(), descriptor.getPropertyName());
            throw new IllegalProcedureDeclaration(msj, ex);
        } catch (InvocationTargetException ex) {
            String msj = String.format(ErrorMessages.GETTER_INVOCATION_ERROR, descriptor.getPropertyName(), descriptor.getProcedureClass().getName());
            throw new IllegalProcedureDeclaration(msj, ex);
        } catch (NoSuchMethodException ex) {
            String msj = String.format(ErrorMessages.NO_GETTER_INPUT_PARAMETER, descriptor.getParameterName(), descriptor.getPropertyName());
            throw new IllegalProcedureDeclaration(msj, ex);
        }        
    }
}
