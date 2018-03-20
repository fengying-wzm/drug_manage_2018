/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.drugdict;

import com.drug.view.task.*;
import com.drug.DrugManageApp;
import com.drug.model.DrugCategory;
import com.drug.model.DrugDict;
import com.drug.model.DrugDosageUnit;
import com.drug.model.DrugFormulation;
import com.drug.model.DrugPkgUnit;
import com.drug.model.DrugUnit;
import com.drug.model.Task;
import com.drug.util.HttpMethod;
import com.drug.util.IPaddress;
import com.drug.util.StringListCell;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class DrugDictMgtOverviewController implements Initializable {

    public static final String MODIFY="modify";
    public static final String CHECK="check";
    
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab taskTab;
    @FXML
    private Tab drugDicTab;
    @FXML
    private ComboBox<String> taskType_cbox;
    @FXML
    private ComboBox<String> taskStatus_cbox;
    @FXML
    private CheckBox taskFilter_clearChkBox;
    @FXML
    private Button queryTaskBtn;
    
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn<Task,LocalDate> assignedOnColumn;
    @FXML
    private TableColumn<Task,String> typeColumn;
    @FXML
    private TableColumn<Task,Integer> countColumn;
    @FXML
    private TableColumn<Task,String> assignedToColumn;
    @FXML
    private TableColumn<Task,String> statusColumn;
    @FXML
    private TableColumn<Task,Integer> processedCountColumn;
    @FXML
    private TableColumn<Task,LocalDate> startOnColumn;
    @FXML
    private TableColumn<Task,LocalDate> completedOnColumn;
    @FXML
    private TableColumn<Task,HBox> taskOptColumn;
    
    /*****************字典数据tab*****************/
    @FXML
    private ComboBox<String> drugStatus_cbox;
    @FXML
    private ComboBox<DrugCategory> drugCategory_cbox;
    @FXML
    private ComboBox<Map> mfr_cbox;
//    @FXML
//    private ComboBox<Map> atc_cbox;
    @FXML
    private TextField drugNameField;
    @FXML
    private CheckBox drugFilter_clearChkBox;
    @FXML
    private Button queryDrugBtn;
    @FXML
    private TableView<DrugDict> drugDictTableView;
    
    @FXML
    private TableColumn<DrugDict,String> appvNumberColumn;
    @FXML
    private TableColumn<DrugDict,String> genericCNameColumn;
    @FXML
    private TableColumn<DrugDict,String> genericENameColumn;
    @FXML
    private TableColumn<DrugDict,String> mdseCNameColumn;
    @FXML
    private TableColumn<DrugDict,String> mdseENameColumn;
    @FXML
    private TableColumn<DrugDict,String> drugCategoryColumn;
    @FXML
    private TableColumn<DrugDict,String> formulationColumn;
    @FXML
    private TableColumn<DrugDict,String> drugUnitColumn;
    @FXML
    private TableColumn<DrugDict,Double> mainDosageColumn;
    @FXML
    private TableColumn<DrugDict,String> mainDosageUnitColumn;
    @FXML
    private TableColumn<DrugDict,Double> sndDosageColumn;
    @FXML
    private TableColumn<DrugDict,String> sndDosageUnitColumn;
    @FXML
    private TableColumn<DrugDict,String> specColumn;
    @FXML
    private TableColumn<DrugDict,String> specNoteColumn;
    @FXML
    private TableColumn<DrugDict,String> mainPkgUnitColumn;
    @FXML
    private TableColumn<DrugDict,Integer> mainPkgSpecColumn;
    @FXML
    private TableColumn<DrugDict,String> sndPkgUnitColumn;
    @FXML
    private TableColumn<DrugDict,Integer> sndPkgSpecColumn;
    @FXML
    private TableColumn<DrugDict,String> trdPkgUnitColumn;
    @FXML
    private TableColumn<DrugDict,String> mfrColumn;
    @FXML
    private TableColumn<DrugDict,LocalDate> appvDateColumn;
    @FXML
    private TableColumn<DrugDict,String> orgAppvNumberColumn;
    @FXML
    private TableColumn<DrugDict,String> stdCodeColumn;
    @FXML
    private TableColumn<DrugDict,String> stdCodeNoteColumn;
    @FXML
    private TableColumn<DrugDict,String> atcCodeColumn;
    @FXML
    private TableColumn<DrugDict,String> ddStatusColumn;
    @FXML
    private TableColumn<DrugDict,Long> ddTaskIdColumn;
    @FXML
    private TableColumn<DrugDict,String> optColumn;
    
    private ObservableList<Map> userList;
    private ObservableList<Task> taskData;
    
    private ObservableList<String> drugStatusList;
    private ObservableList<DrugCategory> drugCategoryList;
    private ObservableList<Map> mfrList;
    
    private ObservableList<DrugDict> drugDictData;
   
    private DrugManageApp drugManageApp;
    
    private DateTimeFormatter dateTimeFormatter;
    

    @FXML
    private void enableClearTaskFilter(){
        this.taskFilter_clearChkBox.setDisable(false);
        this.taskFilter_clearChkBox.setSelected(false);
    }
    @FXML
    private void clearTaskFilter(){
        this.taskType_cbox.getSelectionModel().clearSelection();
        this.taskStatus_cbox.getSelectionModel().clearSelection();
        
        this.taskFilter_clearChkBox.setDisable(true);
    }
    @FXML
    private ObservableList<Task> queryTasks(){
        try {
            String taskType=this.taskType_cbox.getSelectionModel().getSelectedItem();
            String taskStatus=this.taskStatus_cbox.getSelectionModel().getSelectedItem();
            String param="?userId="+this.drugManageApp.getCurrentUser().getId();
            if (taskType!=null){
                param+="&type="+URLEncoder.encode(taskType,"UTF-8");
            }
            if (taskStatus!=null){
                param+="&status="+URLEncoder.encode(taskStatus,"UTF-8");
            }

            String jsonStr=HttpMethod.getGETString(IPaddress.MATHOD_GET_TASKS_BY_TYPE_AND_STATUS_AND_ASSIGNEDTO+param);
            JSONArray jsonArr=JSONArray.fromObject(jsonStr);
            List<Task> result=(List<Task>)JSONArray.toCollection(jsonArr, Task.class);
            this.taskData=FXCollections.observableArrayList(result);
            this.taskTableView.setItems(this.taskData);
            
            return this.taskData;
        } catch (Exception e) {
            Logger.getLogger(DrugDictMgtOverviewController.class.getName()).log(Level.INFO, "URLEncoder.encode()出错！", e);
        }
        
        return null;
    }
   @FXML
   private ObservableList<DrugDict> queryDrugDicts(){
       try {
            List<Long> taskIds=this.getTaskIdsByStatusAndAssignedTo(Task.TaskStatus.IN_PROCESS, this.drugManageApp.getCurrentUser().getId());
            if (taskIds==null){
                this.drugDictData=null;
                this.drugDictTableView.setItems(null);
                return null;
            }
       
            long mfrId=0;
            if (this.mfr_cbox.getSelectionModel().getSelectedItem()!=null){
                mfrId=Long.parseLong(this.mfr_cbox.getSelectionModel().getSelectedItem().get("id").toString());
            }
            
            String mathodName=IPaddress.MATHOD_GET_STD_DICT_DATA+"?mfrId="+mfrId;
             
            String drugStatus=this.drugStatus_cbox.getSelectionModel().getSelectedItem();
            if (drugStatus!=null){
                mathodName+="&status="+URLEncoder.encode(drugStatus,"UTF-8");
            }
            
            if (this.drugCategory_cbox.getSelectionModel().getSelectedItem()!=null){
                String drugCategory=this.drugCategory_cbox.getSelectionModel().getSelectedItem().getName();
                mathodName+="&category="+URLEncoder.encode(drugCategory,"UTF-8");
            }
            
            String drugName=this.drugNameField.getText();
            if (drugName!=null && !drugName.isEmpty() && !drugName.trim().isEmpty()){
                mathodName+="&name="+URLEncoder.encode(drugName, "UTF-8");
            }
            
            JSONArray jsonArr=JSONArray.fromObject(taskIds);
            mathodName+="&taskIds="+jsonArr.toString();
            
            String jsonstr=HttpMethod.getGETString(mathodName);
            JSONObject jsonObj=JSONObject.fromObject(jsonstr);
            Map<String,Object> page=(Map<String,Object>)JSONObject.toBean(jsonObj, Map.class);
            jsonArr=JSONArray.fromObject(page.get("content"));
            List<DrugDict> result=(List<DrugDict>)JSONArray.toCollection(jsonArr, DrugDict.class);
            this.drugDictData=FXCollections.observableArrayList(result);
            this.drugDictTableView.setItems(this.drugDictData);
            
            return this.drugDictData;
            
       } catch (Exception e) {
           Logger.getLogger(DrugDictMgtOverviewController.class.getName()).log(Level.INFO,null,e);
       }
       
       return null;
   }
    @FXML
    private void enableClearDrugFilter(){
        this.drugFilter_clearChkBox.setDisable(false);
        this.drugFilter_clearChkBox.setSelected(false);
    }
    @FXML
    private void clearDrugFilter(){
        this.drugStatus_cbox.getSelectionModel().clearSelection();
        this.drugCategory_cbox.getSelectionModel().clearSelection();
        this.drugNameField.setText(null);
        this.mfr_cbox.getSelectionModel().clearSelection();
        
        this.drugFilter_clearChkBox.setDisable(true);
    }
    
    private List<Long> getTaskIdsByStatusAndAssignedTo(String status,int userId){
        try {
            String mathodName=IPaddress.MATHOD_GET_TASK_IDS_BY_STATUS_AND_ASSIGNED_TO+"?id="+userId;
            if (status!=null){
                mathodName+="&status="+URLEncoder.encode(status,"UTF-8");
            }
            String jsonstr=HttpMethod.getGETString(mathodName);
            JSONArray jsonArr=JSONArray.fromObject(jsonstr);
            List<Long> taskIds=(List<Long>)JSONArray.toCollection(jsonArr, Long.class);

            return taskIds;
        } catch (Exception e) {
            Logger.getLogger(DrugDictMgtOverviewController.class.getName());
        }
        
        return null;
    }
    
    private ObservableList<DrugFormulation> drugFormulationList;
    private ObservableList<DrugUnit> drugUnitList;
    private ObservableList<DrugDosageUnit> drugDosageUnitList;
    private ObservableList<DrugPkgUnit> drugPkgUnitList;
    private void popupDrugDictInfoDialog(String title,String type,DrugDict drugDict) throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(this.getClass().getResource("DrugDictEditView.fxml"));
        VBox drugDictEditView=loader.load();
        DrugDictEditViewController drugDictEditViewController=loader.getController();
        
        String jsonstr=HttpMethod.getGETString(IPaddress.MATHOD_GET_DRUG_FORMULATION_LIST);
        JSONArray jsonArray=JSONArray.fromObject(jsonstr);
        List<DrugFormulation> dfresult=(List<DrugFormulation>)JSONArray.toCollection(jsonArray, DrugFormulation.class);
        this.drugFormulationList=FXCollections.observableList(dfresult);
        
        jsonstr=HttpMethod.getGETString(IPaddress.MATHOD_GET_DRUG_UNIT_LIST);
        jsonArray=JSONArray.fromObject(jsonstr);
        List<DrugUnit> duresult=(List<DrugUnit>)JSONArray.toCollection(jsonArray,DrugUnit.class);
        this.drugUnitList=FXCollections.observableList(duresult);
        
        jsonstr=HttpMethod.getGETString(IPaddress.MATHOD_GET_DRUG_DOSAGE_UNIT_LIST);
        jsonArray=JSONArray.fromObject(jsonstr);
        List<DrugDosageUnit> dduresult=(List<DrugDosageUnit>)JSONArray.toCollection(jsonArray,DrugDosageUnit.class);
        this.drugDosageUnitList=FXCollections.observableList(dduresult);
        
        jsonstr=HttpMethod.getGETString(IPaddress.MATHOD_GET_DRUG_PKG_UNIT_LIST);
        jsonArray=JSONArray.fromObject(jsonstr);
        List<DrugPkgUnit> dpuresult=(List<DrugPkgUnit>)JSONArray.toCollection(jsonArray,DrugPkgUnit.class);
        this.drugPkgUnitList=FXCollections.observableList(dpuresult);
        
        drugDictEditViewController.setDrugCategoryList(this.drugCategoryList);
//        drugDictEditViewController.setDrugGenericList(this.drug);
        drugDictEditViewController.setDrugFormulationList(this.drugFormulationList);
        drugDictEditViewController.setDrugMfrList(this.mfrList);
        drugDictEditViewController.setDrugUnitList(this.drugUnitList);
        drugDictEditViewController.setDrugDosageUnitList(this.drugDosageUnitList);
        drugDictEditViewController.setDrugPkgUnitList(this.drugPkgUnitList);
        
        drugDictEditViewController.setDrugDict(drugDict);
        
        Dialog drugDictEditDialog=new Dialog();
        drugDictEditDialog.setTitle(title);
        
        String type_cn=null;
        switch(type){
            case MODIFY:
                type_cn="修订完成";
                break;
            case CHECK:
                type_cn="复核完成";
                break;
        }
        ButtonType okBtn=new ButtonType(type_cn, ButtonBar.ButtonData.OK_DONE);
        drugDictEditDialog.getDialogPane().getButtonTypes().addAll(okBtn,ButtonType.CANCEL);
        drugDictEditDialog.getDialogPane().setContent(drugDictEditView);
        drugDictEditDialog.setResultConverter(btnType->{
            if (btnType==okBtn){
                DrugDict adrug=new DrugDict();
//                atask.setType(durgDictEditViewController.type_cbox.getValue());
//                atask.setCount(Integer.parseInt(taskEditViewController.countField.getText()));
//                atask.setAssignedToId((int)taskEditViewController.assignedTo_cbox.getValue().get("id"));
                return adrug;
            }

            return null;
        });
        
        Optional<DrugDict> result=drugDictEditDialog.showAndWait();
        if (result!=null && result.get()!=null){
//            drugDict.set(result.get().getType());
//            drugDict.setAssignedToId(result.get().getAssignedToId());
            this.drugDictTableView.refresh();
        } 
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        this.taskType_cbox.setItems(Task.TaskType.getTaskType());
        this.taskStatus_cbox.setItems(Task.TaskStatus.getTaskStatus());
        
        this.assignedOnColumn.setCellValueFactory(new PropertyValueFactory("assignedOn"));
        this.typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
        this.countColumn.setCellValueFactory(new PropertyValueFactory("count"));
        this.statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        this.processedCountColumn.setCellValueFactory(new PropertyValueFactory("processedCount"));
        this.startOnColumn.setCellValueFactory(new PropertyValueFactory("startedOn"));
        this.completedOnColumn.setCellValueFactory(new PropertyValueFactory("completedOn"));
        
        this.taskOptColumn.setCellFactory(col->{
            TableCell<Task,HBox> tableCell=new TableCell<Task,HBox>(){
                @Override
                protected void updateItem(HBox item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                     
                    if (!empty){
                        HBox hbox=new HBox();
                        hbox.setAlignment(Pos.CENTER);
                        Button startTaskBtn=new Button();
                        hbox.getChildren().addAll(startTaskBtn);
                        
                        Task task=(Task)this.getTableRow().getItem();
                        if (task!=null){
                            switch(task.getStatus()){
                                case Task.TaskStatus.HAS_ASSIGNED:
                                    startTaskBtn.setText("开始");
                                    break;
                                case Task.TaskStatus.IN_PROCESS:
                                    startTaskBtn.setText("完成");
                                    break;
                                case Task.TaskStatus.COMPLETED:
                                    startTaskBtn.setText("完成");
                                    startTaskBtn.setDisable(true);
                                    break;
                            }
                        }
                        
                        startTaskBtn.setOnAction(e->{
                            try {
                                JSONObject jsonObj=JSONObject.fromObject(task);
                                String methodName=startTaskBtn.getText().equals("开始")?IPaddress.MATHOD_START_TASK:IPaddress.MATHOD_COMPLETE_TASK;
                                String jsonStr=HttpMethod.sendPOSTString(methodName+"?id="+task.getId(),jsonObj.toString(),"UTF-8");
                                if (jsonStr!=null){
                                    jsonObj=JSONObject.fromObject(jsonStr);
                                    Task updatedTask=(Task)JSONObject.toBean(jsonObj, Task.class);
                                    task.setStartedOn(updatedTask.getStartedOn());
                                    task.setCompletedOn(updatedTask.getCompletedOn());
                                    task.setStatus(updatedTask.getStatus());
                                    
                                    if (startTaskBtn.getText().equals("开始")){
                                        startTaskBtn.setText("完成");
                                    }
                                    else{
                                        startTaskBtn.setDisable(true);
                                    }
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(DrugDictMgtOverviewController.class.getName()).log(Level.INFO, "开始任务失败！", ex);
                            }
                        });
                        
                        this.setGraphic(hbox);
                    }
                    
                }
            };
            
            return tableCell;
        });
        
        /***************字典数据tab***************/
        this.drugStatus_cbox.setItems(DrugDict.DrugStatus.getDrugStatusList());
        
        this.drugCategory_cbox.setButtonCell(new StringListCell());
        this.drugCategory_cbox.setCellFactory(view->new StringListCell());
        
        this.mfr_cbox.setButtonCell(new StringListCell());
        this.mfr_cbox.setCellFactory(view->new StringListCell());
//        this.mfr_cbox.setEditable(true);

        this.drugDictTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.drugDictTableView.setRowFactory(view->{
//            TableRow<DrugDict> row=new TableRowControl<DrugDict>();
            TableRow<DrugDict> row=new TableRow<DrugDict>();
            row.setOnMouseClicked(e->{
                if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount()==2 && row.getIndex()<row.getTableView().getItems().size()){
                    try {
                        String type=MODIFY;
                        String title="标准字典修订";
                        if (row.getItem().getStatus()==DrugDict.DrugStatus.ASSIGNED_CHECK){
                            type=CHECK;
                            title="标准字典复核";
                        }
                        
                        this.popupDrugDictInfoDialog(title, type, row.getItem());
                        
                    } catch (Exception ex) {
                        Logger.getLogger(DrugDictMgtOverviewController.class.getName()).log(Level.INFO, "DrugDictEditView弹出窗口报错！", ex);
                    }
                }
            });
            return row;
        });
        
        CheckBox selectAll_checkBox=new CheckBox("全选");
        selectAll_checkBox.setOnAction(e->{
            selectAll_checkBox.setAllowIndeterminate(false);
            selectAll_checkBox.setIndeterminate(false);
            if (selectAll_checkBox.isSelected()){
                this.drugDictTableView.getSelectionModel().selectAll();
            }
            else{
                this.drugDictTableView.getSelectionModel().clearSelection();
            }
        });
        
        this.appvNumberColumn.setCellValueFactory(new PropertyValueFactory("appvNumber"));
        this.genericCNameColumn.setCellValueFactory(new PropertyValueFactory("genericCName"));
        this.genericENameColumn.setCellValueFactory(new PropertyValueFactory("genericEName"));
        this.mdseCNameColumn.setCellValueFactory(new PropertyValueFactory("mdseCName"));
        this.mdseENameColumn.setCellValueFactory(new PropertyValueFactory("mdseEName"));
        this.drugCategoryColumn.setCellValueFactory(new PropertyValueFactory("category"));
        this.formulationColumn.setCellValueFactory(new PropertyValueFactory("formulation"));
        this.drugUnitColumn.setCellValueFactory(new PropertyValueFactory("drugUnit"));
        this.mainDosageColumn.setCellValueFactory(new PropertyValueFactory("mainDosage"));
        this.mainDosageUnitColumn.setCellValueFactory(new PropertyValueFactory("mainDosageUnit"));
        this.sndDosageColumn.setCellValueFactory(new PropertyValueFactory("sndDosage"));
        this.sndDosageUnitColumn.setCellValueFactory(new PropertyValueFactory("sndDosageUnit"));
        this.specColumn.setCellValueFactory(new PropertyValueFactory("spec"));
        this.specNoteColumn.setCellValueFactory(new PropertyValueFactory("specNote"));
        this.mainPkgUnitColumn.setCellValueFactory(new PropertyValueFactory("mainPkgUnit"));
        this.mainPkgSpecColumn.setCellValueFactory(new PropertyValueFactory("mainPkgSpec"));
        this.sndPkgUnitColumn.setCellValueFactory(new PropertyValueFactory("sndPkgUnit"));
        this.sndPkgSpecColumn.setCellValueFactory(new PropertyValueFactory("sndPkgSpec"));
        this.trdPkgUnitColumn.setCellValueFactory(new PropertyValueFactory("trdPkgUnit"));
        this.mfrColumn.setCellValueFactory(new PropertyValueFactory("mfrPkgUnit"));
        this.appvDateColumn.setCellValueFactory(new PropertyValueFactory("appvDate"));
        this.orgAppvNumberColumn.setCellValueFactory(new PropertyValueFactory("orgAppvNumber"));
        this.stdCodeColumn.setCellValueFactory(new PropertyValueFactory("stdCode"));
        this.stdCodeNoteColumn.setCellValueFactory(new PropertyValueFactory("stdCodeNote"));
        this.atcCodeColumn.setCellValueFactory(new PropertyValueFactory("actCode"));
        this.ddStatusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        this.ddTaskIdColumn.setCellValueFactory(new PropertyValueFactory("taskId"));
        
    }    
    
    public void setDrugManageApp(DrugManageApp drugManageApp) {
        this.drugManageApp = drugManageApp;
    }
    
     public void setUserList(ObservableList<Map> userList){
        this.userList=userList;
        
        this.assignedToColumn.setCellValueFactory(celldata->{
            int userId=celldata.getValue().getAssignedToId();
            String userName=null;
            for (Map user : this.userList){
                if ((int)user.get("id")==userId){
                    userName=(String)user.get("name");
                }
            }
            return new SimpleStringProperty(userName);
        });
    }
     
    public void setDrugCategoryList(ObservableList<DrugCategory> drugCategoryList){
        this.drugCategoryList=drugCategoryList;
        this.drugCategory_cbox.setItems(drugCategoryList);
    }
    public void setMfrList(ObservableList<Map> mfrList){
        this.mfrList=mfrList;
        this.mfr_cbox.setItems(this.mfrList);
    }
    
    public void setTaskData(ObservableList<Task> taskData){
        this.taskData=taskData;
        this.taskTableView.setItems(this.taskData);
    }
    
    public void setDrugDictData(ObservableList<DrugDict> drugDictData){
        this.drugDictData=drugDictData;
        this.drugDictTableView.setItems(this.drugDictData);
    }
    
}

//class TableRowControl<T> extends TableRow<T>{
//    public TableRowControl(){
//        super();
//        this.setOnMouseClicked(e->{
//            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount()==2 && TableRowControl.this.getIndex()<this.getTableView().getItems().size()){
//               
//            }
//        });
//    }
//}