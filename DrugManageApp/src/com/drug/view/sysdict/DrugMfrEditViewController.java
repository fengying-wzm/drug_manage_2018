/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.sysdict;

import com.drug.model.Country;
import com.drug.model.Manufacturer;
import com.drug.model.Region;
import com.drug.util.StringListCell;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class DrugMfrEditViewController implements Initializable {

    public static final String VIEW="view";
    public static final String ADD="add";
    public static final String EDIT="edit";
    
    @FXML
    private TextField mfrCode_field;
    @FXML
    private TextField mfrName_field;
    @FXML
    private TextArea mfrRange_tarea;
    @FXML
    private TextField mfrSocialCreditCode_field;
    @FXML
    private TextField mfrAddress_field;
    @FXML
    private ComboBox<Country> mfrCountry_cbox;
    @FXML
    private ComboBox<Region> mfrRegion_cbox;
    @FXML
    private ComboBox<Manufacturer> mfrParent_cbox;
    
    private ObservableList<Country> countryList;
    private ObservableList<Region> regionList;
    private ObservableList<Manufacturer> mfrList;
    
    private Manufacturer currMfr;
    private String type;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mfrCountry_cbox.setButtonCell(new StringListCell());
        this.mfrCountry_cbox.setCellFactory(view->new StringListCell());
        
        this.mfrRegion_cbox.setButtonCell(new StringListCell());
        this.mfrRegion_cbox.setCellFactory(view->new StringListCell());
        
        this.mfrParent_cbox.setButtonCell(new StringListCell());
        this.mfrParent_cbox.setCellFactory(view->new StringListCell());
        
    }    
    
    public void setType(String type){
        this.type=type;
        
        if (!type.equals(VIEW)){
            this.mfrCode_field.setEditable(true);
            this.mfrName_field.setEditable(true);
            this.mfrRange_tarea.setEditable(true);
            this.mfrSocialCreditCode_field.setEditable(true);
            this.mfrAddress_field.setEditable(true);
            this.mfrCountry_cbox.setDisable(false);
            this.mfrRegion_cbox.setDisable(false);
            this.mfrParent_cbox.setDisable(false);
        }
    }
    
    public void setCountryList(ObservableList<Country> list){
        this.countryList=list;
        if (this.currMfr!=null){
            this.selectCountry();
        }
    }
    
    public void setRegionList(ObservableList<Region> list){
        this.regionList=list;
        if (this.currMfr!=null){
            this.selectRegion();
        }
    }
    
    public void setMfrList(ObservableList<Manufacturer> list){
        this.mfrList=list;
        if (this.currMfr!=null){
            this.selectParent();
        }
    }
    
    public void setDrugMfr(Manufacturer mfr){
        this.currMfr=mfr;
        
        this.mfrCode_field.setText(null);
        this.mfrName_field.setText(null);
        this.mfrRange_tarea.setText(null);
        this.mfrSocialCreditCode_field.setText(null);
        this.mfrAddress_field.setText(null);
        this.mfrCountry_cbox.getSelectionModel().clearSelection();
        this.mfrRegion_cbox.getSelectionModel().clearSelection();
        this.mfrParent_cbox.getSelectionModel().clearSelection();
        
        if (this.currMfr!=null){
            this.mfrCode_field.setText(this.currMfr.getCode());
            this.mfrName_field.setText(this.currMfr.getName());
            this.mfrRange_tarea.setText(this.currMfr.getRange());
            this.mfrSocialCreditCode_field.setText(this.currMfr.getSocialCreditCode());
            this.mfrAddress_field.setText(this.currMfr.getAddress());
            if (this.countryList!=null && this.currMfr.getCountryCode()!=null && !this.currMfr.getCountryCode().isEmpty()){
                this.selectCountry();
            }
            
            if (this.regionList!=null && this.currMfr.getRegionId()!=null && this.currMfr.getRegionId()>0){
                this.selectRegion();
            }
            
            if (this.mfrList!=null && this.currMfr.getParentId()!=null && this.currMfr.getParentId()>0){
                this.selectParent();
            }
        }
    }
    
    public Manufacturer getResult(){
        Manufacturer mfr=new Manufacturer();
        mfr.setRegionId(Long.valueOf(1));
        if (this.type.equals(EDIT)){
            mfr.setId(this.currMfr.getId());
        }
        
        mfr.setCode(this.mfrCode_field.getText());
        mfr.setName(this.mfrName_field.getText());
        mfr.setRange(this.mfrRange_tarea.getText());
        mfr.setSocialCreditCode(this.mfrSocialCreditCode_field.getText());
        mfr.setAddress(this.mfrAddress_field.getText());
        if (this.mfrCountry_cbox.getSelectionModel().getSelectedItem()!=null){
            mfr.setCountryCode(this.mfrCountry_cbox.getSelectionModel().getSelectedItem().getCode());
        }
        if (this.mfrRegion_cbox.getSelectionModel().getSelectedItem()!=null){
            mfr.setRegionId(this.mfrRegion_cbox.getSelectionModel().getSelectedItem().getId());
        }
        if (this.mfrParent_cbox.getSelectionModel().getSelectedItem()!=null){
            mfr.setParentId(this.mfrParent_cbox.getSelectionModel().getSelectedItem().getId());
        }
        
        return mfr;
    }
    
    private void selectCountry(){
        this.countryList.forEach(country->{
            if (country.getCode()==this.currMfr.getCountryCode()){
                this.mfrCountry_cbox.getSelectionModel().select(country);
                return;
            }
        });
    }
    
    private void selectRegion(){
        this.regionList.forEach(region->{
            if (region.getId()==this.currMfr.getRegionId()){
                this.mfrRegion_cbox.getSelectionModel().select(region);
                return;
            }
        });
    }
    
    private void selectParent(){
        this.mfrList.forEach(mfr->{
            if (mfr.getId()==this.currMfr.getParentId()){
                this.mfrParent_cbox.getSelectionModel().select(mfr);
                return;
            }
        });
        
    }
}
