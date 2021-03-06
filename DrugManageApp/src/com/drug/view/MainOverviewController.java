/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view;

import com.drug.DrugManageApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 *
 * @author zjj
 */
public class MainOverviewController implements Initializable {
    @FXML
    private Menu userMgtMenu;
    @FXML
    private Menu taskMgtMenu;
    @FXML
    private Menu drugStdDicMenu;
    @FXML
    private Menu sysDictMenu;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    
    private DrugManageApp drugManageApp;

    @FXML
    private void showUserMgtOverview(){
        try {
            this.drugManageApp.showUserMgtOverview();
        } catch (Exception ex) {
            Logger.getLogger(MainOverviewController.class.getName()).log(Level.INFO, "用户管理视图显示异常！", ex);
        }
    }
    @FXML
    private void showTaskMgtOverview(){
        try {
            this.drugManageApp.showTaskMgtOverview();
        } catch (Exception ex) {
            Logger.getLogger(MainOverviewController.class.getName()).log(Level.INFO, "任务管理视图显示异常！", ex);
        }
    }
    @FXML
    private void showDrugStdDictMgtOverview(){
        try {
            this.drugManageApp.showDrugDictMgtOverview();
        } catch (Exception ex) {
            Logger.getLogger(MainOverviewController.class.getName()).log(Level.INFO, "标准字典维护视图显示异常！", ex);
        }
    }
    @FXML
    private void showSysDictMgtOverview(){
        try {
            this.drugManageApp.showSysDictMgtOverview();
        } catch (Exception ex) {
            Logger.getLogger(MainOverviewController.class.getName()).log(Level.INFO, "系统字典维护视图显示异常！", ex);
        }
    }
    @FXML
    private void showAboutOverview(){
        try {
            this.drugManageApp.showAboutOverview();
        } catch (Exception ex) {
            Logger.getLogger(MainOverviewController.class.getName()).log(Level.INFO, "关于视图显示异常！", ex);
        }
    }
    @FXML
    private void close() throws IOException{
        this.drugManageApp.initLoginOverview();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label userLbl=new Label("用户管理");
        userLbl.setOnMouseClicked(e->{
            this.showUserMgtOverview();
        });
        this.userMgtMenu.setGraphic(userLbl);
        
        Label taskLbl=new Label("任务管理");
        taskLbl.setOnMouseClicked(e->{
            this.showTaskMgtOverview();
        });
        this.taskMgtMenu.setGraphic(taskLbl);
        
        Label drugStdDicLbl=new Label("药品标准字典维护");
        drugStdDicLbl.setOnMouseClicked(e->{
            this.showDrugStdDictMgtOverview();
        });
        this.drugStdDicMenu.setGraphic(drugStdDicLbl);
        
        Label sysDictLbl=new Label("系统字典维护");
        sysDictLbl.setOnMouseClicked(e->{
            this.showSysDictMgtOverview();
        });
        this.sysDictMenu.setGraphic(sysDictLbl);
        
    }
    
    public void setDrugManageApp(DrugManageApp drugManageApp) {
        this.drugManageApp = drugManageApp;
    }
    
}
