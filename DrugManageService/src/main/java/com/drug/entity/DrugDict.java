/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 药品字典实体
 * @author zjj
 */
@Entity
@Table(name="dict_drug_working")
public class DrugDict implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="appv_number")
    private String appvNumber;
    @Column(name="generic_cname")
    private String genericCName;
    @Column(name="generic_ename")
    private String genericEName;
    @Column(name="mdse_cname")
    private String mdseCName;
    @Column(name="mdse_ename")
    private String mdseEName;
    @Column(name="drug_category")
    private String category;
    @Column(name="formulation")
    private String formulation;
    @Column(name="drug_unit")
    private String drugUnit;
    @Column(name="main_dosage")
    private Double mainDosage;
    @Column(name="main_dosage_unit")
    private String mainDosageUnit;
    @Column(name="2nd_dosage")
    private Double sndDosage;
    @Column(name="2nd_dosage_unit")
    private String sndDosageUnit;
    @Column(name="spec")
    private String spec;
    @Column(name="spec_note")
    private String specNote;
    @Column(name="main_pkg_unit")
    private String mainPkgUnit;
    @Column(name="main_pkg_spec")
    private Integer mainPkgSpec;
    @Column(name="2nd_pkg_unit")
    private String sndPkgUnit;
    @Column(name="2nd_pkg_spec")
    private Integer sndPkgSpec;
    @Column(name="3rd_pkg_unit")
    private String trdPkgUnit;
    @Column(name="mfr_id")
    private Long mfrId;
    @Column(name="appv_date")
    private LocalDate appvDate;
    @Column(name="org_appv_number")
    private String orgAppvNumber;
    @Column(name="std_code")
    private String stdCode;
    @Column(name="std_code_note")
    private String stdCodeNote;
    @Column(name="atc_id")
    private Long atcId;
    @Column(name="status")
    private String status;
    @Column(name="created_by_id")
    private Integer createdById;
    @Column(name="created_on")
    private LocalDateTime createdOn;
    @Column(name="source_id")
    private Long sourceId;
    @Column(name="task_id")
    private Long taskId;

    public DrugDict(){
    
    }
    public DrugDict(Long id){
        this.id=id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppvNumber() {
        return appvNumber;
    }

    public void setAppvNumber(String appvNumber) {
        this.appvNumber = appvNumber;
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

    public String getMdseCName() {
        return mdseCName;
    }

    public void setMdseCName(String mdseCName) {
        this.mdseCName = mdseCName;
    }

    public String getMdseEName() {
        return mdseEName;
    }

    public void setMdseEName(String mdseEName) {
        this.mdseEName = mdseEName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public String getDrugUnit() {
        return drugUnit;
    }

    public void setDrugUnit(String drugUnit) {
        this.drugUnit = drugUnit;
    }

    public Double getMainDosage() {
        return mainDosage;
    }

    public void setMainDosage(Double mainDosage) {
        this.mainDosage = mainDosage;
    }

    public String getMainDosageUnit() {
        return mainDosageUnit;
    }

    public void setMainDosageUnit(String mainDosageUnit) {
        this.mainDosageUnit = mainDosageUnit;
    }

    public Double getSndDosage() {
        return sndDosage;
    }

    public void setSndDosage(Double sndDosage) {
        this.sndDosage = sndDosage;
    }

    public String getSndDosageUnit() {
        return sndDosageUnit;
    }

    public void setSndDosageUnit(String sndDosageUnit) {
        this.sndDosageUnit = sndDosageUnit;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSpecNote() {
        return specNote;
    }

    public void setSpecNote(String specNote) {
        this.specNote = specNote;
    }

    public String getMainPkgUnit() {
        return mainPkgUnit;
    }

    public void setMainPkgUnit(String mainPkgUnit) {
        this.mainPkgUnit = mainPkgUnit;
    }

    public Integer getMainPkgSpec() {
        return mainPkgSpec;
    }

    public void setMainPkgSpec(Integer mainPkgSpec) {
        this.mainPkgSpec = mainPkgSpec;
    }

    public String getSndPkgUnit() {
        return sndPkgUnit;
    }

    public void setSndPkgUnit(String sndPkgUnit) {
        this.sndPkgUnit = sndPkgUnit;
    }

    public Integer getSndPkgSpec() {
        return sndPkgSpec;
    }

    public void setSndPkgSpec(Integer sndPkgSpec) {
        this.sndPkgSpec = sndPkgSpec;
    }

    public String getTrdPkgUnit() {
        return trdPkgUnit;
    }

    public void setTrdPkgUnit(String trdPkgUnit) {
        this.trdPkgUnit = trdPkgUnit;
    }

    public Long getMfrId() {
        return mfrId;
    }

    public void setMfrId(Long mfrId) {
        this.mfrId = mfrId;
    }

    public LocalDate getAppvDate() {
        return appvDate;
    }

    public void setAppvDate(LocalDate appvDate) {
        this.appvDate = appvDate;
    }

    public String getOrgAppvNumber() {
        return orgAppvNumber;
    }

    public void setOrgAppvNumber(String orgAppvNumber) {
        this.orgAppvNumber = orgAppvNumber;
    }

    public String getStdCode() {
        return stdCode;
    }

    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

    public String getStdCodeNote() {
        return stdCodeNote;
    }

    public void setStdCodeNote(String stdCodeNote) {
        this.stdCodeNote = stdCodeNote;
    }

    public Long getAtcId() {
        return atcId;
    }

    public void setAtcId(Long atcId) {
        this.atcId = atcId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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
