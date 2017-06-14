package com.techandsolve.easymapper4j.jdbc;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que encapsula y centraliza la dependencia de SPMapper con Spring JdbcTemplate.
 * Todas las adaptaciones (sobreescritura de metodos) que se tengan que hacer al comportamiento de 
 * JdbcTemplate deberan ir en esta clase.
 * 
 * @author Daniel Bustamante <daniel.bustamante>
 */
public class JdbcTemplateSupport extends JdbcTemplate{

    public JdbcTemplateSupport() {
    }

    public JdbcTemplateSupport(DataSource dataSource) {
        super(dataSource);
    }

    public JdbcTemplateSupport(DataSource dataSource, boolean lazyInit) {
        super(dataSource, lazyInit);
    }
}
