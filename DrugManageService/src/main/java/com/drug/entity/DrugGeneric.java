/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 药品通用名实体类
 * @author zjj
 */
@Entity
@Table(name = "drug_generic")
public class DrugGeneric implements Serializable {

    @Id
    @GeneratedValue
    private Long ID;
    
    @Column(name = "generic_cname")
    private String genericCName;
    
    @Column(name = "generic_ename")
    private String genericEName;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getGenericCName() {
        return genericCName;
    }

    public void setGenericCName(String genericCName) {
        this.genericCName = genericCName;
    }

    public String getGenericEName() {
        return genericEName;
    }

    public void setGenericEName(String genericEName) {
        this.genericEName = genericEName;
    }
    
    
}
