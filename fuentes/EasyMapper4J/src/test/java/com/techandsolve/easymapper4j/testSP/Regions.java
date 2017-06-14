/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techandsolve.easymapper4j.testSP;

import com.techandsolve.easymapper4j.model.annotations.Field;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Daniel Bustamante <daniel.bustamante>
 */

public class Regions implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Field(name="REGION_ID")
    private BigDecimal regionId;
       
    @Field(name="REGION_NAME")
    private String regionName;

    public Regions() {
    }

    public Regions(BigDecimal regionId) {
        this.regionId = regionId;
    }

    public BigDecimal getRegionId() {
        return regionId;
    }

    public void setRegionId(BigDecimal regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regionId != null ? regionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regions)) {
            return false;
        }
        Regions other = (Regions) object;
        if ((this.regionId == null && other.regionId != null) || (this.regionId != null && !this.regionId.equals(other.regionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "base.model.Regions[ regionId=" + regionId + " ]";
    }
    
}
