package com.techandsolve.easymapper4j.core;

import com.techandsolve.easymapper4j.core.StoredProcedureExecutor;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class TestUtils {
    
    public static DataSource createTestDataSource(String user, String pass){ 
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        dataSource.setUsername(user);
        dataSource.setPassword(pass);        
        return dataSource;
    }
    
    public static StoredProcedureExecutor createTestExecutor(){
        return new StoredProcedureExecutor(createTestDataSource("hr", "rszthwco"));
    }
    
    public static StoredProcedureExecutor createOtherSchemaTestExecutor(){
       return new StoredProcedureExecutor(createTestDataSource("prueba", "prueba")); 
    }

    static StoredProcedureExecutor createColectivosExecutor() {
        return new StoredProcedureExecutor(createColectivosDataSource());
    }

    private static DataSource createColectivosDataSource() {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@10.192.16.100:1523:OF0");
        dataSource.setUsername("webcolec");
        dataSource.setPassword("webcolec");        
        return dataSource;
    }
    
}
