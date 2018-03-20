/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.service;

import com.drug.dao.IUserDao;
import com.drug.entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public List<Map<String,Object>> getUserList(){
        List<User> userList=this.userDao.getUserList();
        List<Map<String,Object>> userMapList=new ArrayList();
        userList.forEach(user->{
            Map<String,Object> userMap=new HashMap();
            userMap.put("id", user.getId());
            userMap.put("name", user.getName());
            userMapList.add(userMap);
        });
        
        return userMapList;
    }
    public List<User> getAll(){
        return (List<User>)this.userDao.findAll();
    }
    
    public List<User> getByNameAndRoleId(String name,int roleId){
        if (name!=null && !name.isEmpty() && roleId>0){
            return this.userDao.findByNameAndRoleId(name, roleId);
        }else if (name!=null && !name.isEmpty()){
            return this.userDao.findByName(name);
        }else if(roleId>0){
            return this.userDao.findByRoleId(roleId);
        }else{
            return this.getAll();
        }         
        
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
