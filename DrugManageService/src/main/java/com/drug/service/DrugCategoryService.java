/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.drug.dao.IDrugCategoryDao;
import com.drug.entity.DrugCategory;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class DrugCategoryService {
    @Autowired
    private IDrugCategoryDao drugCategoryDao;
    
    public List<DrugCategory> getAll(){
        return (List<DrugCategory>)this.drugCategoryDao.findAll();
    }
}
