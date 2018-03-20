/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.dao;

import com.drug.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zjj
 */
@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
    public User findByNameAndPassword(String name,String pwd);
    
    public List<User> findByName(String name);
    public List<User> findByRoleId(int roleId);
    public List<User> findByNameAndRoleId(String name,int roleId);
    
    @Query("select distinct new User(u.id, u.name) from User u")
    public List<User> getUserList(); 
}
