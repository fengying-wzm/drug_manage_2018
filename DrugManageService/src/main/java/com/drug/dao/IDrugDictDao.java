/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.dao;

import com.drug.entity.DrugDict;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zjj
 */
@Repository
public interface IDrugDictDao extends CrudRepository<DrugDict, Long>{
    @Query("select t from DrugDict t where t.genericCName like ?1")
    public List<DrugDict> findFirst10ByGenericCName(String name);
    
    public List<DrugDict> findByMfrId(long mfrId);
}
