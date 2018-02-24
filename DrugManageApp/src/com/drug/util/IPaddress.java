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
    public static final String IP_get_SITE = "http://localhost:8080";         //接受信息的IP地址
    
    public static final String MATHOD_LOGIN="/login"; //登录
    public static final String MATHOD_GET_USER_DATA="/getUserData";
    public static final String MATHOD_DELETE_USER="/deleteUser";
    
    public static final String MATHOD_GET_DRUG_CATEGORY_LIST = "/getDrugCategoryList";         //获取药品类型
    public static final String MATHOD_GET_TOP10_BY_GENERIC_CNAME = "/getTop10ByGenericCName"; 
    public static final String MATHOD_GET_BY_MFR_ID = "/getByMfrId?id=2401"; 
    
    /*字典维护*/
    public static final String MATHOD_GET_TASKS_BY_ASSIGNED_TO= "/getTasksByAssignedTo"; 
    
 /*******************************************/   
    public static final String IP_send_SITE = "http://localhost:8080";    //发送信息的IP地址
    
    public static final String MATHOD_ADD_USER="/addUser";
}