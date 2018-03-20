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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zjj
 */
@RestController
public class DrugGenericController {
    @Autowired
    private DrugGenericService drugGenericService;
    
    @RequestMapping(value = {"/getDrugGenericData","/getDrugGenericList"})
    public List<DrugGeneric> getAll(){
        return this.drugGenericService.getAll();
    }
}
