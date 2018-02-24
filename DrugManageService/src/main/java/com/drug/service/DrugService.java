/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.dao.IDrugDao;
import com.drug.entity.Drug;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class DrugService {
    
    @Autowired
    private IDrugDao drugDao;
    
    public List<Drug> getByName(String name){
        return this.drugDao.findByName(name);
    }
    
    public List<Drug> getAll(){
        return (List<Drug>)this.drugDao.findAll();
    }
    
}