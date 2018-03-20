/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 药品字典实体
 * @author zjj
 */
public class DrugDict{

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty appvNumber = new SimpleStringProperty();
    private final StringProperty genericCName = new SimpleStringProperty();
    private final StringProperty genericEName = new SimpleStringProperty();
    private final StringProperty mdseCName = new SimpleStringProperty();
    private final StringProperty mdseEName = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
    private final StringProperty formulation = new SimpleStringProperty();
    private final StringProperty drugUnit = new SimpleStringProperty();
    private final DoubleProperty mainDosage = new SimpleDoubleProperty();
    private final StringProperty mainDosageUnit = new SimpleStringProperty();
    private final DoubleProperty sndDosage = new SimpleDoubleProperty();
    private final StringProperty sndDosageUnit = new SimpleStringProperty();
    private final StringProperty spec = new SimpleStringProperty();
    private final StringProperty specNote = new SimpleStringProperty();
    private final StringProperty mainPkgUnit = new SimpleStringProperty();
    private final IntegerProperty mainPkgSpec = new SimpleIntegerProperty();
    private final StringProperty sndPkgUnit = new SimpleStringProperty();
    private final IntegerProperty sndPkgSpec = new SimpleIntegerProperty();
    private final StringProperty trdPkgUnit = new SimpleStringProperty();
    private final LongProperty mfrId = new SimpleLongProperty();
    private final ObjectProperty<LocalDate> appvDate = new SimpleObjectProperty<>();
    private final StringProperty orgAppvNumber = new SimpleStringProperty();
    private final StringProperty stdCode = new SimpleStringProperty();
    private final StringProperty stdCodeNote = new SimpleStringProperty();
    private final LongProperty atcId = new SimpleLongProperty();
    private final StringProperty atcCode = new SimpleStringProperty();
    private final StringProperty atcName = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final IntegerProperty createdById = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDateTime> createdOn = new SimpleObjectProperty<>();
    private final LongProperty sourceId = new SimpleLongProperty();
    private final LongProperty taskId = new SimpleLongProperty();

    public Long getId() {
        return id.getValue();
    }
    public void setId(Long value) {
        id.setValue(value);
    }
    public LongProperty idProperty() {
        return id;
    }

    public String getAppvNumber() {
        return appvNumber.get();
    }
    public void setAppvNumber(String value) {
        appvNumber.set(value);
    }
    public StringProperty appvNumberProperty() {
        return appvNumber;
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
    
    public String getMdseCName() {
        return mdseCName.get();
    }
    public void setMdseCName(String value) {
        mdseCName.set(value);
    }
    public StringProperty mdseCNameProperty() {
        return mdseCName;
    }
   
    public String getMdseEName() {
        return mdseEName.get();
    }
    public void setMdseEName(String value) {
        mdseEName.set(value);
    }
    public StringProperty mdseENameProperty() {
        return mdseEName;
    }
    
    public String getCategory() {
        return category.get();
    }
    public void setCategory(String value) {
        category.set(value);
    }
    public StringProperty categoryProperty() {
        return category;
    }
    
    public String getFormulation() {
        return formulation.get();
    }
    public void setFormulation(String value) {
        formulation.set(value);
    }
    public StringProperty formulationProperty() {
        return formulation;
    }
    
    public String getDrugUnit() {
        return drugUnit.get();
    }
    public void setDrugUnit(String value) {
        drugUnit.set(value);
    }
    public StringProperty drugUnitProperty() {
        return drugUnit;
    }
    
    public Double getMainDosage() {
        return mainDosage.getValue();
    }
    public void setMainDosage(Double value) {
        mainDosage.setValue(value);
    }
    public DoubleProperty mainDosageProperty() {
        return mainDosage;
    }
    
    public String getMainDosageUnit() {
        return mainDosageUnit.get();
    }
    public void setMainDosageUnit(String value) {
        mainDosageUnit.set(value);
    }
    public StringProperty mainDosageUnitProperty() {
        return mainDosageUnit;
    }
   
    public Double getSndDosage() {
        return sndDosage.get();
    }
    public void setSndDosage(Double value) {
        sndDosage.setValue(value);
    }
    public DoubleProperty sndDosageProperty() {
        return sndDosage;
    }
   
    public String getSndDosageUnit() {
        return sndDosageUnit.get();
    }
    public void setSndDosageUnit(String value) {
        sndDosageUnit.set(value);
    }
    public StringProperty sndDosageUnitProperty() {
        return sndDosageUnit;
    }

    public String getSpec() {
        return spec.get();
    }
    public void setSpec(String value) {
        spec.set(value);
    }
    public StringProperty specProperty() {
        return spec;
    }
    
    public String getSpecNote() {
        return specNote.get();
    }
    public void setSpecNote(String value) {
        specNote.set(value);
    }
    public StringProperty specNoteProperty() {
        return specNote;
    }
    
    public String getMainPkgUnit() {
        return mainPkgUnit.get();
    }
    public void setMainPkgUnit(String value) {
        mainPkgUnit.set(value);
    }
    public StringProperty mainPkgUnitProperty() {
        return mainPkgUnit;
    }
    
    public Integer getMainPkgSpec() {
        return mainPkgSpec.get();
    }
    public void setMainPkgSpec(Integer value) {
        mainPkgSpec.setValue(value);
    }
    public IntegerProperty mainPkgSpecProperty() {
        return mainPkgSpec;
    }
    
    public String getSndPkgUnit() {
        return sndPkgUnit.get();
    }
    public void setSndPkgUnit(String value) {
        sndPkgUnit.set(value);
    }
    public StringProperty sndPkgUnitProperty() {
        return sndPkgUnit;
    }
    
    public Integer getSndPkgSpec() {
        return sndPkgSpec.get();
    }
    public void setSndPkgSpec(Integer value) {
        sndPkgSpec.setValue(value);
    }
    public IntegerProperty sndPkgSpecProperty() {
        return sndPkgSpec;
    }
    
    public String getTrdPkgUnit() {
        return trdPkgUnit.get();
    }
    public void setTrdPkgUnit(String value) {
        trdPkgUnit.set(value);
    }
    public StringProperty trdPkgUnitProperty() {
        return trdPkgUnit;
    }
    
    public Long getMfrId() {
        return mfrId.get();
    }
    public void setMfrId(Long value) {
        mfrId.setValue(value);
    }
    public LongProperty mfrIdProperty() {
        return mfrId;
    }
    
    public LocalDate getAppvDate() {
        return appvDate.get();
    }
    public void setAppvDate(LocalDate value) {
        appvDate.set(value);
    }
    public ObjectProperty appvDateProperty() {
        return appvDate;
    }
    
    public String getOrgAppvNumber() {
        return orgAppvNumber.get();
    }
    public void setOrgAppvNumber(String value) {
        orgAppvNumber.set(value);
    }
    public StringProperty orgAppvNumberProperty() {
        return orgAppvNumber;
    }
    
    public String getStdCode() {
        return stdCode.get();
    }
    public void setStdCode(String value) {
        stdCode.set(value);
    }
    public StringProperty stdCodeProperty() {
        return stdCode;
    }

    public String getStdCodeNote() {
        return stdCodeNote.get();
    }
    public void setStdCodeNote(String value) {
        stdCodeNote.set(value);
    }
    public StringProperty stdCodeNoteProperty() {
        return stdCodeNote;
    }
    
    public Long getAtcId() {
        return atcId.get();
    }
    public void setAtcId(Long value) {
        atcId.setValue(value);
    }
    public LongProperty atcIdProperty() {
        return atcId;
    }
    
    public String getStatus() {
        return status.get();
    }
    public void setStatus(String value) {
        status.set(value);
    }
    public StringProperty statusProperty() {
        return status;
    }
    
    public Integer getCreatedById() {
        return createdById.get();
    }
    public void setCreatedById(Integer value) {
        createdById.setValue(value);
    }
    public IntegerProperty createdByIdProperty() {
        return createdById;
    }
    
    public LocalDateTime getCreatedOn() {
        return createdOn.get();
    }
    public void setCreatedOn(LocalDateTime value) {
        createdOn.set(value);
    }
    public ObjectProperty createdOnProperty() {
        return createdOn;
    }
    
    public Long getSourceId() {
        return sourceId.get();
    }
    public void setSourceId(Long value) {
        sourceId.setValue(value);
    }
    public LongProperty sourceIdProperty() {
        return sourceId;
    }
    
    public Long getTaskId() {
        return taskId.get();
    }
    public void setTaskId(Long value) {
        taskId.setValue(value);
    }
    public LongProperty taskIdProperty() {
        return taskId;
    }
    
    public static class DrugStatus{
        public static final String NOASSIGNED="未分配";
        public static final String ASSIGNED_MODIFY="已分配（修订）";
        public static final String MODIFIED="修订完成";
        public static final String ASSIGNED_CHECK="已分配（复核）";
        public static final String CHECKED="复核完成";
        
        public static ObservableList<String> getDrugStatusList(){
            ObservableList<String> drugStatusList=FXCollections.observableArrayList();
            drugStatusList.addAll(NOASSIGNED,ASSIGNED_MODIFY,MODIFIED,ASSIGNED_CHECK,CHECKED);
            
            return drugStatusList;
        }
    }
}
