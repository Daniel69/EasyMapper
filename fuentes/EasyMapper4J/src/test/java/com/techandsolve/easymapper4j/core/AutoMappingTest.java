package com.techandsolve.easymapper4j.core;

import com.techandsolve.easymapper4j.core.StoredProcedureExecutor;
import com.techandsolve.easymapper4j.result.FunctionResult;
import com.techandsolve.easymapper4j.testSP.ListaPersonasRegionesClob;
import com.techandsolve.easymapper4j.testSP.Persona;
import com.techandsolve.easymapper4j.testSP.Regions;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class AutoMappingTest {
    
    private static StoredProcedureExecutor executor;
    private FunctionResult result;


    @BeforeClass
    public static void setUpClass() throws Exception {       
        executor = TestUtils.createTestExecutor();
    }
    
    @Before
    public void setUp(){
        ListaPersonasRegionesClob function = new ListaPersonasRegionesClob();
        function.setParametro1("Un valor cualquiera");        
        result = executor.executeFunction(function);
    }

    @Test
    public void functionResultListTest(){
        List<Persona> personas = result.getReturnedResultList(Persona.class);
        List<Object> personasNoTipada = result.getReturnedResultList();        
        Assert.assertTrue(personas.size() > 0 && personasNoTipada.size() == personas.size());
    }
    
    @Test
    public void outputResultSetTest(){
        List<Object> regionesNoTip = result.getOutputResultList(ListaPersonasRegionesClob.REGIONES_C);
        List<Regions> regiones = result.getOutputResultList(ListaPersonasRegionesClob.REGIONES_C, Regions.class);        
        Assert.assertTrue(regionesNoTip.size() > 0 && regiones.size() == regionesNoTip.size());
    }
    
    @Test
    public void inputOutputParameterTest(){
        Object codEntradaNoTip = result.getOutputParameter(ListaPersonasRegionesClob.COD_ENTRADA);
        String codEntrada = result.getOutputParameter(ListaPersonasRegionesClob.COD_ENTRADA, String.class);
        Assert.assertNotNull(codEntradaNoTip);
        Assert.assertNotNull(codEntrada);
    }
    
    @Test
    public void outputParameterClobTest(){
        Object clob = result.getOutputParameter(ListaPersonasRegionesClob.OUT_HISTORICO_CLOB);
        Assert.assertNotNull(clob);
        String s = result.getClobAsString(ListaPersonasRegionesClob.OUT_HISTORICO_CLOB);
        Assert.assertNotNull(s);
    }
}
