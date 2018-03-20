/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.dao.IDrugGenericDao;
import com.drug.entity.DrugGeneric;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class DrugGenericService {
    @Autowired
    private IDrugGenericDao drugGenericDao;
    
    public List<DrugGeneric> getAll(){
        return this.drugGenericDao.findAll();
    }
    
}
