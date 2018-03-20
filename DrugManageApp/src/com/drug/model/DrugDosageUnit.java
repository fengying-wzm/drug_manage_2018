/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 药品剂量单位实体
 * @author zjj
 */
public class DrugDosageUnit implements IModel{

    private final IntegerProperty id;
    private final StringProperty name;
    
    public DrugDosageUnit(){
        this(null);
    }
    
    public DrugDosageUnit(String name){
        this.id=new SimpleIntegerProperty();
        this.name=new SimpleStringProperty(name);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
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
    
    
}
