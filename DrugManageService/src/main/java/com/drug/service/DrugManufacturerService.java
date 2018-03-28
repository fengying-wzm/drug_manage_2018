/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.drug.dao.IDrugManufacturerDao;
import com.drug.entity.Manufacturer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author zjj
 */
@Service
public class DrugManufacturerService {
    @Autowired
    private IDrugManufacturerDao drugMfrDao;
    
    public List<Manufacturer> getAll(){
        return this.drugMfrDao.findAll();
    }
    
    public Page<Manufacturer> getDrugMfrData(String name,String countryCode,int pageNumber,int pageSize){
        Pageable page=new PageRequest(pageNumber-1, pageSize);
        Specification spec=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                List<Predicate> list=new ArrayList<Predicate>();
                
                if (name!=null){
                    list.add(cb.like(root.get(("name")), "%"+name+"%"));
                }
                
                if (countryCode!=null){
                    list.add(cb.equal(root.get("countryCode"), root));
                }
                
                Predicate[] prdcts=new Predicate[list.size()];
                return cb.and(list.toArray(prdcts));
            }
        };
                
        return this.drugMfrDao.findAll(spec, page);
    }
    
    public Manufacturer addDrugMfr(Manufacturer mfr){
        return this.drugMfrDao.save(mfr);
    }
    public Manufacturer updateDrugMfr(Manufacturer mfr){
        return this.drugMfrDao.save(mfr);
    }
    
    public void deleteDrugMfr(long id){
        this.drugMfrDao.delete(id);
    }
}
