/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 药品实体类
 * @author zjj
 */
public class Drug {
    private final StringProperty cname;
    private final StringProperty ename;
    private final StringProperty mdseCName;
    private final StringProperty mdseEName;
    private final StringProperty stdCode;
    private final StringProperty type;
    private final StringProperty dosageForm;
    private final StringProperty spec;
    private final StringProperty appvNumber;
    private final StringProperty origAppvNumber;
    private final ObjectProperty<LocalDate> appvDate;
    private final StringProperty mfrName;
    private final StringProperty mfrAddr;
    
    public Drug(){
        this(null,null);
    }
    
    public Drug(String name,String type){
        this.cname=new SimpleStringProperty(name);
        this.type=new SimpleStringProperty(type);
        
        this.ename=new SimpleStringProperty("");
        this.mdseCName=new SimpleStringProperty("");
        this.mdseEName=new SimpleStringProperty("");
        this.stdCode=new SimpleStringProperty("");
        this.dosageForm=new SimpleStringProperty("");
        this.spec=new SimpleStringProperty("");
        this.appvNumber=new SimpleStringProperty("");
        this.origAppvNumber=new SimpleStringProperty("");
        this.appvDate=new SimpleObjectProperty(LocalDate.now());
        this.mfrName=new SimpleStringProperty("");
        this.mfrAddr=new SimpleStringProperty("");
    }
    
    public String getCname() {
        return cname.get();
    }   
    public void setCname(String cname) {
        this.cname.set(cname);
    }
    public StringProperty cnameProperty(){
        return cname;
    }
    
    public String getEname() {
        return ename.get();
    }
    public void setEname(String ename) {
        this.ename.set(ename);
    }
    public StringProperty enameProperty() {
        return ename;
    }

    public String getMdseCName() {
        return mdseCName.get();
    }
    public void setMdseCName(String mdseCName) {
        this.mdseCName.set(mdseCName);
    }
    public StringProperty mdseCNameProperty() {
        return mdseCName;
    }
    
    public String getMdseEName() {
        return mdseEName.get();
    }
    public void setMdseEName(String mdseEName) {
        this.mdseEName.set(mdseEName);
    }
    public StringProperty mdseENameProperty() {
        return mdseEName;
    }

    public String getStdCode() {
        return stdCode.get();
    }
    public void setStdCode(String stdCode) {
        this.stdCode.set(stdCode);
    }
    public StringProperty stdCodeProperty() {
        return stdCode;
    }

    public String getType() {
        return type.get();
    }
    public void setType(String type) {
        this.type.set(type);
    }
    public StringProperty typeProperty() {
        return type;
    }

    public String getDosageForm() {
        return dosageForm.get();
    }
    public void setDosageForm(String dosageForm) {
        this.dosageForm.set(dosageForm);
    }
    public StringProperty dosageFormProperty() {
        return dosageForm;
    }

    public String getSpec() {
        return spec.get();
    }
    public void setSpec(String spec) {
        this.spec.set(spec);
    }
    public StringProperty specProperty() {
        return spec;
    }

    public String getAppvNumber() {
        return appvNumber.get();
    }
    public void setAppvNumber(String appvNumber) {
        this.appvNumber.set(appvNumber);
    }
    public StringProperty appvNumberProperty() {
        return appvNumber;
    }

    public String getOrigAppvNumber() {
        return origAppvNumber.get();
    }
    public void setOrigAppvNumber(String origAppvNumber) {
        this.origAppvNumber.set(origAppvNumber);
    }
    public StringProperty origAppvNumberProperty() {
        return origAppvNumber;
    }

    public LocalDate getAppvDate() {
        return appvDate.get();
    }
    public void setAppvDate(LocalDate appvDate) {
        this.appvDate.set(appvDate);
    }
    public ObjectProperty<LocalDate> appvDateProperty() {
        return appvDate;
    }

    public String getMfrName() {
        return mfrName.get();
    }
    public void setMfrName(String mfrName) {
        this.mfrName.set(mfrName);
    }
    public StringProperty mfrNameProperty() {
        return mfrName;
    }

    public String getMfrAddr() {
        return mfrAddr.get();
    }
    public void setMfrAddr(String mfrAddr) {
        this.mfrAddr.set(mfrAddr);
    }
    public StringProperty mfrAddrProperty() {
        return mfrAddr;
    }

    
}
