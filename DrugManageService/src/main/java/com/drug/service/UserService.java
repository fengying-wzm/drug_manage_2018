/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.dao.IUserDao;
import com.drug.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zjj
 */
@Service
public class UserService {
    @Autowired
    private IUserDao userDao;
    
    public List<User> findAll(){
        return (List<User>)this.userDao.findAll();
    }
    
    public User findByNameAndPassword(String name,String pwd){
        return this.userDao.findByNameAndPassword(name, pwd);
    }
    
    public User addUser(User user){
        return this.userDao.save(user);
    }
    
    public void deleteUser(int id){
        this.userDao.delete(id);
    }
}
