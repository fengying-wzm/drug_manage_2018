/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.controller;

import com.drug.entity.DrugCategory;
import com.drug.entity.DrugDict;
import com.drug.entity.DrugDosageUnit;
import com.drug.entity.DrugFormulation;
import com.drug.entity.DrugPkgUnit;
import com.drug.entity.DrugUnit;
import com.drug.service.DrugCategoryService;
import com.drug.service.DrugDictService;
import com.drug.service.DrugDosageUnitService;
import com.drug.service.DrugFormulationService;
import com.drug.service.DrugPkgUnitService;
import com.drug.service.DrugUnitService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
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
public class DrugDictController {
    
    @Autowired
    private DrugDictService drugDictService;
    @Autowired
    private DrugCategoryService drugCategoryService;
    @Autowired
    private DrugFormulationService drugFormulationService;
    @Autowired
    private DrugUnitService drugUnitService;
    @Autowired
    private DrugDosageUnitService drugDosageUnitService;
    @Autowired
    private DrugPkgUnitService drugPkgUnitService;
            
    
    @RequestMapping("/getStdDictData")
    public Page<DrugDict> getAll(String status,String category,String name,Long mfrId,@RequestParam(name = "taskIds",required = false)String taskIdsStr,
            @RequestParam(defaultValue = "1")int pageNumber,@RequestParam(defaultValue = "100")int pageSize){
        List<Long> taskIds=null;
        if (taskIdsStr!=null && !taskIdsStr.isEmpty()){
           JSONArray jsonArr=JSONArray.fromObject(taskIdsStr);
           taskIds=(List<Long>)JSONArray.toCollection(jsonArr, Long.class);
        }
        
        return this.drugDictService.getAll(status,category,name,mfrId,taskIds,pageNumber,pageSize);
    }
    
    @RequestMapping("/getIds")
    public List<Long> getIds(int from,int to){
        return this.drugDictService.getIds(from,to);
    }
    
    @RequestMapping("/getStdDictsByTaskIds")
    public List<DrugDict> getByTaskIds(@RequestParam("ids")String taskIdsStr){
        JSONArray jsonArr=JSONArray.fromObject(taskIdsStr);
        List<Long> taskIds=(List<Long>)JSONArray.toCollection(jsonArr, Long.class);
        return this.drugDictService.getByTaskIds(taskIds);
    }
    
    @RequestMapping("/modifyStdDict")
    public DrugDict modifyStdDict(@RequestBody DrugDict drugDict){
        drugDict.setStatus(DrugDict.DrugStatus.MODIFIED);
        return this.drugDictService.updateDrugDict(drugDict);
    }
    @RequestMapping("/checkStdDict")
    public DrugDict checkStdDict(@RequestBody DrugDict drugDict){
        drugDict.setStatus(DrugDict.DrugStatus.CHECKED);
        return this.drugDictService.updateDrugDict(drugDict);
    }
    
    /***********DrugCategory***************/
    @RequestMapping("/getDrugCategoryList")
    public List<DrugCategory> getDrugCategoryList(){
        return this.drugCategoryService.getAll();
    }
    
    /************DrugFormulation****************/
    @RequestMapping("/getDrugFormulationList")
    public List<DrugFormulation> getDrugFormulationList(){
        return this.drugFormulationService.getAll();
    }
    
    @RequestMapping("/getDrugUnitList")
    public List<DrugUnit> getDrugUnitList(){
        return this.drugUnitService.getAll();
    }
    
    /************DrugDosageUnit****************/
    @RequestMapping("/getDrugDosageUnitList")
    public List<DrugDosageUnit> getDrugDosageUnitList(){
        return this.drugDosageUnitService.getAll();
    }
    
    /************DrugPkgUnit****************/
    @RequestMapping("/getDrugPkgUnitList")
    public List<DrugPkgUnit> getDrugPkgUnitList(){
        return this.drugPkgUnitService.getAll();
    }
    
}
