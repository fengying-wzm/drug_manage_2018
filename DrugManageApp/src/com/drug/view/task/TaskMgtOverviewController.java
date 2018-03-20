/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.task;

import com.drug.DrugManageApp;
import com.drug.model.DrugCategory;
import com.drug.model.DrugDict;
import com.drug.model.DrugGeneric;
import com.drug.model.Task;
import com.drug.util.HttpMethod;
import com.drug.util.IPaddress;
import com.drug.util.StringListCell;
import com.drug.view.drugdict.DrugDictMgtOverviewController;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class TaskMgtOverviewController implements Initializable {

    public static final String ADD="add";
    public static final String Edit="edit";
    public static final String VIEW="view";
    
    public static final String TOOLTIP_CLEAR="清空选择条件";
    
    public static final int DEFAULT_ASSIGNED_COUNT=2000;
    
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab taskTab;
    @FXML
    private Tab stdDicTab;
    @FXML
    private ComboBox<String> taskType_cbox;
    @FXML
    private ComboBox<String> taskStatus_cbox;
    @FXML
    private ComboBox<Map> user_cbox;
    @FXML
    private CheckBox taskFilter_clearChkBox;
    @FXML
    private Button queryTaskBtn;
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
    private TableColumn<Task,LocalDate> startedOnColumn;
    @FXML
    private TableColumn<Task,LocalDate> completedOnColumn;
    @FXML
    private TableColumn<Task,HBox> optColumn;
    
    /*****************标准字典数据选择tab*****************/
    @FXML
    private ComboBox<String> stdDictDataStatus_cbox;
    @FXML
    private ComboBox<DrugCategory> stdDictDataCategory_cbox;
    @FXML
    private ComboBox<DrugGeneric> stdDictDataGenericName_cbox;
    @FXML
    private ComboBox<Map> stdDictDataMfr_cbox;
//    @FXML
//    private ComboBox<Map> atc_cbox;
     @FXML
    private CheckBox stdDictFilter_clearChkBox;
    @FXML
    private Button queryStdDictBtn;
    
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
    private ObservableList<DrugGeneric> drugGenericList;
    
    private ObservableList<Task> taskData;
    
    private Map<String,Object> currStdDictPage;
    private ObservableList<DrugDict> stdDictData;
    private ObservableList<Map<String,Object>> selectedDataList;
   
    private StringProperty resultTotal=new SimpleStringProperty();
    private List<Long> relIds;
    
    private DrugManageApp drugManageApp;
    

    @FXML
    private void enableClearTaskFilter(){
        this.taskFilter_clearChkBox.setDisable(false);
        this.taskFilter_clearChkBox.setSelected(false);
    }
    @FXML
    private void clearTaskFilter(){
        this.taskType_cbox.getSelectionModel().clearSelection();
        this.taskStatus_cbox.getSelectionModel().clearSelection();
        this.user_cbox.getSelectionModel().clearSelection();
        
        this.taskFilter_clearChkBox.setDisable(true);
    }
    @FXML
    private ObservableList<Task> queryTasks(){
        try {
            String taskType=this.taskType_cbox.getSelectionModel().getSelectedItem();
            String taskStatus=this.taskStatus_cbox.getSelectionModel().getSelectedItem();
            int userId=0;
            if (this.user_cbox.getSelectionModel().getSelectedItem()!=null){
                userId=(int)this.user_cbox.getSelectionModel().getSelectedItem().get("id");
            }

            String mathod=IPaddress.MATHOD_GET_TASKS_BY_TYPE_AND_STATUS_AND_ASSIGNEDTO+"?id="+userId;
            if (taskType!=null){
                mathod+="&type="+URLEncoder.encode(taskType,"UTF-8");
            }
            if (taskStatus!=null){
                mathod+="&status="+URLEncoder.encode(taskStatus,"UTF-8");
            }

            String jsonStr=jsonStr = HttpMethod.getGETString(mathod);
            JSONArray jsonArray=JSONArray.fromObject(jsonStr);
            List<Task> tasks=(List<Task>)JSONArray.toCollection(jsonArray, Task.class);

            this.taskData=FXCollections.observableArrayList(tasks);
            this.taskTableView.setItems(this.taskData);
        
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TaskMgtOverviewController.class.getName()).log(Level.INFO, "URLEncoder编码出错！", ex);
        }
        
        return this.taskData;
    }
    @FXML
    private void addTask(){
         Alert alert=new Alert(Alert.AlertType.INFORMATION,"请先选择数据后再添加任务！");
         alert.setHeaderText(null);
         alert.show();
    }
    
    @FXML
    private void enableClearStdDictFilter(){
        this.stdDictFilter_clearChkBox.setDisable(false);
        this.stdDictFilter_clearChkBox.setSelected(false);
    }
    @FXML
    private void clearStdDictFilter(){
        this.stdDictDataStatus_cbox.getSelectionModel().clearSelection();
        this.stdDictDataCategory_cbox.getSelectionModel().clearSelection();
        this.stdDictDataGenericName_cbox.getSelectionModel().clearSelection();
        this.stdDictDataMfr_cbox.getSelectionModel().clearSelection();
        
        this.stdDictFilter_clearChkBox.setDisable(true);
    }
    @FXML
    private ObservableList<DrugDict> queryStdDictData(){
        try{
            long mfrId=0;
            if (this.stdDictDataMfr_cbox.getSelectionModel().getSelectedItem()!=null){
                mfrId=Long.parseLong(this.stdDictDataMfr_cbox.getSelectionModel().getSelectedItem().get("id").toString());
            }
            
            String mathodName=IPaddress.MATHOD_GET_STD_DICT_DATA+"?mfrId="+mfrId;
             
            String drugStatus=this.stdDictDataStatus_cbox.getSelectionModel().getSelectedItem();
            if (drugStatus!=null){
                mathodName+="&status="+URLEncoder.encode(drugStatus,"UTF-8");
            }
            
            if (this.stdDictDataCategory_cbox.getSelectionModel().getSelectedItem()!=null){
                String drugCategory=this.stdDictDataCategory_cbox.getSelectionModel().getSelectedItem().getName();
                mathodName+="&category="+URLEncoder.encode(drugCategory,"UTF-8");
            }
            
            DrugGeneric drugGeneric=this.stdDictDataGenericName_cbox.getSelectionModel().getSelectedItem();//this.drugNameField.getText();
            if (drugGeneric!=null){
                mathodName+="&name="+URLEncoder.encode(drugGeneric.getGenericCName(), "UTF-8");
            }
            
            String jsonstr=HttpMethod.getGETString(mathodName);
            JSONObject jsonObj=JSONObject.fromObject(jsonstr);
            Map<String,Object> page=(Map<String,Object>)JSONObject.toBean(jsonObj, Map.class);
            this.setStdDictPage(page);
            
            JSONArray jsonArr=JSONArray.fromObject(page.get("content"));
            List<DrugDict> result=(List<DrugDict>)JSONArray.toCollection(jsonArr, DrugDict.class);
            this.stdDictData=FXCollections.observableArrayList(result);
            this.drugDictTableView.setItems(this.stdDictData);
            
            return this.stdDictData;
            
       } catch (Exception e) {
           Logger.getLogger(DrugDictMgtOverviewController.class.getName()).log(Level.INFO,null,e);
       }
       
       return null;
    }
    
    @FXML
    private void addStdDrugToTask(){
        try {
            List<Long> relIds=new ArrayList();
            
            if (this.selectedDataCount==0){
                this.relIds=this.getStdDictIds();
                if (this.relIds==null){
                    return;
                }
                relIds.addAll(this.relIds);
            }
            else{
                this.selectedDataList.forEach(map->{
                    relIds.addAll((List<Long>)map.get("relIds"));
                });
            }
                
            Task newTask=new Task();
            newTask.setRelIds(relIds);
            newTask.setCount(relIds.size());
                
            String dataStatus=this.stdDictDataStatus_cbox.getSelectionModel().getSelectedItem();
            
            if (dataStatus==null || dataStatus.isEmpty()){
                Alert alert=new Alert(Alert.AlertType.WARNING,"数据状态不能为空，请选择后筛选数据添加至任务！");
                alert.setHeaderText(null);
                alert.show();
                return;
            }
            
            String taskType=Task.TaskType.MODIFY_STD_DICT;
            switch (dataStatus){
                case DrugDict.DrugStatus.NOASSIGNED:
                    taskType=Task.TaskType.MODIFY_STD_DICT;
                    break;
                case DrugDict.DrugStatus.MODIFIED:
                    taskType=Task.TaskType.CHECK_STD_DICT;
                    break;
            }
            
            newTask.setType(taskType);
            this.popupTaskEditDialog("添加任务", ADD, newTask);
        } catch (Exception ex) {
            Logger.getLogger(TaskMgtOverviewController.class.getName()).log(Level.INFO, "添加任务失败！", ex);
        }
    }
    
    private int selectedDataCount;
    @FXML
    private void addStdDrugToList(){
        this.relIds=this.getStdDictIds();
        if (this.relIds==null){
            return;
        }
        
        String filter="";
        if (this.stdDictDataStatus_cbox.getValue()!=null) filter+=this.stdDictDataStatus_cbox.getValue()+",";
        if (this.stdDictDataCategory_cbox.getValue()!=null) filter+=this.stdDictDataCategory_cbox.getValue().getName()+",";
        if (this.stdDictDataGenericName_cbox.getValue()!=null) filter+=this.stdDictDataGenericName_cbox.getValue().getGenericCName()+",";
        if (this.stdDictDataMfr_cbox.getValue()!=null) filter+=this.stdDictDataMfr_cbox.getValue().get("name");
        
        if (this.selectedDataList==null){
            this.selectedDataList=FXCollections.observableArrayList();
            this.showList_cbox.setItems(this.selectedDataList);
        }
        
        Map<String,Object> map=new HashMap();
        int count=this.relIds.size();
        map.put("name", filter+"("+count+")");
        map.put("relIds", this.relIds);
        this.selectedDataList.add(map);
        
        this.selectedDataCount+=count;
        
        String str=this.addToListBtn.getText().substring(0, 5);
        str+="("+this.selectedDataCount+")";
        this.addToListBtn.setText(str);
        
    }
    
    private List<Long> getStdDictIds(){
        int from=Integer.parseInt(this.fromField.getText());
        int to=Integer.parseInt(this.toField.getText());
        long total=Long.parseLong(this.resultTotal.get());
        if (total==0 || from<0 || to<0 || from>to || to>total){
            Alert alert=new Alert(Alert.AlertType.WARNING,"请输入正确的数值范围！");
            alert.setHeaderText(null);
            alert.show();
            return null;
        }
        
        String jsonStr=HttpMethod.getGETString(IPaddress.MATHOD_GET_STD_DICT_IDS+"?from="+from+"&to="+to);
        JSONArray jsonArray=JSONArray.fromObject(jsonStr);
        List <Long> ids=(List<Long>)JSONArray.toCollection(jsonArray);
        
        return ids;
    }
    private void popupTaskEditDialog(String title,String type,Task task) throws Exception{
       FXMLLoader loader=new FXMLLoader();
        loader.setLocation(this.getClass().getResource("TaskEditView.fxml"));
        GridPane taskEditView=loader.load();
        TaskEditViewController taskEditViewController=loader.getController();
        taskEditViewController.setType(type);
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
        taskEditDialog.getDialogPane().setContent(taskEditView);
        if (!type.equals(VIEW)){
            taskEditDialog.setResultConverter(btnType->{
                if (btnType==okBtn){
                    Task atask=new Task();
                    atask.setType(taskEditViewController.type_cbox.getValue());
                    atask.setCount(Integer.parseInt(taskEditViewController.countField.getText()));
                    atask.setAssignedToId((int)taskEditViewController.assignedTo_cbox.getValue().get("id"));
                    atask.setRelIds(task.getRelIds());
                    return atask;
                }

                return null;
            });
        }
        
        Optional<Task> result=taskEditDialog.showAndWait();
        if (result!=null && result.get()!=null){
            if (type.equals(ADD)){
                JSONObject jsonObj=JSONObject.fromObject(result.get());
                String resultStr=(String)HttpMethod.sendPOSTString(IPaddress.MATHOD_ADD_TASK,jsonObj.toString(), "UTF-8");
                jsonObj=JSONObject.fromObject(resultStr);
                Task newTask=(Task)JSONObject.toBean(jsonObj, Task.class);
                this.taskData.add(newTask);
            }
            
            if (type.equals(Edit)){
                task.setType(result.get().getType());
                task.setAssignedToId(result.get().getAssignedToId());
                JSONObject jsonObj=JSONObject.fromObject(task);
                String resultStr=(String)HttpMethod.sendPOSTString(IPaddress.MATHOD_UPDATE_TASK,jsonObj.toString(), "UTF-8");
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
        
        this.taskFilter_clearChkBox.setTooltip(new Tooltip(TOOLTIP_CLEAR));
        
        this.assignedOnColumn.setCellValueFactory(new PropertyValueFactory("assignedOn"));
        this.typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
        this.countColumn.setCellValueFactory(new PropertyValueFactory("count"));
        
        this.statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        this.processedCountColumn.setCellValueFactory(new PropertyValueFactory("processedCount"));
        this.startedOnColumn.setCellValueFactory(new PropertyValueFactory("startedOn"));
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
                            editBtn.setDisable(true);
                            delBtn.setDisable(true);
                        }
                        
                        editBtn.setOnAction(e->{
                            try {
                                popupTaskEditDialog("修改任务", Edit, task);
                            } catch (Exception ex) {
                                Logger.getLogger(TaskMgtOverviewController.class.getName()).log(Level.INFO, "TaskEditView弹出窗口异常！", ex);
                            }
                        });
                        delBtn.setOnAction(e->{
                            String jsonStr=HttpMethod.getGETString(IPaddress.MATHOD_DELETE_TASK+"?id="+task.getId());
                            if (jsonStr!=null){
                                this.getTableView().getItems().remove(this.getIndex(), this.getIndex()+1);
                            }
                        });
                        
                        this.setGraphic(hbox);
                    }
                    
                }
            };
            
            return tableCell;
        });
        
        /***************标准字典数据tab***************/
        this.stdDictDataCategory_cbox.setButtonCell(new StringListCell());
        this.stdDictDataCategory_cbox.setCellFactory(view->new StringListCell());
        
        this.stdDictDataGenericName_cbox.setButtonCell(new StringListCell());
        this.stdDictDataGenericName_cbox.setCellFactory(view->new StringListCell());
        this.stdDictDataGenericName_cbox.addEventHandler(ActionEvent.ACTION,e->{
            if (this.stdDictDataGenericName_cbox.getValue()!=null){
                this.stdDictDataGenericName_cbox.setTooltip(new Tooltip(this.stdDictDataGenericName_cbox.getValue().getGenericCName()));
            }else{
                this.stdDictDataGenericName_cbox.setTooltip(null);
            }
            
        });
//        this.drugGenericName_cbox.setEditable(true);
        
        this.stdDictDataMfr_cbox.setButtonCell(new StringListCell());
        this.stdDictDataMfr_cbox.setCellFactory(view->new StringListCell());
        this.stdDictDataMfr_cbox.addEventHandler(ActionEvent.ACTION,e->{
            if (this.stdDictDataMfr_cbox.getValue()!=null){
                this.stdDictDataMfr_cbox.setTooltip(new Tooltip((String)this.stdDictDataMfr_cbox.getValue().get("name")));
            }else{
                this.stdDictDataMfr_cbox.setTooltip(null);
            }
        });
        
       
//        this.mfr_cbox.setEditable(true);

        this.stdDictFilter_clearChkBox.setTooltip(new Tooltip(TOOLTIP_CLEAR));
        
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
        
//        this.addToTaskBtn.setOnAction(e->{
//            try {
//                
//            } catch (Exception ex) {
//                Logger.getLogger(TaskMgtOverviewController.class.getName()).log(Level.INFO, "添加任务失败！", ex);
//            }
//        });
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
        this.stdDictDataStatus_cbox.setItems(this.dataProcStatusList);
        this.stdDictDataStatus_cbox.getSelectionModel().select(0);
    }
    
    public void setDrugCategoryList(ObservableList<DrugCategory> drugCategoryList){
        this.drugCategoryList=drugCategoryList;
        this.stdDictDataCategory_cbox.setItems(drugCategoryList);
    }
    public void setDrugGenericList(ObservableList<DrugGeneric> drugGenericList){
        this.drugGenericList=drugGenericList;
        this.stdDictDataGenericName_cbox.setItems(drugGenericList);
    }
    public void setMfrList(ObservableList<Map> mfrList){
        this.mfrList=mfrList;
        this.stdDictDataMfr_cbox.setItems(this.mfrList);
    }
    
    public void setTaskData(ObservableList<Task> taskData){
        this.taskData=taskData;
        this.taskTableView.setItems(this.taskData);
    }
    
    public void setStdDictData(ObservableList<DrugDict> drugDictData){
        this.stdDictData=drugDictData;
        this.drugDictTableView.setItems(this.stdDictData);
    }
    
    public void setStdDictPage(Map<String,Object> page){
        this.currStdDictPage=page;
        this.relIds=null;//后续考虑返回
        long total=Long.parseLong(page.get("totalElements").toString());
        this.resultTotal.set(total+"");
        
        this.fromField.setText("");
        this.toField.setText("");
        if (total>0 && total<=DEFAULT_ASSIGNED_COUNT){
            this.fromField.setText("1");
            this.toField.setText(total+"");
        }
        
        if (total>DEFAULT_ASSIGNED_COUNT){
            this.fromField.setText("1");
            this.toField.setText(DEFAULT_ASSIGNED_COUNT+"");
        }
    }
}
