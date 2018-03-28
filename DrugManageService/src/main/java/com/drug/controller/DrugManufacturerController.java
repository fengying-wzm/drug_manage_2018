/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.controller;

import com.drug.entity.Manufacturer;
import com.drug.service.DrugManufacturerService;
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
public class DrugManufacturerController {
    @Autowired
    private DrugManufacturerService drugMfrService;
    
    @RequestMapping({"/getDrugManufacturerAll","/getDrugManufacturerList"})
    public List<Manufacturer> getAll(){
        return this.drugMfrService.getAll(); 
    }
    
    @RequestMapping("/getDrugManufacturerData")
    public Page<Manufacturer> getDrugMfrData(String name,String countryCode,@RequestParam(defaultValue = "1")int pageNumber,@RequestParam(defaultValue = "100")int pageSize){
        return this.drugMfrService.getDrugMfrData(name, countryCode,pageNumber,pageSize);
    }
    
    @RequestMapping({"/addDrugManufacturer","/updateDrugManufacturer"})
    public Manufacturer addDrugMfr(@RequestBody Manufacturer mfr){
        return this.drugMfrService.addDrugMfr(mfr);
    }
    
    @RequestMapping("/deleteDrugManufacturer")
    public void deleteDrugMfr(long id){
        this.drugMfrService.deleteDrugMfr(id);
    }
}
