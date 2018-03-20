/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.controller;

import com.drug.entity.User;
import com.drug.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @RequestMapping("/getUserList")
    public List<Map<String,Object>> getUserList(){
        return this.userService.getUserList();
    }
    
    @RequestMapping("/getUserData")
    public List<User> getAll(){
        return this.userService.getAll();
    }
    
    @RequestMapping("/getByNameAndRole")
    public List<User> getByNameAndRole(@RequestParam String name,@RequestParam int roleId){
        return this.userService.getByNameAndRoleId(name, roleId);
    }
    
    @RequestMapping("/addUser")
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }
/*    public User addUser(String name, String password,int roleId){
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        user.setRoleId(roleId);
        return this.userService.addUser(user);
    }
*/
    @RequestMapping("/deleteUser")
    public void deleteUser(@RequestParam("id")int id){
        this.userService.deleteUser(id);
    }
}
