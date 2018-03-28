/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.drugdict;

import com.drug.model.DrugCategory;
import com.drug.model.DrugDict;
import com.drug.model.DrugDosageUnit;
import com.drug.model.DrugFormulation;
import com.drug.model.DrugGeneric;
import com.drug.model.DrugPkgUnit;
import com.drug.model.DrugUnit;
import com.drug.util.StringListCell;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class DrugDictEditViewController implements Initializable {

    @FXML
    private TextField appvNumber_field;
    @FXML
    private ComboBox<DrugCategory> category_cbox;
    @FXML
    private ComboBox<DrugGeneric> genericCName_cbox;
    @FXML
    private TextField genericEName_field;
    @FXML
    private TextField mdseCName_field;
    @FXML
    private TextField mdseEName_field;
    @FXML
    private ComboBox<DrugFormulation> formulation_cbox;
    @FXML
    private ComboBox atcCode_cbox;
    @FXML
    private ComboBox<Map> mfr_cbox;
    @FXML
    private ComboBox<DrugUnit> drugUnit_cbox;
    @FXML
    private TextField appvDate_field;
    @FXML
    private TextField orgAppvNumber_field;
    @FXML
    private TextField stdCode_field;
    @FXML
    private TextArea stdCodeNote_tarea;
    
    
    @FXML
    private TextField spec_field;
    @FXML
    private TextArea specNote_tarea;
    @FXML
    private TextField mainDosage_field;
    @FXML
    private ComboBox<DrugDosageUnit> mainDosageUnit_cbox;
    @FXML
    private TextField sndDosage_field;
    @FXML
    private ComboBox<DrugDosageUnit> sndDosageUnit_cbox;
    @FXML
    private ComboBox<DrugPkgUnit> mainPkgUnit_cbox;
    @FXML
    private TextField mainPkgSpec_field;
    @FXML
    private ComboBox<DrugPkgUnit> sndPkgUnit_cbox;
    @FXML
    private TextField sndPkgSpec_field;
    @FXML
    private ComboBox<DrugPkgUnit> trdPkgUnit_cbox;
  
    private ObservableList<DrugCategory> drugCategoryList;
    private ObservableList<DrugGeneric> drugGenericList;
    private ObservableList<DrugFormulation> drugFormulationList;
    private ObservableList<Map> drugMfrList;
    private ObservableList<DrugUnit> drugUnitList;
    private ObservableList<DrugDosageUnit> drugDosageUnitList;
    private ObservableList<DrugPkgUnit> drugPkgUnitList;
    
    private DrugDict currDrugDict;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.category_cbox.setButtonCell(new StringListCell());
        this.category_cbox.setCellFactory(view->new StringListCell());
        
        this.formulation_cbox.setButtonCell(new StringListCell());
        this.formulation_cbox.setCellFactory(view->new StringListCell());
        
        this.mfr_cbox.setButtonCell(new StringListCell());
        this.mfr_cbox.setCellFactory(view->new StringListCell());
        
        this.drugUnit_cbox.setButtonCell(new StringListCell());
        this.drugUnit_cbox.setCellFactory(view->new StringListCell());
        
        this.mainDosageUnit_cbox.setButtonCell(new StringListCell());
        this.mainDosageUnit_cbox.setCellFactory(view->new StringListCell());
        
        this.sndDosageUnit_cbox.setButtonCell(new StringListCell());
        this.sndDosageUnit_cbox.setCellFactory(view->new StringListCell());
        
        this.mainPkgUnit_cbox.setButtonCell(new StringListCell());
        this.mainPkgUnit_cbox.setCellFactory(view->new StringListCell());
        
        this.sndPkgUnit_cbox.setButtonCell(new StringListCell());
        this.sndPkgUnit_cbox.setCellFactory(view->new StringListCell());
        
        this.trdPkgUnit_cbox.setButtonCell(new StringListCell());
        this.trdPkgUnit_cbox.setCellFactory(view->new StringListCell());
        
        
//        String jsonstr=HttpMethod.getGETString(IPaddress.MATHOD_GET_DRUG_CATEGORY_LIST);
//        JSONArray jsonArr=JSONArray.fromObject(jsonstr);
//        this.drugCategoryList=
        
    }    
    
    public void setDrugCategoryList(ObservableList<DrugCategory> list){
        this.drugCategoryList=list;
        this.category_cbox.setItems(this.drugCategoryList);
        this.selectCategory();
    }
    
    public void setDrugGenericList(ObservableList<DrugGeneric> list){
        this.drugGenericList=list;
        this.genericCName_cbox.setItems(this.drugGenericList);
        this.selectGenericName();
    }
    
    public void setDrugFormulationList(ObservableList<DrugFormulation> list){
        this.drugFormulationList=list;
        this.formulation_cbox.setItems(this.drugFormulationList);
        this.selectFormulation();
    }
    
    public void setDrugMfrList(ObservableList<Map> list){
        this.drugMfrList=list;
        this.mfr_cbox.setItems(this.drugMfrList);
        this.selectDrugMfr();
    }
    public void setDrugUnitList(ObservableList<DrugUnit> list){
        this.drugUnitList=list;
        this.drugUnit_cbox.setItems(this.drugUnitList);
        
        this.selectDrugUnit();
    }
    public void setDrugDosageUnitList(ObservableList<DrugDosageUnit> list){
        this.drugDosageUnitList=list;
        this.mainDosageUnit_cbox.setItems(this.drugDosageUnitList);
        this.sndDosageUnit_cbox.setItems(this.drugDosageUnitList);
        
        this.selectDrugDosageUnit();
    }
    public void setDrugPkgUnitList(ObservableList<DrugPkgUnit> list){
        this.drugPkgUnitList=list;
        this.mainPkgUnit_cbox.setItems(this.drugPkgUnitList);
        this.sndPkgUnit_cbox.setItems(this.drugPkgUnitList);
        this.trdPkgUnit_cbox.setItems(this.drugPkgUnitList);
        
        this.selectDrugPkgUnit();
    }
    
    public void setDrugDict(DrugDict drugDict){
        this.currDrugDict=drugDict;
        
        this.appvNumber_field.setText(this.currDrugDict.getAppvNumber());
        this.selectCategory();
        this.mdseCName_field.setText(this.currDrugDict.getMdseCName());
        this.mdseEName_field.setText(this.currDrugDict.getMdseEName());
        this.selectFormulation();
//        this.atcCode_cbox
        this.selectDrugMfr();
        this.selectDrugUnit();
        this.appvDate_field.setText(this.currDrugDict.getAppvDate()!=null?this.currDrugDict.getAppvDate().toString():null);
        this.orgAppvNumber_field.setText(this.currDrugDict.getOrgAppvNumber());
        this.stdCode_field.setText(this.currDrugDict.getStdCode());
        this.stdCodeNote_tarea.setText(this.currDrugDict.getStdCodeNote());
        
        this.spec_field.setText(this.currDrugDict.getSpec());
        this.specNote_tarea.setText(this.currDrugDict.getSpecNote());
        this.mainDosage_field.setText(this.currDrugDict.getMainDosage()!=null?this.currDrugDict.getMainDosage().toString():null);
        this.sndDosage_field.setText(this.currDrugDict.getSndDosage()!=null?this.currDrugDict.getSndDosage().toString():null);
        this.selectDrugDosageUnit();
        this.selectDrugPkgUnit();
        this.mainPkgSpec_field.setText(this.currDrugDict.getMainPkgSpec()!=null?this.currDrugDict.getMainPkgSpec().toString():null);
        this.sndPkgSpec_field.setText(this.currDrugDict.getSndPkgSpec()!=null?this.currDrugDict.getSndPkgSpec().toString():null);
    }
    
    public DrugDict getResult(){
        DrugDict drug=new DrugDict();
        drug.setId(this.currDrugDict.getId());
        
        drug.setAppvNumber(this.appvNumber_field.getText());
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        if (this.category_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setCategory(this.category_cbox.getSelectionModel().getSelectedItem().getName());
        }
        else{
//            alert.setContentText("药品类型不能为空，请选择！");
//            alert.show();
//            return null;
        }
        
        if (this.genericCName_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setGenericCName(this.genericCName_cbox.getSelectionModel().getSelectedItem().getGenericCName());
            drug.setGenericEName(this.genericCName_cbox.getSelectionModel().getSelectedItem().getGenericEName());
        }
        else{
//            alert.setContentText("药品名不能为空，请选择！");
//            alert.show();
//            return null;
        }
        
        drug.setMdseCName(this.mdseCName_field.getText());
        drug.setMdseEName(this.mdseEName_field.getText());
        
        if (this.formulation_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setFormulation(this.formulation_cbox.getSelectionModel().getSelectedItem().getName());
        }
        else{
//            alert.setContentText("剂型不能为空，请选择！");
//            alert.show();
//            return null;
        }
        
        drug.setAtcId(this.currDrugDict.getAtcId());
        if (this.atcCode_cbox.getSelectionModel().getSelectedItem()!=null){
//            drug.setAtcId(this.currDrugDict.getAtcId());
        }
        else {
//            alert.setContentText("ATC编码不能为空，请选择！");
//            alert.show();
//            return null;
        }
        
        if (this.mfr_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setMfrId(Long.valueOf(this.mfr_cbox.getSelectionModel().getSelectedItem().get("id").toString()));
        }
        else {
//            alert.setContentText("生产厂家不能为空，请选择！");
//            alert.show();
//            return null;
        }
        
        if (this.drugUnit_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setDrugUnit(this.drugUnit_cbox.getSelectionModel().getSelectedItem().getName());
        }
        
        String appvDateStr=this.appvDate_field.getText();
        if (appvDateStr!=null && !appvDateStr.isEmpty() && !appvDateStr.trim().isEmpty()){
            drug.setAppvDate(LocalDate.parse(appvDateStr.subSequence(0,appvDateStr.length())));
        }
        
        drug.setOrgAppvNumber(this.orgAppvNumber_field.getText());
        
        drug.setStdCode(this.stdCode_field.getText());
        drug.setStdCodeNote(this.stdCodeNote_tarea.getText());
        
        drug.setSpec(this.spec_field.getText());
        drug.setSpecNote(this.specNote_tarea.getText());
        
        String str=this.mainDosage_field.getText();
        if (str!=null && !str.isEmpty() && !str.trim().isEmpty()){
            drug.setMainDosage(Double.valueOf(str));
        }
        if (this.mainDosageUnit_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setMainDosageUnit(this.mainDosageUnit_cbox.getSelectionModel().getSelectedItem().getName());
        }
        
        str=this.sndDosage_field.getText();
        if (str!=null && !str.isEmpty() && !str.trim().isEmpty()){
            drug.setSndDosage(Double.valueOf(str));
        }
        if (this.sndDosageUnit_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setSndDosageUnit(this.sndDosageUnit_cbox.getSelectionModel().getSelectedItem().getName());
        }
        
        str=this.mainPkgSpec_field.getText();
        if (str!=null && !str.isEmpty() && !str.trim().isEmpty()){
            drug.setMainPkgSpec(Integer.valueOf(str));
        }
        str=this.sndPkgSpec_field.getText();
        if (str!=null && !str.isEmpty() && !str.trim().isEmpty()){
            drug.setSndPkgSpec(Integer.valueOf(str));
        }
        
        if (this.mainPkgUnit_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setMainPkgUnit(this.mainPkgUnit_cbox.getSelectionModel().getSelectedItem().getName());
        }
        if (this.sndPkgUnit_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setSndPkgUnit(this.sndPkgUnit_cbox.getSelectionModel().getSelectedItem().getName());
        }
        if (this.trdPkgUnit_cbox.getSelectionModel().getSelectedItem()!=null){
            drug.setTrdPkgUnit(this.trdPkgUnit_cbox.getSelectionModel().getSelectedItem().getName());
        }
        
        drug.setStatus(this.currDrugDict.getStatus());
        drug.setCreatedById(this.currDrugDict.getCreatedById());
        drug.setCreatedOn(this.currDrugDict.getCreatedOn());
        drug.setSourceId(this.currDrugDict.getSourceId());
        drug.setTaskId(this.currDrugDict.getTaskId());
        
        return drug;
    }
    
    private void selectCategory(){
        if (this.currDrugDict!=null && this.drugCategoryList!=null){
            this.drugCategoryList.forEach(item->{
                if (item.getName().equals(this.currDrugDict.getCategory())){
                    this.category_cbox.getSelectionModel().select(item);
                }
            });
        }
    }
    
    private void selectGenericName(){
        if (this.currDrugDict!=null && this.drugGenericList!=null){
            this.drugGenericList.forEach(item->{
                if (item.getGenericCName().equals(this.currDrugDict.getGenericCName())){
                    this.genericCName_cbox.getSelectionModel().select(item);
                    this.genericEName_field.setText(item.getGenericEName());
                }
            });
        }
    }
    
    private void selectFormulation(){
        if (this.currDrugDict!=null && this.drugFormulationList!=null){
            this.drugFormulationList.forEach(item->{
                if (item.getName().equals(this.currDrugDict.getFormulation())){
                    this.formulation_cbox.getSelectionModel().select(item);
                }
            });
        }
    }
    
    private void selectDrugMfr(){
        if (this.currDrugDict!=null && this.drugMfrList!=null){
            this.drugMfrList.forEach(item->{
                if (Long.valueOf(item.get("id").toString())==this.currDrugDict.getMfrId()){
                    this.mfr_cbox.getSelectionModel().select(item);
                }
            });
        }
    }
    
    private void selectDrugUnit(){
        if (this.currDrugDict!=null && this.drugUnitList!=null){
            this.drugUnitList.forEach(item->{
                if (item.getName().equals(this.currDrugDict.getDrugUnit())){
                    this.drugUnit_cbox.getSelectionModel().select(item);
                }
            });
        }
    }
    
    private void selectDrugDosageUnit(){
        if (this.currDrugDict!=null && this.drugDosageUnitList!=null){
            this.drugDosageUnitList.forEach(item->{
                if (item.getName().equals(this.currDrugDict.getMainDosageUnit())){
                    this.mainDosageUnit_cbox.getSelectionModel().select(item);
                }
                
                if (item.getName().equals(this.currDrugDict.getSndDosageUnit())){
                    this.sndDosageUnit_cbox.getSelectionModel().select(item);
                }
            });
        }
    }
    
    private void selectDrugPkgUnit(){
        if (this.currDrugDict!=null && this.drugPkgUnitList!=null){
            this.drugPkgUnitList.forEach(item->{
                if (item.getName().equals(this.currDrugDict.getMainPkgUnit())){
                    this.mainPkgUnit_cbox.getSelectionModel().select(item);
                }
                
                if (item.getName().equals(this.currDrugDict.getSndPkgUnit())){
                    this.sndPkgUnit_cbox.getSelectionModel().select(item);
                }
                
                if (item.getName().equals(this.currDrugDict.getTrdPkgUnit())){
                    this.trdPkgUnit_cbox.getSelectionModel().select(item);
                }
            });
        }
    }
}
