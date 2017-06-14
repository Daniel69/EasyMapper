/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techandsolve.easymapper4j.mapping;

import com.techandsolve.easymapper4j.parameters.ParameterExtractor;
import com.techandsolve.easymapper4j.procedures.model.Institucion;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanWrapperImpl;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class AutoConversionTest  {
    
    
    @Test
    public void testAutoConversion() throws Exception{
       ClaseMapear claseMapear = new ClaseMapear();
       BeanUtils.setProperty(claseMapear, "nombre", "Un nombre Str");
       BeanUtils.setProperty(claseMapear, "fechaString", new java.sql.Date(2011, 7, 31));
       BeanUtils.setProperty(claseMapear, "numeroString", new BigDecimal(324234));
       BeanUtils.setProperty(claseMapear, "numero", new BigDecimal(342));
       BeanUtils.setProperty(claseMapear, "numeroGrande", 324242);
       BeanUtils.setProperty(claseMapear, "fecha", new Date());
       BeanUtils.setProperty(claseMapear, "numeroFlotante", new BigDecimal(342332)); 
    }
    
    @Test
    public void testPropAnidadas() {
        Institucion institucion = new Institucion();
        try {
            BeanWrapperImpl beanWrapperImpl = new BeanWrapperImpl(institucion);
            beanWrapperImpl.setPropertyValue("director.nombre", "Nombre Director");
            BeanUtils.setProperty(institucion, "director.nombre", "Nombre Director");
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AutoConversionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AutoConversionTest.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void isByteArrayTest(){
        Byte[] bytes = {1,2,3,4,5,6,87};
        byte[] bs = {2,98,7,56,4,4,3,2};
        char[] bCs = {21,21,12,2};
        Assert.assertTrue(ParameterExtractor.isByteArray(bytes.getClass()));
        Assert.assertTrue(ParameterExtractor.isByteArray(bs.getClass()));
        Assert.assertFalse(ParameterExtractor.isByteArray(bCs.getClass()));
    }
}

