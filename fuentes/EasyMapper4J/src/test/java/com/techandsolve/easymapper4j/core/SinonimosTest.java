
package com.techandsolve.easymapper4j.core;

import com.techandsolve.easymapper4j.core.StoredProcedureExecutor;
import com.techandsolve.easymapper4j.mapping.DynamicRowMapper;
import com.techandsolve.easymapper4j.mapping.MappingDescriptorFactory;
import com.techandsolve.easymapper4j.result.FunctionResult;
import com.techandsolve.easymapper4j.testSP.EncontrarPersonaSP;
import com.techandsolve.easymapper4j.testSP.Persona;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
//import oracle.jdbc.OracleTypes;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 * Test para soluciones del problema de los sinonimos
 * @author daniel.bustamante
 */
public class SinonimosTest {
    
    private static StoredProcedureExecutor schemaOwnerExecutor;
    private static StoredProcedureExecutor otherSchemaExecutor;
    
    @BeforeClass
    public static void inicializarExecutor(){
        schemaOwnerExecutor = TestUtils.createTestExecutor();
        otherSchemaExecutor = TestUtils.createOtherSchemaTestExecutor();
    }
    
    @Test 
    public void buscarPersonaSinonimTest(){
        buscarPersonaTest(otherSchemaExecutor);
    }
    
    @Test 
    public void buscarPersonaOwnerTest(){
        buscarPersonaTest(schemaOwnerExecutor);
    }
    
    @Test
    public void buscarPersonaNoMetaDataTest(){
        SimpleJdbcCall call = new SimpleJdbcCall(TestUtils.createTestDataSource("prueba", "prueba"));
        call.withFunctionName("encontrar_persona");
        call.withCatalogName("PERSONAS_PKG");
        //call.returningResultSet("return", new DynamicRowMapper(MappingDescriptorFactory.createMappingDescriptor(Persona.class)));
        //call.addDeclaredRowMapper("return", new DynamicRowMapper(MappingDescriptorFactory.createMappingDescriptor(Persona.class)));
        
        //TODO: descomentar y agregar ojdbc para dependencia.
        //call.declareParameters(new SqlOutParameter("return", OracleTypes.CURSOR, new DynamicRowMapper(MappingDescriptorFactory.createMappingDescriptor(Persona.class))));
        call.declareParameters(new SqlParameter("PV_CEDULA", Types.VARCHAR));
        call.declareParameters(new SqlOutParameter("COD_ERROR", Types.NUMERIC));
        call.declareParameters(new SqlOutParameter("MSJ_ERROR", Types.VARCHAR));
          
        call.withoutProcedureColumnMetaDataAccess();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("PV_CEDULA", "1037606041");
        Map<String, Object> resul = call.execute(parametros);
    }
    
    private void buscarPersonaTest(StoredProcedureExecutor executor){
        EncontrarPersonaSP encontrarPersonaSP = new EncontrarPersonaSP("1037606041");
        FunctionResult result = executor.executeFunction(encontrarPersonaSP);
        Assert.assertNotNull(result.getReturnedResultList().get(0));
    }
}
