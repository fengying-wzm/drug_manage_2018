/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.dao;

import com.drug.entity.DrugDict;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zjj
 */
@Repository
public interface IDrugDictDao extends JpaRepository<DrugDict, Long>,JpaSpecificationExecutor<DrugDict>{
    public DrugDict findById(long id);
    
    @Query("select t.id from DrugDict t")
    public List<Long> findIds();
    
    public List<DrugDict> findByTaskIdIn(List<Long> taskIds);
    
}
