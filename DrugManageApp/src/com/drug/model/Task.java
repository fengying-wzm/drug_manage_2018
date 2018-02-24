/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.model;

import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    public static final int DEFAULT_COUNT=1000;
    
    public static int task_count;
    
    private final IntegerProperty id;
    private final ObjectProperty<LocalDate> assignedOn;
    private final StringProperty type;
    private final IntegerProperty count;
    private final IntegerProperty assignedToId;
    private final StringProperty status;
    private final IntegerProperty processedCount;
    private final ObjectProperty<LocalDate> startOn;
    private final ObjectProperty<LocalDate> completedOn;
    private final ObjectProperty<List<Long>> relIds = new SimpleObjectProperty<>();

    public Task() {
        this(null,0,0);
    }
    public Task(String type,int count,int assignedToId) {
        this.task_count+=1;
        this.id = new SimpleIntegerProperty(task_count);
        this.assignedOn=new SimpleObjectProperty(LocalDate.now());
        this.type=new SimpleStringProperty(type);
        this.count=new SimpleIntegerProperty(count);
        this.assignedToId=new SimpleIntegerProperty(assignedToId);
        this.status=new SimpleStringProperty(Task.TaskStatus.HAS_ASSIGNED);
        this.processedCount=new SimpleIntegerProperty();
        this.startOn=new SimpleObjectProperty();
        this.completedOn=new SimpleObjectProperty();
    }
    
    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    
    public LocalDate getAssignedOn() {
        return assignedOn.get();
    }

    public void setAssignedOn(LocalDate value) {
        assignedOn.set(value);
    }

    public ObjectProperty<LocalDate> assignedOnProperty() {
        return assignedOn;
    }
    
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
    
    public LocalDate getStartOn() {
        return startOn.get();
    }

    public void setStartOn(LocalDate value) {
        startOn.set(value);
    }

    public ObjectProperty startOnProperty() {
        return startOn;
    }

    public LocalDate getCompletedOn() {
        return completedOn.get();
    }

    public void setCompletedOn(LocalDate value) {
        completedOn.set(value);
    }

    public ObjectProperty completedOnProperty() {
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
        public static final String MODIFY_DICT="字典修订";
        public static final String CHECK_DICT="字典复核";
        public static final String MODIFY_ITEM="条目修订";
        public static final String CHECK_ITEM="条目复核";

        public static ObservableList<String> getTaskType(){
            ObservableList<String> taskTypeList=FXCollections.observableArrayList();
            taskTypeList.addAll(MODIFY_DICT,CHECK_DICT,MODIFY_ITEM,CHECK_ITEM);

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
