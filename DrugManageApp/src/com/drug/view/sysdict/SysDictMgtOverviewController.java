/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.sysdict;

import com.drug.DrugManageApp;
import com.drug.model.Country;
import com.drug.model.DrugGeneric;
import com.drug.model.Manufacturer;
import com.drug.util.HttpMethod;
import com.drug.util.IPaddress;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class SysDictMgtOverviewController implements Initializable {

    public  static final String VIEW="view"; 
    public  static final String ADD="add"; 
    public  static final String EDIT="edit";
    
    //药品通用名字典
    @FXML
    private TextField genericName_field;
    @FXML
    private Button addGenericBtn;
    @FXML
    private TableView<DrugGeneric> genericTableView;
    @FXML
    private TableColumn<DrugGeneric,String> genericCName_column;
    @FXML
    private TableColumn<DrugGeneric,String> genericEName_column;
    @FXML
    private TableColumn<DrugGeneric,HBox> genericOpt_column;
    
    //药品生产厂家字典
    @FXML
    private TextField mfrName_field;
    @FXML
    private ComboBox<Country> country_cbox;
    @FXML
    private CheckBox clearMfrFilter_chkbox;
    @FXML
    private Button addMfrBtn;
    @FXML
    private TableView<Manufacturer> mfrTableView;
    @FXML
    private TableColumn<Manufacturer,String> mfrCode_column;
    @FXML
    private TableColumn<Manufacturer,String> mfrName_column;
    @FXML
    private TableColumn<Manufacturer,String> mfrRange_column;
    @FXML
    private TableColumn<Manufacturer,String> mfrSocialCreditCode_column;
    @FXML
    private TableColumn<Manufacturer,String> mfrAddress_column;
    @FXML
    private TableColumn<Manufacturer,String> mfrCountryCode_column;
    @FXML
    private TableColumn<Manufacturer,String> mfrRegion_column;
    @FXML
    private TableColumn<Manufacturer,HBox> mfrOpt_column;
    
    private DrugManageApp drugManageApp;
    private ObservableList<DrugGeneric> drugGenericData;
    private Map currGenericPage;
    private ObservableList<Manufacturer> drugMfrData;
    private Map currMfrPage;
    
    @FXML
    private void addGeneric(){
        this.popupDrugGenericEditView("添加通用名",ADD , null);
    }
    
    @FXML
    private void addMfr(){
        this.popupDrugMfrEditView("添加生产厂家", ADD, null);
    }
    
    @FXML
    private ObservableList<DrugGeneric> queryDrugGenericData(){
        String mathodName=IPaddress.MATHOD_GET_DRUG_GENERIC_DATA;
        
        String genericName=this.genericName_field.getText();
        if (genericName!=null && !genericName.trim().isEmpty()){
            genericName=genericName.trim();
            try {
                mathodName+="?name="+URLEncoder.encode(genericName, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SysDictMgtOverviewController.class.getName()).log(Level.INFO, "URLEncoder.encode()编码异常！", ex);
            }
        }
        String jsonstr=HttpMethod.getGETString(mathodName);
        JSONObject jsonObj=JSONObject.fromObject(jsonstr);
        Map page=(Map)JSONObject.toBean(jsonObj, Map.class);
        this.currGenericPage=page;
        JSONArray jsonArr=JSONArray.fromObject(page.get("content"));
        List<DrugGeneric> list=(List<DrugGeneric>)JSONArray.toCollection(jsonArr, DrugGeneric.class);
        this.drugGenericData=FXCollections.observableList(list);
        this.genericTableView.setItems(this.drugGenericData);
        
        return this.drugGenericData;
    }
    
    @FXML
    private void enableClearMfrFilter(){
        this.clearMfrFilter_chkbox.setDisable(false);
        this.clearMfrFilter_chkbox.setSelected(false);
    }
    @FXML
    private void clearMfrFilter(){
        this.mfrName_field.setText(null);
        this.country_cbox.getSelectionModel().clearSelection();
        
        this.clearMfrFilter_chkbox.setDisable(true);
        this.queryDrugMfrData();
    }
    @FXML
    private ObservableList<Manufacturer> queryDrugMfrData(){
        String mathodName=IPaddress.MATHOD_GET_DRUG_MFR_DATA;
        
        String mfrName=this.mfrName_field.getText();
        if (mfrName!=null && !mfrName.trim().isEmpty()){
            mfrName=mfrName.trim();
            try {
                mathodName+="?name="+URLEncoder.encode(mfrName, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SysDictMgtOverviewController.class.getName()).log(Level.INFO, "URLEncode.encode()编码出错！", ex);
            }
        }
        
        Country country=this.country_cbox.getSelectionModel().getSelectedItem();
        if (country!=null){
            int index=mathodName.indexOf("?");
            if (index>-1){
                mathodName+="&countryCode="+country.getCode();
            }
        }
        
        String jsonstr=HttpMethod.getGETString(mathodName);
        JSONObject jsonObj=JSONObject.fromObject(jsonstr);
        Map page=(Map)JSONObject.toBean(jsonObj, Map.class);
        this.currMfrPage=page;
        JSONArray jsonArr=JSONArray.fromObject(page.get("content"));
        List<Manufacturer> list=(List<Manufacturer>)JSONArray.toCollection(jsonArr,Manufacturer.class);
        this.drugMfrData=FXCollections.observableList(list);
        this.mfrTableView.setItems(this.drugMfrData);
        
        return this.drugMfrData;
    }
    
    private void popupDrugGenericEditView(String title,String type,DrugGeneric generic){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(this.getClass().getResource("DrugGenericEditView.fxml"));
            AnchorPane genericEditView=(AnchorPane)loader.load();
            DrugGenericEditViewController drugGenericEditController=loader.getController();
            drugGenericEditController.setType(type);
            drugGenericEditController.setDrugGeneric(generic);
            
            Dialog dialog=new Dialog();
            dialog.setTitle(title);
            
            String okBtnText=null;
            switch(type){
                case VIEW:
                    okBtnText="确定";
                    break;
                case ADD:
                    okBtnText="添加";
                    break;
                case EDIT:
                    okBtnText="修改";
                    break;
            }
            
            ButtonType okBtn=new ButtonType(okBtnText);
            dialog.getDialogPane().getButtonTypes().addAll(okBtn,ButtonType.CANCEL);
            dialog.getDialogPane().setContent(genericEditView);
            
            if (!type.equals(VIEW)){
                dialog.setResultConverter(btn->{
                    if (btn==okBtn){
                        return drugGenericEditController.getResult();
                    }
                    else{
                        return null;
                    }
                });
            }
            
            Optional<DrugGeneric> result=dialog.showAndWait();
            if (result!=null && result.get()!=null){
                String mathodName=null;
                if (type.equals(ADD)){
                    mathodName=IPaddress.MATHOD_ADD_DRUG_GENERIC;
                }
                if (type.equals(EDIT)){
                    mathodName=IPaddress.MATHOD_UPDATE_DRUG_GENERIC;
                }
                
                JSONObject jsonObj=JSONObject.fromObject(result.get());
                String msg="添加/修改药品通用名发生异常！";
                try {
                    String jsonstr=HttpMethod.sendPOSTString(mathodName, jsonObj.toString(), "UTF-8");
                    if (jsonstr!=null){
                        this.queryDrugGenericData();
                    }
                    else{
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText(msg);
                        alert.show();
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(SysDictMgtOverviewController.class.getName()).log(Level.SEVERE,msg, ex);
                }
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(SysDictMgtOverviewController.class.getName()).log(Level.INFO, "通用名编辑页面DrugGenericEditView.fxml显示异常！", ex);
        }
    }
    
    private void popupDrugMfrEditView(String title,String type,Manufacturer mfr){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(this.getClass().getResource("DrugMfrEditView.fxml"));
            AnchorPane mfrEditView=(AnchorPane)loader.load();
            DrugMfrEditViewController drugMfrEditController=loader.getController();
            drugMfrEditController.setType(type);
            drugMfrEditController.setDrugMfr(mfr);
            
            Dialog dialog=new Dialog();
            dialog.setTitle(title);
            
            String okBtnText=null;
            switch(type){
                case VIEW:
                    okBtnText="确定";
                    break;
                case ADD:
                    okBtnText="添加";
                    break;
                case EDIT:
                    okBtnText="修改";
                    break;
            }
            
            ButtonType okBtn=new ButtonType(okBtnText);
            dialog.getDialogPane().getButtonTypes().addAll(okBtn,ButtonType.CANCEL);
            dialog.getDialogPane().setContent(mfrEditView);
            
            if (!type.equals(VIEW)){
                dialog.setResultConverter(btn->{
                    if (btn==okBtn){
                        return drugMfrEditController.getResult();
                    }
                    else{
                        return null;
                    }
                });
            }
            
            Optional<Manufacturer> result=dialog.showAndWait();
            if (result!=null && result.get()!=null){
                String mathodName=null;
                if (type.equals(ADD)){
                    mathodName=IPaddress.MATHOD_ADD_DRUG_MFR;
                }
                if (type.equals(EDIT)){
                    mathodName=IPaddress.MATHOD_UPDATE_DRUG_MFR;
                }
                
                JSONObject jsonObj=JSONObject.fromObject(result.get());
                String msg="添加/修改药品通用名发生异常！";
                try {
                    String jsonstr=HttpMethod.sendPOSTString(mathodName, jsonObj.toString(), "UTF-8");
                    if (jsonstr!=null){
                        this.queryDrugMfrData();
                    }
                    else{
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText(msg);
                        alert.show();
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(SysDictMgtOverviewController.class.getName()).log(Level.SEVERE,msg, ex);
                }
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(SysDictMgtOverviewController.class.getName()).log(Level.INFO, "通用名编辑页面DrugGenericEditView.fxml显示异常！", ex);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.genericCName_column.setCellValueFactory(new PropertyValueFactory("genericCName"));
        this.genericEName_column.setCellValueFactory(new PropertyValueFactory("genericEName"));
        this.genericOpt_column.setCellFactory(col->{
            TableCell<DrugGeneric,HBox> cell=new TableCell<DrugGeneric,HBox>(){
                @Override
                protected void updateItem(HBox item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    
                    if (!empty){
                        HBox hbox=new HBox();
                        hbox.setAlignment(Pos.CENTER);
                        
                        Button editBtn=new Button("修改");
                        Button deleteBtn=new Button("删除");
                        hbox.getChildren().addAll(editBtn,deleteBtn);
                        
                        DrugGeneric generic=(DrugGeneric)this.getTableRow().getItem();
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        
                        editBtn.setOnAction(e->{
                            popupDrugGenericEditView("修改通用名", EDIT, generic);
                        });
                        
                        deleteBtn.setOnAction(e->{
                            String jsonstr=HttpMethod.getGETString(IPaddress.MATHOD_DELETE_DRUG_GENERIC+"?id="+generic.getId());
                            if (jsonstr!=null){
                                queryDrugGenericData();
                            }
                            else{
                                alert.setContentText("删除通用名异常！");
                                alert.show();
                            }
                        });
                        
                        this.setGraphic(hbox);
                    }
                }
            };
            
            return cell;
        });
        
        
        this.mfrName_field.addEventHandler(ActionEvent.ACTION, e->{
            this.queryDrugMfrData();
        });
        this.country_cbox.addEventHandler(ActionEvent.ACTION, e->this.queryDrugMfrData());
        
        this.mfrCode_column.setCellValueFactory(new PropertyValueFactory("code"));
        this.mfrName_column.setCellValueFactory(new PropertyValueFactory("name"));
        this.mfrRange_column.setCellValueFactory(new PropertyValueFactory("range"));
        this.mfrSocialCreditCode_column.setCellValueFactory(new PropertyValueFactory("socialCreditCode"));
        this.mfrAddress_column.setCellValueFactory(new PropertyValueFactory("address"));
        this.mfrCountryCode_column.setCellValueFactory(null);
        this.mfrRegion_column.setCellValueFactory(null);
        this.mfrOpt_column.setCellFactory(col->{
            TableCell<Manufacturer,HBox> cell=new TableCell<Manufacturer,HBox>(){
                @Override
                protected void updateItem(HBox item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    
                    if (!empty){
                        HBox hbox=new HBox();
                        hbox.setAlignment(Pos.CENTER);
                        
                        Button editBtn=new Button("修改");
                        Button deleteBtn=new Button("删除");
                        hbox.getChildren().addAll(editBtn,deleteBtn);
                        
                        Manufacturer mfr=(Manufacturer)this.getTableRow().getItem();
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        
                        editBtn.setOnAction(e->{
                            popupDrugMfrEditView("修改生产厂家", EDIT, mfr);
                        });
                        
                        deleteBtn.setOnAction(e->{
                            String jsonstr=HttpMethod.getGETString(IPaddress.MATHOD_DELETE_DRUG_MFR+"?id="+mfr.getId());
                            if (jsonstr!=null){
                                queryDrugMfrData();
                            }
                            else{
                                alert.setContentText("生产厂家删除异常！");
                                alert.show();
                            }
                        });
                        
                        this.setGraphic(hbox);
                    }
                }
            };
            
            return cell;
        });
        
    }    
    
    public void setDrugManageApp(DrugManageApp app){
        this.drugManageApp=app;
    }
    
    public void setDrugGenericData(ObservableList<DrugGeneric> list){
        this.drugGenericData=list;
        this.genericTableView.setItems(this.drugGenericData);
    }
    
    public void setDrugMfrData(ObservableList<Manufacturer> list){
        this.drugMfrData=list;
        this.mfrTableView.setItems(this.drugMfrData);
    }
}
