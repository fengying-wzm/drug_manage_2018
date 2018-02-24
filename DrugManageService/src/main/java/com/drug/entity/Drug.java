/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author zjj
 */
@Entity
@Table(name="cfda_drug_info_cdi")
public class Drug implements Serializable{
    @Id
    @Column(name="id")
    private String id;
    
    @Column(name="cdi_drug_cname",nullable=false)
    private String name_c;
    
    @Column(name="cdi_drug_ename")
    private String name_e;
    
    @Column(name="cdi_drug_mdse_cname")
    private String mdse_name_c;
    
    @Column(name="cdi_drug_mdse_ename")
    private String mdse_name_e;
    
    @Column(name="cdi_drug_type",nullable=false)
    private String type;
    
    @Column(name="cdi_dosage_form",nullable=false)
    private String dosage_form;
    
    @Column(name="cdi_spec",nullable=false)
    private String spec;
    
    @Column(name="cdi_std_code",nullable=false)
    private String std_code;
    
    @Column(name="cdi_appv_number",nullable=false)
    private String appv_number;
    
    @Column(name="cdi_orig_appv_number")
    private String orig_appv_number;
    
    @Temporal(TemporalType.DATE)
    @Column(name="cdi_appv_date")
    private Date appv_date;
    
    @Column(name="cdi_mfr_name",nullable=false)
    private String mfr_name;
    
    @Column(name="cdi_mfr_addr",nullable=false)
    private String mfr_addr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_c() {
        return name_c;
    }

    public void setName_c(String name_c) {
        this.name_c = name_c;
    }

    public String getName_e() {
        return name_e;
    }

    public void setName_e(String name_e) {
        this.name_e = name_e;
    }

    public String getMdse_name_c() {
        return mdse_name_c;
    }

    public void setMdse_name_c(String mdse_name_c) {
        this.mdse_name_c = mdse_name_c;
    }

    public String getMdse_name_e() {
        return mdse_name_e;
    }

    public void setMdse_name_e(String mdse_name_e) {
        this.mdse_name_e = mdse_name_e;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDosage_form() {
        return dosage_form;
    }

    public void setDosage_form(String dosage_form) {
        this.dosage_form = dosage_form;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getStd_code() {
        return std_code;
    }

    public void setStd_code(String std_code) {
        this.std_code = std_code;
    }

    public String getAppv_number() {
        return appv_number;
    }

    public void setAppv_number(String appv_number) {
        this.appv_number = appv_number;
    }

    public String getOrig_appv_number() {
        return orig_appv_number;
    }

    public void setOrig_appv_number(String orig_appv_number) {
        this.orig_appv_number = orig_appv_number;
    }

    public Date getAppv_date() {
        return appv_date;
    }

    public void setAppv_date(Date appv_date) {
        this.appv_date = appv_date;
    }

    public String getMfr_name() {
        return mfr_name;
    }

    public void setMfr_name(String mfr_name) {
        this.mfr_name = mfr_name;
    }

    public String getMfr_addr() {
        return mfr_addr;
    }

    public void setMfr_addr(String mfr_addr) {
        this.mfr_addr = mfr_addr;
    }
    
}
