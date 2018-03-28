/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.controller;

import com.drug.entity.DrugGeneric;
import com.drug.service.DrugGenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zjj
 */
@RestController
public class DrugGenericController {
    @Autowired
    private DrugGenericService drugGenericService;
    
    @RequestMapping(value = {"/getDrugGenericAll","/getDrugGenericList"})
    public List<DrugGeneric> getAll(){
        return this.drugGenericService.getAll();
    }
    
    @RequestMapping("/getDrugGenericData")
    public Page<DrugGeneric> getDrugGenericData(String name,@RequestParam(defaultValue = "1")int pageNumber,@RequestParam(defaultValue = "100")int pageSize){
        return this.drugGenericService.getDrugGenericData(name,pageNumber,pageSize);
    }
    
    @RequestMapping({"/addDrugGeneric","/updateDrugGeneric"})
    public DrugGeneric addDrugGeneric(@RequestBody DrugGeneric generic){
        return this.drugGenericService.addDrugGeneric(generic);
    }
    
    @RequestMapping("/deleteDrugGeneric")
    public void deleteDrugGeneric(long id){
        this.drugGenericService.deleteDrugGeneric(id);
    }
}
