/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 任务分配实体类
 * @author zjj
 */
@Entity
@Table(name="task")
public class Task implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="assigned_on",updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp assignedOn;
    
    @Column(name="type")
    private String type;
    
    @Column(name="count")
    private Integer count;
    
    @Column(name="status")
    private String status;
    
    @Column(name="assigned_to_id")
    private Integer assignedToId;
    
    @Column(name="processed_count")
    private Integer processedCount;
    
    @Column(name="started_on",insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp startedOn;
    
    @Column(name="completed_on",insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp completedOn;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getAssignedOn() {
        return assignedOn;
    }

    public void setAssignedOn(Timestamp assignedOn) {
        this.assignedOn = assignedOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Integer assignedToId) {
        this.assignedToId = assignedToId;
    }

    public Integer getProcessedCount() {
        return processedCount;
    }

    public void setProcessedCount(Integer processedCount) {
        this.processedCount = processedCount;
    }

    public Timestamp getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Timestamp startedOn) {
        this.startedOn = startedOn;
    }

    public Timestamp getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Timestamp completedOn) {
        this.completedOn = completedOn;
    }
    
     public static class TaskType{
        public static final String MODIFY_STD_DICT="标准字典修订";
        public static final String CHECK_STD_DICT="标准字典复核";
        public static final String MODIFY_HOSP_DICT="医院字典修订";
        public static final String CHECK_HOSP_DICT="医院字典复核";
    }
     
    public static class TaskStatus{
        public static final String HAS_ASSIGNED="已分配";
        public static final String IN_PROCESS="进行中";
        public static final String COMPLETED="已完成";
    }
}
