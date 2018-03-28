/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.dao;

import com.drug.entity.Manufacturer;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zjj
 */
@Repository
public interface IDrugManufacturerDao extends JpaRepository<Manufacturer, Long>,JpaSpecificationExecutor<Manufacturer>{
    public List<Manufacturer> findByNameLike(String name);
    public List<Manufacturer> findByCountryCode(String code);
    public List<Manufacturer> findByNameLikeAndCountryCode(String name,String countryCode);
}
