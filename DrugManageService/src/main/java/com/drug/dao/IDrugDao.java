/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.dao;

import com.drug.entity.Drug;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zjj
 */
@Repository
public interface IDrugDao extends CrudRepository<Drug, Integer>{
    @Query("select t from Drug t where t.name_c=:name")
    public List<Drug> findByName(@Param("name") String name);
}
