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
 * 地区字典实体
 * @author zjj
 */
public class Region implements IModel{

    private final LongProperty id;
    private final StringProperty code;
    private final StringProperty name;
    private final LongProperty parentId;
    
    public Region(){
        this.id = new SimpleLongProperty();
        this.code = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.parentId = new SimpleLongProperty();
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
