package com.techandsolve.easymapper4j.core;

import com.techandsolve.easymapper4j.core.StoredProcedureExecutor;
import com.techandsolve.easymapper4j.result.FunctionResult;
import com.techandsolve.easymapper4j.testSP.ListaPersonasRegionesClobHerencia;
import com.techandsolve.easymapper4j.testSP.PersonaEspecial;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Daniel Bustamante Ospina <daniel.bustamante>
 */
public class HerenciaMapeoTest {
    
    private static StoredProcedureExecutor executor;
    
    @BeforeClass
    public static void before(){
        executor = TestUtils.createTestExecutor();
    }
    
    @Test
    public void mapeoPorHerenciaTest(){
        FunctionResult result = executor.executeFunction(new ListaPersonasRegionesClobHerencia());
        List<PersonaEspecial> personas = result.getReturnedResultList(PersonaEspecial.class);
        Assert.assertTrue(personas.size() > 0 && personas.get(0).getCedula() != null);
    }
}
