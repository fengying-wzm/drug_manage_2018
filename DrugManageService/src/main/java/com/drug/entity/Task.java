/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    
    @Column(name="assigned_on")
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
    
    @Column(name="Started_on")
    private Timestamp StartedOn;
    
    @Column(name="completed_on")
    private Timestamp completedOn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignedOn() {
        LocalDateTime dt=assignedOn.toLocalDateTime();
        return dt.toString();
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

    public String getStartedOn() {
        return StartedOn.toLocalDateTime().toString();
    }

    public void setStartedOn(Timestamp StartedOn) {
        this.StartedOn = StartedOn;
    }

    public String getCompletedOn() {
        return completedOn.toLocalDateTime().toString();
    }

    public void setCompletedOn(Timestamp completedOn) {
        this.completedOn = completedOn;
    }
    
}
