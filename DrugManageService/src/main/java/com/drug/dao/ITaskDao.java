/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.dao;

import com.drug.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author zjj
 */
@Repository
public interface ITaskDao extends JpaRepository<Task, Long>{
    
    public Task findById(long id);
    
    public List<Task> findByType(String type);
    public List<Task> findByStatus(String status);
    public List<Task> findByAssignedToId(int userId);
    
    public List<Task> findByTypeAndStatus(String type,String status);
    public List<Task> findByTypeAndAssignedToId(String type,int userId);
    public List<Task> findByStatusAndAssignedToId(String status,int userId);
    
    public List<Task> findByTypeAndStatusAndAssignedToId(String type,String status,int userId);
    
    @Query("select t.id from Task t where t.status=?1 and t.assignedToId=?2")
    public List<Long> findIdsByStatusAndAssignedToId(String status,int userId);
    
}
