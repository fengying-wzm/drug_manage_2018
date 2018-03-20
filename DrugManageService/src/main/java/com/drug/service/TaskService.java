/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.dao.ITaskDao;
import com.drug.entity.Task;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class TaskService {
    @Autowired
    private ITaskDao taskDao;
    
    public List<Task> getAll(){
        return (List<Task>)this.taskDao.findAll();
    }
    public Task getTaskById(Long taskId){
        return (Task)this.taskDao.findById(taskId);
    }
    public List<Task> getByAssignedTo(int id){
        return this.taskDao.findByAssignedToId(id);
    }
   
    public List<Task> getByTypeAndStatusAndAssignedTo(String type,String status,int userId){
        if (type!=null && !type.isEmpty() && status!=null && !status.isEmpty() && userId>0){
            return this.taskDao.findByTypeAndStatusAndAssignedToId(type, status, userId);
        }else if (type!=null && !type.isEmpty() && status!=null && !status.isEmpty()){
                return this.taskDao.findByTypeAndStatus(type, status);
        }else if (type!=null && !type.isEmpty() && userId>0){
            return this.taskDao.findByTypeAndAssignedToId(type,userId);
        }else if (status!=null && !status.isEmpty() && userId>0){
            return this.taskDao.findByStatusAndAssignedToId(status,userId);
        }else if (type!=null && !type.isEmpty()){
            return this.taskDao.findByType(type);
        }else if (status!=null && !status.isEmpty()){
            return this.taskDao.findByStatus(status);
        }else if (userId>0){
            return this.taskDao.findByAssignedToId(userId);
        }
        else{
            return this.getAll();
        }
    }
    
    public List<Long> getIdsByStatusAndAssignedTo(String status ,int userId){
        return this.taskDao.findIdsByStatusAndAssignedToId(status,userId);
    }
    
    public Task addTask(Task task){
        return this.taskDao.save(task);
    }
    
    public void deleteTask(Long taskId){
        this.taskDao.delete(taskId);
    }
}
