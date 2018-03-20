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
import java.util.ArrayList;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class DrugDictService {
    @Autowired
    private IDrugDictDao drugDictDao;
    
    public Page<DrugDict> getAll(Pageable page){
        return this.drugDictDao.findAll(page);
    }
    
    public Page<DrugDict> getAll(String status,String category,String name,Long mfrId,List<Long> taskIds,int pageNumber,int pageSize){
        Specification<DrugDict> spec=new Specification<DrugDict>(){
            @Override
            public Predicate toPredicate(Root<DrugDict> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> list=new ArrayList<Predicate>();
                if (status!=null && !status.isEmpty()){
                    list.add(cb.equal(root.get("status").as(String.class), status));
                }
                
                if (category!=null && !category.isEmpty()){
                    list.add(cb.equal(root.get("category").as(String.class), category));
                }
                
                if (name!=null && !name.isEmpty()){
                    List<Predicate> subList=new ArrayList<Predicate>();
                    subList.add(cb.like(root.get("genericCName"), "%"+name+"%"));
                    subList.add(cb.like(root.get("genericEName"), "%"+name+"%"));
                    Predicate[] p=new Predicate[subList.size()];
                    Predicate prdct=cb.or(subList.toArray(p));
                    list.add(prdct);
                }
                
                if (mfrId!=null && mfrId>0){
                    list.add(cb.equal(root.get("mfrId"), mfrId));
                }
                
                if (taskIds!=null && taskIds.size()>0){
                    In<Long> in=cb.in(root.get("taskId"));
                    taskIds.forEach(taskId->in.value(taskId));
                    list.add(in);
                }
                
                Predicate[] prdcts=new Predicate[list.size()];
                return cb.and(list.toArray(prdcts));
            }
        };
        
        Pageable page=new PageRequest(pageNumber-1, pageSize);
         
        return this.drugDictDao.findAll(spec,page);
    }
    
    public List<Long> getIds(int from,int to){
        return this.drugDictDao.findIds().subList(from-1, to);
    }
    
    public List<DrugDict> getByTaskIds(List<Long> taskIds){
        return this.drugDictDao.findByTaskIdIn(taskIds);
    }
    
    public DrugDict getById(long id){
        return this.drugDictDao.findById(id);
    }
    
    public DrugDict updateDrugDict(DrugDict drugDict){
        return this.drugDictDao.save(drugDict);
    }
    
}
