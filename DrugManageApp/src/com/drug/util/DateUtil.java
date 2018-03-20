/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.util;

import java.time.LocalDateTime;

/**
 * 日期时间公共类
 * @author zjj
 */
public class DateUtil {
    
    /*
     * @param dateTimeStr 格式为"yyyy-MM-dd HH:mm:ss"的日期时间字符串
     */
    public static LocalDateTime toLocalDateTime(String dateTimeStr){
        String[] strs=dateTimeStr.split(" ");
        String dateStr=strs[0];
        String timeStr=strs[1];
        String[] dateStrs=dateStr.split("-");
        String[] timeStrs=timeStr.split(":");
        
        int year=Integer.valueOf(dateStrs[0]);
        int month=Integer.valueOf(dateStrs[1]);
        int day=Integer.valueOf(dateStrs[2]);
        int hour=Integer.valueOf(timeStrs[0]);
        int minute=Integer.valueOf(timeStrs[1]);
        int second=Integer.valueOf(timeStrs[2]);
        
        return LocalDateTime.of(year,month,day,hour,minute,second);
    }
}
