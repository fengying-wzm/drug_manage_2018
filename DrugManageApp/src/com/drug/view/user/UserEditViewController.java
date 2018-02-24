/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.user;

import com.drug.model.User;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

import com.drug.util.StringListCell;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class UserEditViewController implements Initializable {

    @FXML
    public TextField userField;
    
    @FXML
    public TextField pwdField;
    
    @FXML
    public ComboBox<Map> role_cbox;
    
    private ObservableList<Map> roleList;
    private User user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userField.textProperty().setValue(null);
        this.pwdField.textProperty().setValue(null);
        this.role_cbox.setItems(null);
        
        this.role_cbox.setButtonCell(new StringListCell());
        
        this.role_cbox.setCellFactory(listView->{
            ListCell listCell=new StringListCell();
            return listCell;
        });
    }    
    
    public void setRoleList(ObservableList<Map> roleList){
        this.roleList=roleList;
        this.role_cbox.setItems(roleList);
        if (this.user!=null){
            this.selectRole();
        }
    }
    
    public void setUser(User user){
        this.user=user;
        this.userField.setText(this.user.getName());
        this.pwdField.setText(this.user.getPassword());
        if (this.roleList!=null){
            this.selectRole();
        }
    }
    
    private void selectRole(){
        this.roleList.forEach(role->{
            if ((int)role.get("id")==this.user.getRoleId()){
               this.role_cbox.setValue(role);
            }
        });
    }
}
