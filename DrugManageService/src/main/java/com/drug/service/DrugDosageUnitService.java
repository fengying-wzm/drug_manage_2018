/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.dao.IDrugDosageUnitDao;
import com.drug.entity.DrugDosageUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class DrugDosageUnitService {
    @Autowired
    private IDrugDosageUnitDao drugDosageUnitDao;
    
    public List<DrugDosageUnit> getAll(){
        return this.drugDosageUnitDao.findAll();
    }
}
