/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.dao.IDrugGenericDao;
import com.drug.entity.DrugGeneric;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DrugGenericService {
    @Autowired
    private IDrugGenericDao drugGenericDao;
    
    public List<DrugGeneric> getAll(){
        return this.drugGenericDao.findAll();
    }
    
    public Page<DrugGeneric> getDrugGenericData(String name,int pageNumber,int pageSize){
        Pageable page=new PageRequest(pageNumber-1, pageSize);
        Specification<DrugGeneric> spec=new Specification<DrugGeneric>() {
            @Override
            public Predicate toPredicate(Root<DrugGeneric> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> list=new ArrayList<Predicate>();
                if (name!=null){
                    List<Predicate> subList=new ArrayList<Predicate>();
                    subList.add(cb.like(root.get("genericCName"), "%"+name+"%"));
                    subList.add(cb.like(root.get("genericEName"), "%"+name+"%"));
                    
                    Predicate[] p=new Predicate[subList.size()];
                    list.add(cb.or(subList.toArray(p)));
                }
                
                Predicate[] prdct=new Predicate[list.size()];
                return cb.and(list.toArray(prdct));
            }
        };
                
        return this.drugGenericDao.findAll(spec, page);
    }
    
    public DrugGeneric addDrugGeneric(DrugGeneric generic){
        return this.drugGenericDao.save(generic);
    }
    public DrugGeneric updateDrugGeneric(DrugGeneric generic){
        return this.drugGenericDao.save(generic);
    }
    
    public void deleteDrugGeneric(long id){
        this.drugGenericDao.delete(id);
    }
}
