/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.controller;

import com.drug.entity.Drug;
import com.drug.entity.DrugCategory;
import com.drug.entity.DrugDict;
import com.drug.service.DrugCategoryService;
import com.drug.service.DrugDictService;
import com.drug.service.DrugService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zjj
 */
@RestController
public class DrugDictController {
    
    @Autowired
    private DrugService drugService;
    @Autowired
    private DrugDictService drugDictService;
    @Autowired
    private DrugCategoryService drugCategoryService;
    
    
    @RequestMapping("/getByName")
    public List<Drug> getByName(@RequestParam("name") String name){
        return this.drugService.getByName(name);
    }
    
    @RequestMapping("/getAll")
    public List<Drug> getAll(){
        return this.drugService.getAll();
    }
    
    @RequestMapping("/getTop10ByGenericCName")
    public List<DrugDict> getTop10ByGenericCName(@RequestParam("name")String name){
        return this.drugDictService.getTop10ByGenericCName(name);
    }
    
    @RequestMapping("/getByMfrId")
    public List<DrugDict> getByMfrId(@RequestParam("id")long mfrId){
        return this.drugDictService.getByMfrId(mfrId);
    }
    
    /***********DrugCategory***************/
    @RequestMapping("/getDrugCategoryList")
    public List<DrugCategory> getDrugCategoryList(){
        return this.drugCategoryService.getAll();
    }
}
