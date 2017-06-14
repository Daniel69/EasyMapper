
package com.techandsolve.easymapper4j.testSP;

import com.techandsolve.easymapper4j.model.annotations.Field;

/**
 *
 * @author user
 */
public class Ciudad {
    
    @Field(name="COD_PAIS")
    private String codPais;

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }
    
    
}
