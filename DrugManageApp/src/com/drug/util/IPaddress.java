/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.util;

import java.net.URLEncoder;

/**
 *
 * @author zjj
 */
public class IPaddress {
    public static final String IP_get_SITE = "http://localhost:8080/";         //接受信息的IP地址
    
    /******************纬度字典***********************/
    public static final String MATHOD_GET_USER_LIST="getUserList";
    
    public static final String MATHOD_LOGIN="login"; //登录
    
    /*用户管理*/
    public static final String MATHOD_GET_USER_DATA="getUserData";
    public static final String MATHOD_GET_USERS_BY_NAME_AND_ROLE="getByNameAndRole";
    public static final String MATHOD_DELETE_USER="deleteUser";
    
    /*药品字典*/
    public static final String MATHOD_GET_STD_DICT_DATA = "getStdDictData";         //获取药品标准字典数据
    public static final String MATHOD_GET_STD_DICT_IDS= "getIds";//"getStdDictIds";         //获取药品标准字典id
    
    public static final String MATHOD_GET_DRUG_CATEGORY_LIST = "getDrugCategoryList";         //获取药品类型
    public static final String MATHOD_GET_DRUG_GENERIC_LIST = "getDrugGenericList";         //获取药品通用名
    
    /*任务管理*/
    public static final String MATHOD_GET_TASK_DATA="getTaskData";
    public static final String MATHOD_GET_TASKS_BY_TYPE_AND_STATUS_AND_ASSIGNEDTO="getTasksByTypeAndStatusAndAssignedTo";
    public static final String MATHOD_ADD_TASK="addTask";
    public static final String MATHOD_UPDATE_TASK="updateTask";
    public static final String MATHOD_DELETE_TASK="deleteTask";
    /*药品标准字典维护*/
    public static final String MATHOD_GET_TASKS_BY_ASSIGNED_TO= "getTasksByAssignedTo"; 
    public static final String MATHOD_GET_TASK_IDS_BY_STATUS_AND_ASSIGNED_TO= "getTaskIdsByStatusAndAssignedTo"; 
    public static final String MATHOD_START_TASK="startTask";
    public static final String MATHOD_COMPLETE_TASK="completeTask";
    
    public static final String MATHOD_MODIFY_STD_DICT="modifyStdDict";
    public static final String MATHOD_CHECK_STD_DICT="checkStdDict";
    
    public static final String MATHOD_GET_STD_DICTS_BY_TASK_IDS="getStdDictsByTaskIds";
    
    public static final String MATHOD_GET_DRUG_FORMULATION_LIST="getDrugFormulationList";
    public static final String MATHOD_GET_DRUG_UNIT_LIST="getDrugUnitList";
    public static final String MATHOD_GET_DRUG_DOSAGE_UNIT_LIST="getDrugDosageUnitList";
    public static final String MATHOD_GET_DRUG_PKG_UNIT_LIST="getDrugPkgUnitList";
    
    
 /*******************************************/   
    public static final String IP_send_SITE = "http://localhost:8080/";    //发送信息的IP地址
    
    public static final String MATHOD_ADD_USER="addUser";
    
}