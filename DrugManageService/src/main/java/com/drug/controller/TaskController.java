/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.controller;

import com.drug.entity.Task;
import com.drug.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zjj
 */
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    
    @RequestMapping("/getTaskData")
    public List<Task> getAll(){
        return this.taskService.getAll();
    }
    
    @RequestMapping("getTasksByAssignedTo")
    public List<Task> getTasksByAssignedTo(@RequestParam("id")int id){
        return this.taskService.getByAssignedTo(id);
    }
}
