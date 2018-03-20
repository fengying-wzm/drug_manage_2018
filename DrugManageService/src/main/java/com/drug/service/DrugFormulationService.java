/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.dao.IDrugFormulationDao;
import com.drug.entity.DrugFormulation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class DrugFormulationService {
    @Autowired
    private IDrugFormulationDao drugFormulationDao;
    
    public List<DrugFormulation> getAll(){
        return this.drugFormulationDao.findAll();
    }
}
