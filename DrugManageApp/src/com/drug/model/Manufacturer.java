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

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty code = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty range = new SimpleStringProperty();
    private final StringProperty socialCreditCode = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty countryCode = new SimpleStringProperty();
    private final LongProperty regionId = new SimpleLongProperty();
    private final LongProperty parentId = new SimpleLongProperty();

    public long getId() {
        return id.get();
    }
    public void setId(long value) {
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
    
    public long getRegionId() {
        return regionId.get();
    }
    public void setRegionId(long value) {
        regionId.set(value);
    }
    public LongProperty regionIdProperty() {
        return regionId;
    }
   
    public long getParentId() {
        return parentId.get();
    }
    public void setParentId(long value) {
        parentId.set(value);
    }
    public LongProperty parentIdProperty() {
        return parentId;
    }
    
}
