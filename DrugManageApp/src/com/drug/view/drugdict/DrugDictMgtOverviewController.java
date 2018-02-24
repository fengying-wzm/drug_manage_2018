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
import com.drug.model.Task;
import com.drug.util.StringListCell;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class DrugDictMgtOverviewController implements Initializable {

    public static final String ADD="add";
    public static final String Edit="edit";
    public static final String VIEW="view";
    
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
    private Button queryBtn;
    
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn<Task,LocalDate> assignedOnColumn;
    @FXML
    private TableColumn<Task,String> typeColumn;
    @FXML
    private TableColumn<Task,Integer> countColumn;
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
    private ComboBox<DrugCategory> drugCategory_cbox;
    @FXML
    private ComboBox<Map> mfr_cbox;
//    @FXML
//    private ComboBox<Map> atc_cbox;
    @FXML
    private TextField drugNameField;
    
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
    private TableColumn<DrugDict,String> optColumn;
    
    private ObservableList<Map> mfrList;
    private ObservableList<DrugCategory> drugCategoryList;
    
    private ObservableList<Task> taskData;
    
    private ObservableList<DrugDict> drugDictData;
   
    private DrugManageApp drugManageApp;
    

    @FXML
    private ObservableList<Task> getTasks(){
        return null;
    }
    
    private void popupDrugDictInfoDialog(String title,String type,Task task) throws Exception{
       FXMLLoader loader=new FXMLLoader();
        loader.setLocation(this.getClass().getResource("TaskEditView.fxml"));
        GridPane userEditView=loader.load();
        TaskEditViewController taskEditViewController=loader.getController();
//        taskEditViewController.setUserList(this.userList);
        
//        if (!type.equals(ADD)){
            taskEditViewController.setTask(task);
//        }
        
        Dialog taskEditDialog=new Dialog();
        taskEditDialog.setTitle(title);
        
        String type_cn=null;
        switch(type){
            case "view":
                type_cn="确定";
                break;
            case "edit":
                type_cn="修改";
                break;
            case "add":
                type_cn="添加";
                break;
                
        }
        ButtonType okBtn=new ButtonType(type_cn, ButtonBar.ButtonData.OK_DONE);
        taskEditDialog.getDialogPane().getButtonTypes().addAll(okBtn,ButtonType.CANCEL);
        taskEditDialog.getDialogPane().setContent(userEditView);
        if (!type.equals(VIEW)){
            taskEditDialog.setResultConverter(btnType->{
                if (btnType==okBtn){
                    Task atask=new Task();
                    atask.setType(taskEditViewController.type_cbox.getValue());
                    atask.setCount(Integer.parseInt(taskEditViewController.countField.getText()));
                    atask.setAssignedToId((int)taskEditViewController.assignedTo_cbox.getValue().get("id"));
                    return atask;
                }

                return null;
            });
        }
        
        Optional<Task> result=taskEditDialog.showAndWait();
        if (result.get()!=null){
            if (type.equals(ADD)){
                //todo保存至数据库
                this.taskData.add(result.get());
            }
            
            if (type.equals(Edit)){
                //todo 保存至数据库
                task.setAssignedOn(LocalDate.now());
                task.setType(result.get().getType());
                task.setAssignedToId(result.get().getAssignedToId());
                this.taskTableView.refresh();
            }
        } 
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.taskType_cbox.setItems(Task.TaskType.getTaskType());
        this.taskStatus_cbox.setItems(Task.TaskStatus.getTaskStatus());
        
        this.assignedOnColumn.setCellValueFactory(new PropertyValueFactory("assignedOn"));
        this.typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
        this.countColumn.setCellValueFactory(new PropertyValueFactory("count"));
        
        this.statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        this.processedCountColumn.setCellValueFactory(new PropertyValueFactory("processedCount"));
        this.startOnColumn.setCellValueFactory(new PropertyValueFactory("startOn"));
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
                        Button startTaskBtn=new Button("开始");
                        hbox.getChildren().addAll(startTaskBtn);
                        
                        Task task=(Task)this.getTableRow().getItem();
                        if (task!=null && !task.getStatus().equals(Task.TaskStatus.HAS_ASSIGNED)){
                            startTaskBtn.setDisable(false);
                        }
                        
                        startTaskBtn.setOnAction(e->{
                            
                        });
                        
                        this.setGraphic(hbox);
                    }
                    
                }
            };
            
            return tableCell;
        });
        
        /***************字典数据tab***************/
        this.drugCategory_cbox.setButtonCell(new StringListCell());
        this.drugCategory_cbox.setCellFactory(view->new StringListCell());
        
        this.mfr_cbox.setButtonCell(new StringListCell());
        this.mfr_cbox.setCellFactory(view->new StringListCell());
//        this.mfr_cbox.setEditable(true);

        this.drugDictTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        this.mdseCNameColumn.setCellValueFactory(new PropertyValueFactory("mdseEName"));
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
        
    }    
    
    public void setDrugManageApp(DrugManageApp drugManageApp) {
        this.drugManageApp = drugManageApp;
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
