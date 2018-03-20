/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.dao.IDrugUnitDao;
import com.drug.entity.DrugUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class DrugUnitService {
    @Autowired
    private IDrugUnitDao drugUnitDao;
    
    public List<DrugUnit> getAll(){
        return this.drugUnitDao.findAll();
    }
}
