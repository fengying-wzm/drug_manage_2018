/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.drug.model.Drug;
import com.drug.model.DrugCategory;
import com.drug.model.DrugDict;
import com.drug.model.DrugGeneric;
import com.drug.model.Task;
import com.drug.model.User;
import com.drug.util.HttpMethod;
import com.drug.util.IPaddress;
import com.drug.util.ServiceUtil;
import com.drug.view.LoginOverviewController;
import com.drug.view.MainOverviewController;
import com.drug.view.drugdict.DrugDictMgtOverviewController;
import com.drug.view.task.TaskMgtOverviewController;
import com.drug.view.user.UserMgtOverviewController;
import com.sun.org.apache.bcel.internal.generic.LoadClass;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author zjj
 */
public class DrugManageApp extends Application {
    private Stage primaryStage;
    private AnchorPane loginOverview;
    private BorderPane mainOverview;
    
    private User currUser;
    private ObservableList<Map> userList;
    private ObservableList<Map> roleList;
    private ObservableList<Map> authorityList;
    private ObservableList<Drug> drugDicList;
    
    /**
     * 数据处理状态
     */
    private ObservableList<String> dataProcStatusList;
    private ObservableList<DrugCategory> drugCategoryList;
    private ObservableList<DrugGeneric> drugGenericList;
    private ObservableList<Map> mfrList;
    
    private ObservableList<DrugDict> stdDictData;
    private Map<String,Object> currStdDictPage;
    
    private Map<String,Object> result;

    public DrugManageApp() {
        this.drugDicList=FXCollections.observableArrayList();
        this.drugDicList.add(new Drug("drug1", "中药"));
        this.drugDicList.add(new Drug("drug2", "中药"));
        this.drugDicList.add(new Drug("drug3", "中药"));
        this.drugDicList.add(new Drug("drug4", "中药"));
        
    }
    
   @Override
    public void start(Stage primaryStage) throws Exception{
//        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("DrugManageApp");
        this.primaryStage.getIcons().add(new Image("./resources/images/logo32.png"));
        
        this.initLoginOverview();
//        System.out.println("jsonstr");
//        String jsonstr=HttpMethod.getGETString();
//        System.out.println("jsonstr");
    }
    
    public void initLoginOverview() throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("view/LoginOverview.fxml"));
        this.loginOverview=(AnchorPane)loader.load();
        
        Scene scene=new Scene(this.loginOverview);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        
        LoginOverviewController loginOverviewController=loader.getController();
        loginOverviewController.setDrugManageApp(this);
    }
    
    public void initMainOverview() throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("view/MainOverview.fxml"));
        this.mainOverview=(BorderPane)loader.load();
        
        Scene scene=new Scene(this.mainOverview);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        
        MainOverviewController mainOverviewController=loader.getController();
        mainOverviewController.setDrugManageApp(this);
    }
    
    public void showUserMgtOverview() throws Exception{
        UserMgtOverviewController userMgtOverviewController=(UserMgtOverviewController)this.showMainSubOverview("view/user/UserMgtOverview.fxml");
        userMgtOverviewController.setDrugManageApp(this);
        
        if (this.userList==null){
            this.loadUserList();
        }
        if (this.roleList==null){
            this.loadRoleList();
        }
        if (this.authorityList==null){
            this.loadAuthorityList();
        }
        
        userMgtOverviewController.setUserList(this.userList);
        userMgtOverviewController.setRoleList(this.roleList);
        userMgtOverviewController.setAuthorityList(this.authorityList);
    }
    
    public void showTaskMgtOverview() throws Exception{
        TaskMgtOverviewController taskMgtOverviewController=(TaskMgtOverviewController)this.showMainSubOverview("view/task/TaskMgtOverview.fxml");
        taskMgtOverviewController.setDrugManageApp(this);
        
        if (this.userList==null){
            this.loadUserList();
        }
        if (this.dataProcStatusList==null){
            this.loadDataProcStatus();
        }
        if (this.drugCategoryList==null ){
            this.loadDrugCategory();
        }
        if (this.drugGenericList==null){
            this.loadDrugGeneric();
        }
        if (this.mfrList==null){
            this.loadMfrList();
        }
        
        taskMgtOverviewController.setUserList(this.userList);
        taskMgtOverviewController.setDataProcStatusList(this.dataProcStatusList);
        taskMgtOverviewController.setDrugCategoryList(this.drugCategoryList);
        taskMgtOverviewController.setDrugGenericList(this.drugGenericList);
        taskMgtOverviewController.setMfrList(this.mfrList);
        
        ObservableList<Task> taskData=this.loadTaskData();
        taskMgtOverviewController.setTaskData(taskData);
        
        //        if (this.drugDictData==null){
            this.loadStdDictData();
//        }
        
        taskMgtOverviewController.setStdDictData(this.stdDictData);
        taskMgtOverviewController.setStdDictPage(this.currStdDictPage);
    }
    
    public void showDrugDictMgtOverview() throws Exception{
        DrugDictMgtOverviewController drugDictMgtOverviewController=(DrugDictMgtOverviewController)this.showMainSubOverview("view/drugdict/DrugDictMgtOverview.fxml");
        drugDictMgtOverviewController.setDrugManageApp(this);
        
        if (this.userList==null){
            this.loadUserList();
        }
        if (this.dataProcStatusList==null){
            this.loadDataProcStatus();
        }
        if (this.drugCategoryList==null ){
            this.loadDrugCategory();
        }
        if (this.drugGenericList==null ){
            this.loadDrugGeneric();
        }
        if (this.mfrList==null){
            this.loadMfrList();
        }
        
        drugDictMgtOverviewController.setUserList(this.userList);
        drugDictMgtOverviewController.setTaskData(this.loadTasksByAssignedTo(this.currUser.getId()));
        
        drugDictMgtOverviewController.setDrugCategoryList(this.drugCategoryList);
        drugDictMgtOverviewController.setMfrList(this.mfrList);
        List<Long> taskIds=this.loadTaskIdsByStatusAndAssignedTo(Task.TaskStatus.IN_PROCESS, this.currUser.getId());
        if (taskIds!=null){
            drugDictMgtOverviewController.setDrugDictData(this.loadStdDictsByTaskIds(taskIds));
        }
    }
    
    public void showAboutOverview() throws Exception{
        this.showMainSubOverview("view/AboutOverview.fxml");
    }
    
    private Initializable showMainSubOverview(String relativePath) throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(relativePath));
        Pane subOverview=loader.load();
        
        this.mainOverview.setCenter(subOverview);
        Initializable subOverviewController=loader.getController();
        
        return subOverviewController;
    }
    
    private ObservableList<Drug> loadDrugDicList(){
        return this.drugDicList;
    }
    
    private ObservableList<Map> loadUserList(){
        String mathodName=IPaddress.MATHOD_GET_USER_LIST;
        String resultJson=HttpMethod.getGETString(mathodName);
        JSONArray jsonArray=JSONArray.fromObject(resultJson);
        List<Map> result=(List<Map>)JSONArray.toCollection(jsonArray, Map.class);
        this.userList=FXCollections.observableArrayList(result);
        
        return this.userList;
    }
    
    private ObservableList<Map> loadRoleList(){
        this.roleList=FXCollections.observableArrayList();
        Map<String,Object> obj=new HashMap();
        obj.put("id", 1);
        obj.put("name","管理员" );
        this.roleList.add(obj);
        obj=new HashMap();
        obj.put("id", 2);
        obj.put("name","经理" );
        this.roleList.add(obj);
        obj=new HashMap();
        obj.put("id", 3);
        obj.put("name","高级NDF" );
        this.roleList.add(obj);
        obj=new HashMap();
        obj.put("id", 4);
        obj.put("name","初级NDF" );
        this.roleList.add(obj);
        
        return this.roleList;
    }
    
    private ObservableList<Map> loadAuthorityList(){
        this.authorityList=FXCollections.observableArrayList();
        Map<String,Object> obj=new HashMap();
        obj.put("id", 1);
        obj.put("name","用户管理" );
        this.authorityList.add(obj);
        obj=new HashMap();
        obj.put("id", 2);
        obj.put("name","任务管理" );
        this.authorityList.add(obj);
        obj=new HashMap();
        obj.put("id", 3);
        obj.put("name","字典维护" );
        this.authorityList.add(obj);
        obj=new HashMap();
        obj.put("id", 4);
        obj.put("name","修订" );
        this.authorityList.add(obj);
        obj=new HashMap();
        obj.put("id", 5);
        obj.put("name","复核" );
        this.authorityList.add(obj);
        
        return this.authorityList;
    }
    
    private ObservableList<String> loadDataProcStatus(){
        this.dataProcStatusList=DrugDict.DrugStatus.getDrugStatusList();
        return this.dataProcStatusList;
    }
    
    /**
     * 加载药品生产厂家列表
     */
    private ObservableList<Map> loadMfrList(){
        this.mfrList=FXCollections.observableArrayList();
        Map<String,Object> mfr=new HashMap();
        mfr.put("id", 1);
        mfr.put("name", "安国市天下康制药有限公司");
        mfrList.add(mfr);
        mfr=new HashMap();
        mfr.put("id", 2);
        mfr.put("name", "北京中新制药厂");
        mfrList.add(mfr);
        mfr=new HashMap();
        mfr.put("id", 3);
        mfr.put("name", "长白山制药股份有限公司");
        mfrList.add(mfr);
        mfr=new HashMap();
        mfr.put("id", 4);
        mfr.put("name", "大理药业股份有限公司");
        mfrList.add(mfr);
        mfr=new HashMap();
        mfr.put("id", 5);
        mfr.put("name", "峨眉山通惠制药有限公司");
        mfrList.add(mfr);
        mfr=new HashMap();
        mfr.put("id", 6);
        mfr.put("name", "佛山德众药业有限公司");
        mfrList.add(mfr);
        
        return this.mfrList;
    }
    
    private ObservableList<DrugDict> loadStdDictData(){
        try {
            String mathodName=IPaddress.MATHOD_GET_STD_DICT_DATA;
            String jsonstr=HttpMethod.getGETString(mathodName);
            JSONObject jsonObj=JSONObject.fromObject(jsonstr);
            this.currStdDictPage=(Map<String,Object>)JSONObject.toBean(jsonObj,Map.class);//因暂时未引spring框架，所以Page对象用不了，后续考虑用spring-boot-javafx搭建项目
            JSONArray jsonArray=JSONArray.fromObject(this.currStdDictPage.get("content"));
            List<DrugDict> drugDictData=(List<DrugDict>)JSONArray.toCollection(jsonArray, DrugDict.class);
            this.stdDictData=FXCollections.observableArrayList(drugDictData);
            
            return this.stdDictData;
        } catch (Exception ex) {
            Logger.getLogger(DrugManageApp.class.getName()).log(Level.INFO, IPaddress.MATHOD_GET_STD_DICT_DATA+"方法调用异常！", ex);
        }
        
        return null;
    }
    private ObservableList<DrugDict> loadStdDictsByTaskIds(List<Long> taskIds){
        ObservableList<DrugDict> result=null;
        
        try {
            JSONArray jsonArr=JSONArray.fromObject(taskIds);
            String mathodName=IPaddress.MATHOD_GET_STD_DICTS_BY_TASK_IDS+"?ids="+jsonArr.toString();
            String jsonstr=HttpMethod.getGETString(mathodName);
            JSONArray jsonArray=JSONArray.fromObject(jsonstr);
            List<DrugDict> drugDictData=(List<DrugDict>)JSONArray.toCollection(jsonArray, DrugDict.class);
            result=FXCollections.observableArrayList(drugDictData);
            
        } catch (Exception ex) {
            Logger.getLogger(DrugManageApp.class.getName()).log(Level.INFO, IPaddress.MATHOD_GET_STD_DICT_DATA+"方法调用异常！", ex);
        }
        
        return result;
    }
    
    private ObservableList<DrugCategory> loadDrugCategory() throws Exception{
        String result=HttpMethod.getGETString(IPaddress.MATHOD_GET_DRUG_CATEGORY_LIST);
        JSONArray jsonArray=JSONArray.fromObject(result);
        List<DrugCategory> drugCategoryList=(List<DrugCategory>)JSONArray.toCollection(jsonArray, DrugCategory.class);
        
        this.drugCategoryList=FXCollections.observableArrayList(drugCategoryList);
        
        return this.drugCategoryList;
    }
    private ObservableList<DrugGeneric> loadDrugGeneric() throws Exception{
        String result=HttpMethod.getGETString(IPaddress.MATHOD_GET_DRUG_GENERIC_LIST);
        JSONArray jsonArray=JSONArray.fromObject(result);
        List<DrugGeneric> drugGenericList=(List<DrugGeneric>)JSONArray.toCollection(jsonArray, DrugGeneric.class);
        
        this.drugGenericList=FXCollections.observableArrayList(drugGenericList);
        
        return this.drugGenericList;
    }
    
    private ObservableList<Task> loadTaskData(){
        String resultJson=HttpMethod.getGETString(IPaddress.MATHOD_GET_TASK_DATA);
        JSONArray jsonArray=JSONArray.fromObject(resultJson);
        List<Task> result=(List<Task>)JSONArray.toCollection(jsonArray, Task.class);
        ObservableList<Task> taskData=FXCollections.observableArrayList(result);
        
        return taskData;
    }
    
    private ObservableList<Task> loadTasksByAssignedTo(int id){
        String jsonStr=HttpMethod.getGETString(IPaddress.MATHOD_GET_TASKS_BY_ASSIGNED_TO+"?id="+id);
        JSONArray jsonArr=JSONArray.fromObject(jsonStr);
        List<Task> result=(List<Task>)JSONArray.toCollection(jsonArr, Task.class);
        return FXCollections.observableArrayList(result);
    }
    
    private List<Long> loadTaskIdsByStatusAndAssignedTo(String status,int userId){
        List<Long> taskIds=null;
        
        try {
                String mathodName=IPaddress.MATHOD_GET_TASK_IDS_BY_STATUS_AND_ASSIGNED_TO+"?status="+URLEncoder.encode(status, "UTF-8")+"&id="+userId;
                String jsonStr=HttpMethod.getGETString(mathodName);
                JSONArray jsonArr=JSONArray.fromObject(jsonStr);
                taskIds=(List<Long>)JSONArray.toCollection(jsonArr, Long.class);
               
        } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(DrugManageApp.class.getName()).log(Level.INFO, "查询任务ids出错！", ex);
        }
        
        return taskIds;
    }
    public ObservableList<Map> getUserList(){
        return this.userList;
    }
    public ObservableList<Map> getRoleList(){
        return this.roleList;
    }
    public ObservableList<Map> getAuthorityList(){
        return this.authorityList;
    }
    public ObservableList<Drug> getDrugDicList() {
        return drugDicList;
    }
     
    public Stage getPrimaryStage(){
        return this.primaryStage;
    }
    
    public User getCurrentUser(){
        return this.currUser;
    }
    
    public void setCurrentUser(User user){
        this.currUser=user;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
