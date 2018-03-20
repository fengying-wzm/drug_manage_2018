/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.model;

import com.drug.util.DateUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 任务实体类
 * @author zjj
 */
public class Task {
    private final LongProperty id;
//    private final ObjectProperty<LocalDateTime> assignedOn;
    private final StringProperty assignedOn;
    private final StringProperty type;
    private final IntegerProperty count;
    private final IntegerProperty assignedToId;
    private final StringProperty status;
    private final IntegerProperty processedCount;
//    private final ObjectProperty<LocalDateTime> startedOn;
//    private final ObjectProperty<LocalDateTime> completedOn;
    private final StringProperty startedOn;
    private final StringProperty completedOn;
    private final ObjectProperty<List<Long>> relIds = new SimpleObjectProperty<>();

    public Task() {
        this(null,0,0);
    }
    public Task(String type,int count,int assignedToId) {
        this.id = new SimpleLongProperty();
//        this.assignedOn=new SimpleObjectProperty(LocalDateTime.now());
        this.assignedOn=new SimpleStringProperty();
        this.type=new SimpleStringProperty(type);
        this.count=new SimpleIntegerProperty(count);
        this.assignedToId=new SimpleIntegerProperty(assignedToId);
        this.status=new SimpleStringProperty(Task.TaskStatus.HAS_ASSIGNED);
        this.processedCount=new SimpleIntegerProperty();
//        this.startedOn=new SimpleObjectProperty();
//        this.completedOn=new SimpleObjectProperty();
        this.startedOn=new SimpleStringProperty();
        this.completedOn=new SimpleStringProperty();
    }
    
    public long getId() {
        return id.get();
    }

    public void setId(long value) {
        id.set(value);
    }

    public LongProperty idProperty() {
        return id;
    }
    
    public String getAssignedOn() {
        return assignedOn.get();
    }

    public void setAssignedOn(String value) {
        this.assignedOn.set(value);
    }
    
    public StringProperty assignedOnProperty(){
        return this.assignedOn;
    }
//    public LocalDateTime getAssignedOn() {
//        return assignedOn.get();
//    }
//    public void setAssignedOn(LocalDateTime value) {
//        assignedOn.set(value);
//    } 
//    public ObjectProperty<LocalDateTime> assignedOnProperty() {
//        return assignedOn;
//    }
    
    public String getType() {
        return type.get();
    }

    public void setType(String value) {
        type.set(value);
    }

    public StringProperty typeProperty() {
        return type;
    }
    
    public int getCount() {
        return count.get();
    }

    public void setCount(int value) {
        count.set(value);
    }

    public IntegerProperty countProperty() {
        return count;
    }  
    
    public int getAssignedToId() {
        return assignedToId.get();
    }

    public void setAssignedToId(int value) {
        assignedToId.set(value);
    }

    public IntegerProperty assignedToIdProperty() {
        return assignedToId;
    }
    
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public StringProperty statusProperty() {
        return status;
    }

     public int getProcessedCount() {
        return processedCount.get();
    }

    public void setProcessedCount(int value) {
        processedCount.set(value);
    }

    public IntegerProperty processedCountProperty() {
        return processedCount;
    }
    
//    public LocalDateTime getStartedOn() {
//        return startedOn.get();
//    }
//
//    public void setStartedOn(LocalDateTime value) {
//        startedOn.set(value);
//    }
//
//    public ObjectProperty startedOnProperty() {
//        return startedOn;
//    }
//
//    public LocalDateTime getCompletedOn() {
//        return completedOn.get();
//    }
//
//    public void setCompletedOn(LocalDateTime value) {
//        completedOn.set(value);
//    }
//
//    public ObjectProperty completedOnProperty() {
//        return completedOn;
//    }
    
    public String getStartedOn() {
        return startedOn.get();
    }

    public void setStartedOn(String value) {
        startedOn.set(value);
    }

    public StringProperty startedOnProperty() {
        return startedOn;
    }

    public String getCompletedOn() {
        return completedOn.get();
    }

    public void setCompletedOn(String value) {
        completedOn.set(value);
    }

    public StringProperty completedOnProperty() {
        return completedOn;
    }
    
     public List getRelIds() {
        return relIds.get();
    }

    public void setRelIds(List value) {
        relIds.set(value);
    }

    public ObjectProperty relIdsProperty() {
        return relIds;
    }
    
    public static class TaskType{
        public static final String MODIFY_STD_DICT="标准字典修订";
        public static final String CHECK_STD_DICT="标准字典复核";
        public static final String MODIFY_HOSP_DICT="医院字典修订";
        public static final String CHECK_HOSP_DICT="医院字典复核";

        public static ObservableList<String> getTaskType(){
            ObservableList<String> taskTypeList=FXCollections.observableArrayList();
            taskTypeList.addAll(MODIFY_STD_DICT,CHECK_STD_DICT,MODIFY_HOSP_DICT,CHECK_HOSP_DICT);

            return taskTypeList;
        }
    }

    public static class TaskStatus{
        public static final String HAS_ASSIGNED="已分配";
        public static final String IN_PROCESS="进行中";
        public static final String COMPLETED="已完成";

        public static ObservableList<String> getTaskStatus(){
            ObservableList<String> taskStatusList=FXCollections.observableArrayList();
            taskStatusList.addAll(HAS_ASSIGNED,IN_PROCESS,COMPLETED);

            return taskStatusList;

        }
    }
}
