/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.task;

import com.drug.model.Task;
import com.drug.util.StringListCell;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class TaskEditViewController implements Initializable {

    @FXML
    private TextField assignedOnField;
    @FXML
    public ComboBox<String> type_cbox;
    @FXML
    public TextField countField;
    @FXML
    public ComboBox<Map> assignedTo_cbox;
    @FXML
    private ComboBox<String> status_cbox;
    @FXML
    private TextField proecssedCountField;
    @FXML
    private TextField startOnField;
    @FXML
    private TextField completedOnField;
    
    private ObservableList<Map> userList;
    private Task task;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.assignedOnField.setEditable(false);
        this.type_cbox.setDisable(true);
        this.countField.setEditable(false);
        this.assignedTo_cbox.setDisable(false);
        this.status_cbox.setDisable(true);
        this.proecssedCountField.setEditable(false);
        this.startOnField.setEditable(false);
        this.completedOnField.setEditable(false);
            
        this.type_cbox.setItems(Task.TaskType.getTaskType());
        this.status_cbox.setItems(Task.TaskStatus.getTaskStatus());
    }    
    
    /**
     * 设置窗口类型：add-添加、view-查看、edit-编辑
     */
    public void setType(String type){
        if (type.equals("view")){
            this.assignedTo_cbox.setDisable(true);
        }
    }
    
    public void setUserList(ObservableList<Map> userList){
        this.userList=userList;
        this.assignedTo_cbox.setButtonCell(new StringListCell());
        this.assignedTo_cbox.setCellFactory(listview->new StringListCell());
        this.assignedTo_cbox.setItems(this.userList);
        
        if (this.task!=null){
            this.selectUser();
        }
    }
    
    public void setTask(Task task){
        if (task==null){
            return;
        }
        
        this.task=task;
        
        if (this.task.getAssignedOn()!=null){
            this.assignedOnField.setText(this.task.getAssignedOn().toString());
        }
        
        this.countField.setText(String.valueOf(this.task.getCount()));
        this.proecssedCountField.setText(String.valueOf(this.task.getProcessedCount()));
        if (this.task.getStartedOn()!=null){
            this.startOnField.setText(this.task.getStartedOn().toString());
        }
        
        if (this.task.getCompletedOn()!=null){
            this.completedOnField.setText(this.task.getCompletedOn().toString());
        }
        
        this.type_cbox.setValue(this.task.getType());
        this.status_cbox.setValue(this.task.getStatus());
        
        int userId=this.task.getAssignedToId();
        
        if (this.userList!=null){
            this.selectUser();
        }
    }
    
    private void selectUser(){
        this.userList.forEach(user->{
            if ((int)user.get("id")==this.task.getAssignedToId()){
                this.assignedTo_cbox.setValue(user);
            }
        });
    }
}
