/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.dao;

import com.drug.entity.Task;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author zjj
 */
@Repository
public interface ITaskDao extends CrudRepository<Task, Long>{
    public List<Task> findByAssignedToId(int id);
}
