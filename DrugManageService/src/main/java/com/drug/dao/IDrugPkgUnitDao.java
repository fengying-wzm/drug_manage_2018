/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.dao;

import com.drug.entity.DrugPkgUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zjj
 */
@Repository
public interface IDrugPkgUnitDao extends JpaRepository<DrugPkgUnit, Integer>{
    
}