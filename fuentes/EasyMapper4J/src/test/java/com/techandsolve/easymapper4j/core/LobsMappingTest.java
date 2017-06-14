package com.techandsolve.easymapper4j.core;

import com.techandsolve.easymapper4j.core.StoredProcedureExecutor;
import com.techandsolve.easymapper4j.result.FunctionResult;
import com.techandsolve.easymapper4j.result.ProcedureResult;
import com.techandsolve.easymapper4j.testSP.Historico;
import com.techandsolve.easymapper4j.testSP.IngresarHistoricoSP;
import com.techandsolve.easymapper4j.testSP.ListarHistoricosSP;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author daniel.bustamante
 */
public class LobsMappingTest {
    
    private static StoredProcedureExecutor executor;


    @BeforeClass
    public static void setUpClass() throws Exception {       
        executor = TestUtils.createTestExecutor();
    }
      
    @Test 
    public void consultarListaTest(){
        FunctionResult result = executor.executeFunction(new ListarHistoricosSP(2));
        List<Historico> historicos = result.getReturnedResultList(Historico.class);
        Assert.assertNotNull(historicos);
        Assert.assertTrue(historicos.size() > 1);
    }
    
    @Test 
    public void listaVaciaMsjErrorTest(){
       FunctionResult result = executor.executeFunction(new ListarHistoricosSP(6));
       int cod_error = result.getOutputParameter(ListarHistoricosSP.COD_ERROR, Integer.class);
       Assert.assertEquals(cod_error, -1);
       Assert.assertTrue(result.getReturnedResultList(Historico.class).isEmpty());
    }
    
    @Test
    public void inputParameterClobTest(){
        IngresarHistoricoSP historicoSP = new IngresarHistoricoSP();
        historicoSP.setNombre("Clob uno");
        historicoSP.setHistorial("Contenido del CLob de prueba... ");
        ProcedureResult result = executor.executeProcedure(historicoSP);
        Integer resultadoEjecucion = result.getOutputParameter("COD_ERROR", Integer.class);
        Assert.assertNotNull(resultadoEjecucion);
    }
            
    
}
