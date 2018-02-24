/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.controller;

import com.drug.entity.User;
import com.drug.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zjj
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping("/login")
    public User login(@RequestParam("userName")String name,@RequestParam("password")String pwd){
        return this.userService.findByNameAndPassword(name, pwd);
    }
    
    @RequestMapping("/getUserData")
    public List<User> getAll(){
        return this.userService.findAll();
    }
    
    @RequestMapping("/addUser")
    public User addUser(@RequestParam("user")String userStr){
        
        User user=new User();
        return this.userService.addUser(user);
    }
    
    @RequestMapping("/deleteUser")
    public void deleteUser(@RequestParam("userId")int id){
        this.userService.deleteUser(id);
    }
}
