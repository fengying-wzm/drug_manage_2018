/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.task;

import com.drug.DrugManageApp;
import com.drug.model.DrugCategory;
import com.drug.model.DrugDict;
import com.drug.model.Task;
import com.drug.util.StringListCell;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class TaskMgtOverviewController implements Initializable {

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
    private ComboBox<Map> user_cbox;
    @FXML
    private Button queryBtn;
    @FXML
    private Button addBtn;
    
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
    private TableColumn<Task,HBox> optColumn;
    
    /*****************字典数据tab*****************/
    @FXML
    private ComboBox<String> dataProcStatus_cbox;
    @FXML
    private ComboBox<DrugCategory> drugCategory_cbox;
    @FXML
    private ComboBox<Map> mfr_cbox;
//    @FXML
//    private ComboBox<Map> atc_cbox;
    @FXML
    private TextField drugNameField;
    
    @FXML
    private Label resultTotalLbl;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private Button addToListBtn;
    @FXML
    private ComboBox<Map<String,Object>> showList_cbox;
    @FXML
    private Button addToTaskBtn;
    
    @FXML
    private TableView<DrugDict> drugDictTableView;
    
    @FXML
    private TableColumn<DrugDict,CheckBox> checkColumn;
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
    
    private ObservableList<String> dataProcStatusList;
    private ObservableList<Map> userList;
    private ObservableList<Map> mfrList;
    private ObservableList<DrugCategory> drugCategoryList;
    
    private ObservableList<Task> taskData;
    
    private ObservableList<DrugDict> drugDictData;
    private ObservableList<Map<String,Object>> selectedDataList;
   
    private StringProperty resultTotal=new SimpleStringProperty("2000");
    private List<Long> relIds;
    
    private DrugManageApp drugManageApp;
    

    @FXML
    private ObservableList<Task> getTasks(){
        return null;
    }
    @FXML
    private void addTask(){
        
    }
    
    private int selectedDataCount;
    @FXML
    private void addToList(){
        if (this.relIds==null){
            this.relIds=new ArrayList();
        }
        
        String filter="";
        if (this.dataProcStatus_cbox.getValue()!=null) filter+=this.dataProcStatus_cbox.getValue()+",";
        if (this.drugCategory_cbox.getValue()!=null) filter+=this.drugCategory_cbox.getValue().getName()+",";
        if (this.drugNameField.getText()!=null && !this.drugNameField.getText().equals("")) filter+=this.drugNameField.getText()+",";
        if (this.mfr_cbox.getValue()!=null) filter+=this.mfr_cbox.getValue().get("name");
        
        if (this.selectedDataList==null){
            this.selectedDataList=FXCollections.observableArrayList();
            this.showList_cbox.setItems(this.selectedDataList);
        }
        
        Map<String,Object> map=new HashMap();
        int count=Integer.valueOf(this.toField.getText())-Integer.valueOf(this.fromField.getText())+1;
        map.put("name", filter+"("+count+")");
        map.put("relIds", this.relIds);
        this.selectedDataList.add(map);
        
        this.selectedDataCount+=count;
        
        String str=this.addToListBtn.getText().substring(0, 5);
        str+="("+this.selectedDataCount+")";
        this.addToListBtn.setText(str);
        
        
    }
    private void popupTaskEditDialog(String title,String type,Task task) throws Exception{
       FXMLLoader loader=new FXMLLoader();
        loader.setLocation(this.getClass().getResource("TaskEditView.fxml"));
        GridPane userEditView=loader.load();
        TaskEditViewController taskEditViewController=loader.getController();
        taskEditViewController.setUserList(this.userList);
        
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
        
        this.user_cbox.setButtonCell(new StringListCell());
        this.user_cbox.setCellFactory(view->new StringListCell());
        
        this.assignedOnColumn.setCellValueFactory(new PropertyValueFactory("assignedOn"));
        this.typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
        this.countColumn.setCellValueFactory(new PropertyValueFactory("count"));
        
        this.statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        this.processedCountColumn.setCellValueFactory(new PropertyValueFactory("processedCount"));
        this.startOnColumn.setCellValueFactory(new PropertyValueFactory("startOn"));
        this.completedOnColumn.setCellValueFactory(new PropertyValueFactory("completedOn"));
        
        this.optColumn.setCellFactory(col->{
            TableCell<Task,HBox> tableCell=new TableCell<Task,HBox>(){
                @Override
                protected void updateItem(HBox item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                     
                    if (!empty){
                        HBox hbox=new HBox();
                        hbox.setAlignment(Pos.CENTER);
                        Button editBtn=new Button("修改");
                        Button delBtn=new Button("删除");
                        hbox.getChildren().addAll(editBtn,delBtn);
                        
                        Task task=(Task)this.getTableRow().getItem();
                        if (task!=null && !task.getStatus().equals(Task.TaskStatus.HAS_ASSIGNED)){
                            editBtn.setDisable(false);
                            delBtn.setDisable(false);
                        }
                        
                        editBtn.setOnAction(e->{
                            try {
                                popupTaskEditDialog("修改任务", Edit, task);
                            } catch (Exception ex) {
                                Logger.getLogger(TaskMgtOverviewController.class.getName()).log(Level.INFO, "TaskEditView弹出窗口异常！", ex);
                            }
                        });
                        delBtn.setOnAction(e->{
                            this.getTableView().getItems().remove(this.getIndex(), this.getIndex()+1);
                            this.getTableView().refresh();
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

        this.resultTotalLbl.textProperty().bind(this.resultTotal);
        this.fromField.setText(""+1);
        this.toField.setText(this.resultTotal.get());
        
        this.showList_cbox.setVisibleRowCount(10);
        this.showList_cbox.setButtonCell(new StringListCell());
        this.showList_cbox.setCellFactory(view->{
            ListCell listCell=new ListCell<Map<String,Object>>(){
                
                public void ListCell(){
                    this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                        if (e.getTarget() instanceof Button){
                            this.getListView().getItems().remove(this.getIndex(), this.getIndex()+1);
                        }
                    });
                }
                
                @Override
                protected void updateItem(Map<String, Object> item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    
                    if (!empty){
                        HBox hbox=new HBox();
                        hbox.setAlignment(Pos.CENTER_LEFT);
                        Label lbl=new Label((String)item.get("name"));
                        lbl.setTooltip(new Tooltip(lbl.getText()));
                        lbl.setMaxWidth(150);
                        
                        Pane pane=new Pane();
                        HBox.setHgrow(pane, Priority.ALWAYS);
                        
                        ImageView imageView=new ImageView("./resources/images/close.png");
                        Button imageBtn=new Button();
                        imageBtn.setGraphic(imageView);
                        hbox.getChildren().addAll(lbl,pane,imageBtn);
                        
                        this.setGraphic(hbox);
                        
                        imageBtn.addEventFilter(MouseEvent.MOUSE_CLICKED,e->{
                            this.getListView().getItems().remove(this.getIndex(), this.getIndex()+1);
                        });
                    }
                }
            };
            
            return listCell;
        });
        
        this.addToTaskBtn.setOnAction(e->{
            try {
                if (this.selectedDataCount==0){
                    return;
                }
                
                Task newTask=new Task();
                List<Long> relIds=new ArrayList();
                if (this.selectedDataList==null || this.selectedDataList.isEmpty()){
                    relIds.addAll(this.relIds);
                }
                else{
                    this.selectedDataList.forEach(map->{
                        relIds.addAll((List<Long>)map.get("relIds"));
                    });
                }
                newTask.setRelIds(relIds);
                newTask.setCount(this.selectedDataCount);
                this.popupTaskEditDialog("添加任务", ADD, newTask);
            } catch (Exception ex) {
                Logger.getLogger(TaskMgtOverviewController.class.getName()).log(Level.INFO, "添加任务失败！", ex);
            }
        });
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
        
        this.checkColumn.setGraphic(selectAll_checkBox);
        this.checkColumn.setCellFactory(col->{
            TableCell<DrugDict,CheckBox> tableCell=new TableCell<DrugDict,CheckBox>(){
                @Override
                protected void updateItem(CheckBox item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    
                    if (!empty){
                        CheckBox checkBox=new CheckBox();
//                        checkBox.selectedProperty().bind(this.getTableRow().selectedProperty());
                        this.getTableRow().selectedProperty().addListener(e->{
                            checkBox.setSelected(this.getTableRow().selectedProperty().get());
                            if (this.getTableView().getSelectionModel().getSelectedItems().size()>0){
                                if (this.getTableView().getItems().size()!=this.getTableView().getSelectionModel().getSelectedItems().size()){
                                    selectAll_checkBox.setIndeterminate(true);
                                }
                                else{
                                    selectAll_checkBox.setIndeterminate(false);
                                    selectAll_checkBox.setSelected(true);
                                }
                            }
                            else{
                                selectAll_checkBox.setIndeterminate(false);
                                selectAll_checkBox.setSelected(false);
                            }
                        });
                        
                        checkBox.setOnAction(e->{
                            if (checkBox.isSelected()){
                                this.getTableView().getSelectionModel().select(this.getIndex());
                            }
                            else{
                                this.getTableView().getSelectionModel().clearSelection(this.getIndex());
                            }
                        });
                        
                        this.setGraphic(checkBox);
                    }
                    
                }
                
            };
                    
            return tableCell;
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
        
        this.user_cbox.setItems(userList);
    }
    
    public void setDataProcStatusList(ObservableList<String> dataProcStatusList){
        this.dataProcStatusList=dataProcStatusList;
        this.dataProcStatus_cbox.setItems(this.dataProcStatusList);
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
