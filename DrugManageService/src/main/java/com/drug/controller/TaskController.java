/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.controller;

import com.drug.entity.DrugDict;
import com.drug.entity.Task;
import com.drug.service.DrugDictService;
import com.drug.service.TaskService;
import com.drug.vo.TaskVO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private DrugDictService drugDictService;
    
    @RequestMapping("/getTaskData")
    public List<Task> getAll(){
        return this.taskService.getAll();
    }
    @RequestMapping("/getTaskById")
    public Task getTaskById(@RequestParam("id")long taskId){
        return this.taskService.getTaskById(taskId);
    }
    
    @RequestMapping("/getTasksByAssignedTo")
    public List<Task> getTasksByAssignedTo(@RequestParam("id")int userId){
        return this.taskService.getByAssignedTo(userId);
    }
    @RequestMapping("/getTasksByTypeAndStatusAndAssignedTo")
    public List<Task> getByTypeAndStatusAndAssignedTo(String type,String status,@RequestParam("id")int userId){
        return this.taskService.getByTypeAndStatusAndAssignedTo(type, status, userId);
    }
    
    @RequestMapping("/getTaskIdsByStatusAndAssignedTo")
    public List<Long> getIdsByStatusAndAssignedTo(String status,@RequestParam("id")int userId){
        return this.taskService.getIdsByStatusAndAssignedTo(status,userId);
    }
    
    @RequestMapping("/addTask")
    @Transactional
    public Task addTask(@RequestBody TaskVO taskVO){
        Task newTask=new Task();
        newTask.setAssignedOn(taskVO.getAssignedOn());
        newTask.setType(taskVO.getType());
        newTask.setCount(taskVO.getCount());
        newTask.setStatus(taskVO.getStatus());
        newTask.setAssignedToId(taskVO.getAssignedToId());
        newTask.setProcessedCount(taskVO.getProcessedCount());
        newTask.setStartedOn(taskVO.getStartedOn());
        newTask.setCompletedOn(taskVO.getCompletedOn());
        
        Task result=this.taskService.addTask(newTask);
        
        List<Long> relIds=taskVO.getRelIds();
        if (relIds!=null){
            relIds.forEach(id->{
                DrugDict drugDict=this.drugDictService.getById(id);
                drugDict.setTaskId(result.getId());
                
                String drugStatus=null;
                switch(result.getType()){
                    case Task.TaskType.MODIFY_STD_DICT:
                        drugStatus=DrugDict.DrugStatus.ASSIGNED_MODIFY;
                        break;
                    case Task.TaskType.CHECK_STD_DICT:
                        drugStatus=DrugDict.DrugStatus.ASSIGNED_CHECK;
                        break;
//                    case Task.TaskType.MODIFY_HOSP_DICT:
//                        
//                        break;
//                    case Task.TaskType.CHECK_HOSP_DICT:
//                        
//                        break;
                }
                drugDict.setStatus(drugStatus);
                
                this.drugDictService.updateDrugDict(drugDict);
            });
        }
        
        return result;
    }
    @RequestMapping("/updateTask")
    public Task updateTask(@RequestBody Task task){
        return this.taskService.addTask(task);
    }
    @RequestMapping("/startTask")
    public Task startTask(@RequestParam("id")long taskId){
        Task task=this.getTaskById(taskId);
        task.setStartedOn(Timestamp.valueOf(LocalDateTime.now()));
        task.setStatus(Task.TaskStatus.IN_PROCESS);
        return this.taskService.addTask(task);
    }
    @RequestMapping("/completeTask")
    public Task completeTask(@RequestParam("id")long taskId){
        Task task=this.getTaskById(taskId);
        task.setCompletedOn(Timestamp.valueOf(LocalDateTime.now()));
        task.setStatus(Task.TaskStatus.COMPLETED);
        return this.taskService.addTask(task);
    }
    
    @RequestMapping("/deleteTask")
    public void deleteTask(@RequestParam("id") long taskId){
        this.taskService.deleteTask(taskId);
    }
}
