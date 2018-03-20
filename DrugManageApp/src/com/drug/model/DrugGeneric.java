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
 * 药品通用名字典
 * @author zjj
 */
public class DrugGeneric implements IModel{

    private final LongProperty id;
    private final StringProperty genericCName;
    private final StringProperty genericEName;
    
    public DrugGeneric(){
        this.id=new SimpleLongProperty();
        this.genericCName=new SimpleStringProperty();
        this.genericEName=new SimpleStringProperty();
    }
    
    public long getId() {
        return id.get();
    }

    public void setId(long value) {
        id.set(value);
    }

    public LongProperty idProperty() {
        return id;
    }
    
    public String getGenericCName() {
        return genericCName.get();
    }

    public void setGenericCName(String value) {
        genericCName.set(value);
    }

    public StringProperty genericCNameProperty() {
        return genericCName;
    }
    
    public String getGenericEName() {
        return genericEName.get();
    }

    public void setGenericEName(String value) {
        genericEName.set(value);
    }

    public StringProperty genericENameProperty() {
        return genericEName;
    }

    @Override
    public String getName() {
        return this.getGenericCName()+"（"+this.getGenericEName()+"）";
    }
    
    
}
