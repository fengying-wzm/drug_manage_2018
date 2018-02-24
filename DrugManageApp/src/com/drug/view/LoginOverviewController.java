/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view;

import com.drug.DrugManageApp;
import com.drug.model.User;
import com.drug.util.HttpMethod;
import com.drug.util.IPaddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.sf.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class LoginOverviewController implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    
    private DrugManageApp drugManageApp;
    
    @FXML
    private void gotoMainOverview() throws Exception{
        String userName=this.userNameField.getText().trim();
        String pwd=this.passwordField.getText().trim();
        
        userName=URLEncoder.encode(userName, "UTF-8");
        pwd=URLEncoder.encode(pwd, "UTF-8");
        
        String jsonStr=HttpMethod.getGETString(IPaddress.MATHOD_LOGIN+"?userName="+userName+"&password="+pwd);
         if (jsonStr==null || jsonStr.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("登录失败");
            alert.setHeaderText(null);
            alert.setContentText("账户或密码错误，登录失败！");
            alert.showAndWait();
            return;
        }
         
        JSONObject jsonObj=JSONObject.fromObject(jsonStr);
        User user=(User)JSONObject.toBean(jsonObj, User.class);
        
        this.drugManageApp.setCurrentUser(user);
        this.drugManageApp.initMainOverview();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setDrugManageApp(DrugManageApp drugManageApp) {
        this.drugManageApp = drugManageApp;
    }
}
