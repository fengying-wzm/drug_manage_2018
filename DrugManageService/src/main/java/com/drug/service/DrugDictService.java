/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.entity.DrugDict;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.drug.dao.IDrugDictDao;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class DrugDictService {
    @Autowired
    private IDrugDictDao drugDictDao;
    
    public List<DrugDict> getTop10ByGenericCName(String name){
        return this.drugDictDao.findFirst10ByGenericCName(name);
    }
    public List<DrugDict> getByMfrId(long mfrId){
        return (List<DrugDict>)this.drugDictDao.findByMfrId(mfrId);
    }
    
}
