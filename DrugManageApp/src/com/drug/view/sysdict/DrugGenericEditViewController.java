/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.sysdict;

import com.drug.model.DrugGeneric;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class DrugGenericEditViewController implements Initializable {
    
    public static final String VIEW="view";
    public static final String ADD="add";
    public static final String EDIT="edit";
    
    @FXML
    private TextField genericCName_field; 
    @FXML
    private TextField genericEName_field; 
    
    private DrugGeneric currDrugGeneric;
    private String type;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setType(String type){
        this.type=type;
        
        if (!type.equals(VIEW)){
            this.genericCName_field.setEditable(true);
            this.genericEName_field.setEditable(true);
        }
    }
    public void setDrugGeneric(DrugGeneric generic){
        this.currDrugGeneric=generic;
        
        this.genericCName_field.setText(null);
        this.genericEName_field.setText(null);
        
        if (this.currDrugGeneric!=null){
            this.genericCName_field.setText(this.currDrugGeneric.getGenericCName());
            this.genericEName_field.setText(this.currDrugGeneric.getGenericEName());
        }
    }
    
    public DrugGeneric getResult(){
        DrugGeneric generic=new DrugGeneric();
        if (this.type.equals(EDIT)){
            generic.setId(this.currDrugGeneric.getId());
        }
        generic.setGenericCName(this.genericCName_field.getText());
        generic.setGenericEName(this.genericEName_field.getText());
        
        return generic;
    }
    
}
