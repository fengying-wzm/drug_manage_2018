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
    public List<Task> getByAssignedTo(int id){
        return this.taskDao.findByAssignedToId(id);
    }
}
