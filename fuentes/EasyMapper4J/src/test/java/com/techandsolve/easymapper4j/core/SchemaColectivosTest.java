package com.techandsolve.easymapper4j.core;

import com.techandsolve.easymapper4j.core.StoredProcedureExecutor;
import com.techandsolve.easymapper4j.result.FunctionResult;
import com.techandsolve.easymapper4j.testSP.Ciudad;
import com.techandsolve.easymapper4j.testSP.LeerCodigosPostalesSP;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author user
 */
public class SchemaColectivosTest {
 
    private static StoredProcedureExecutor  executor;
    
    //@BeforeClass
    public static void inicializar(){
        executor = TestUtils.createColectivosExecutor();
    }
    
    @Test @Ignore
    public void colectivosTest(){
        FunctionResult result = executor.executeFunction(new LeerCodigosPostalesSP());
        List<Ciudad> ciudades = result.getReturnedResultList(Ciudad.class);
        Assert.assertTrue(!ciudades.isEmpty());
    }
    
}
