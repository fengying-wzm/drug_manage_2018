/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 药品生产厂家实体
 * @author zjj
 */
public class Manufacturer implements IModel{

    private final LongProperty id;
    private final StringProperty code;
    private final StringProperty name;
    private final StringProperty range;
    private final StringProperty socialCreditCode;
    private final StringProperty address;
    private final StringProperty countryCode;
    private final LongProperty regionId;
    private final LongProperty parentId;
    
    
    public Manufacturer(){
        this.id = new SimpleLongProperty();
        this.code = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.range = new SimpleStringProperty();
        this.socialCreditCode = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.countryCode = new SimpleStringProperty();
        this.regionId = new SimpleLongProperty();
        this.parentId = new SimpleLongProperty();
    }

    public Long getId() {
        return id.get();
    }
    public void setId(Long value) {
        id.set(value);
    }
    public LongProperty idProperty() {
        return id;
    }
    
    public String getCode() {
        return code.get();
    }
    public void setCode(String value) {
        code.set(value);
    }
    public StringProperty codeProperty() {
        return code;
    }

    @Override
    public String getName() {
        return name.get();
    }
    public void setName(String value) {
        name.set(value);
    }
    public StringProperty nameProperty() {
        return name;
    }
    
    public String getRange() {
        return range.get();
    }
    public void setRange(String value) {
        range.set(value);
    }
    public StringProperty rangeProperty() {
        return range;
    }
    
    public String getSocialCreditCode() {
        return socialCreditCode.get();
    }
    public void setSocialCreditCode(String value) {
        socialCreditCode.set(value);
    }
    public StringProperty socialCreditCodeProperty() {
        return socialCreditCode;
    }
    
    public String getAddress() {
        return address.get();
    }
    public void setAddress(String value) {
        address.set(value);
    }
    public StringProperty addressProperty() {
        return address;
    }
    
    public String getCountryCode() {
        return countryCode.get();
    }
    public void setCountryCode(String value) {
        countryCode.set(value);
    }
    public StringProperty countryCodeProperty() {
        return countryCode;
    }
    
    public Long getRegionId() {
        return regionId.get();
    }
    public void setRegionId(Long value) {
        if (value==null){
            return;
        }
        regionId.set(value);
    }
    public LongProperty regionIdProperty() {
        return regionId;
    }
   
    public Long getParentId() {
        return parentId.get();
    }
    public void setParentId(Long value) {
        if (value==null){
            return;
        }
        parentId.set(value);
    }
    public LongProperty parentIdProperty() {
        return parentId;
    }
    
}
