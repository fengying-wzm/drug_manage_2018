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
 * 用户实体类
 * @author zjj
 */
public class User implements IModel{
    
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty password;
    private final IntegerProperty roleId;
//    private final IntegerProperty authorityId;

     public User() {
        this(null,null,0);
    }
    
    public User(String name,String pwd,int roleId) {
        this.id=new SimpleIntegerProperty();
        this.name=new SimpleStringProperty(name);
        this.password=new SimpleStringProperty(pwd);
        this.roleId=new SimpleIntegerProperty(roleId);
//        this.authorityId=new SimpleIntegerProperty(authorityId);
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
    
    public String getPassword() {
        return password.get();
    }
    public void setPassword(String value) {
        password.set(value);
    }
    public StringProperty pwdProperty() {
        return password;
    }

    public int getRoleId() {
        return roleId.get();
    }
    public void setRoleId(int value) {
        roleId.set(value);
    }
    public IntegerProperty roleIdProperty() {
        return roleId;
    }

//    public int getAuthorityId() {
//        return authorityId.get();
//    }
//    public void setAuthorityId(int value) {
//        authorityId.set(value);
//    }
//    public IntegerProperty authorityIdProperty() {
//        return authorityId;
//    }
}
