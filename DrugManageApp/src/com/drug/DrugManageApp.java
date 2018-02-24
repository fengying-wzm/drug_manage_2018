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
import com.drug.model.Task;
import com.drug.model.User;
import com.drug.util.HttpMethod;
import com.drug.util.IPaddress;
import com.drug.view.drugdict.DrugDictOverviewController;
import com.drug.view.LoginOverviewController;
import com.drug.view.MainOverviewController;
import com.drug.view.drugdict.DrugDictMgtOverviewController;
import com.drug.view.task.TaskMgtOverviewController;
import com.drug.view.user.UserMgtOverviewController;
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
    private ObservableList<Map> mfrList;
    private ObservableList<DrugDict> drugDictData;
    
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
        if (this.mfrList==null){
            this.loadMfrList();
        }
        
        taskMgtOverviewController.setUserList(this.userList);
        taskMgtOverviewController.setDataProcStatusList(this.dataProcStatusList);
        taskMgtOverviewController.setDrugCategoryList(drugCategoryList);
        taskMgtOverviewController.setMfrList(this.mfrList);
        
        ObservableList<Task> taskData=this.loadTaskData();
        taskMgtOverviewController.setTaskData(taskData);
        
        //        if (this.drugDictData==null){
            this.loadDrugDictData();
//        }
        
        taskMgtOverviewController.setDrugDictData(this.drugDictData);
    }
    
    public void showDrugDictMgtOverview() throws Exception{
        DrugDictMgtOverviewController drugDictMgtOverviewController=(DrugDictMgtOverviewController)this.showMainSubOverview("view/drugdict/DrugDictMgtOverview.fxml");
        drugDictMgtOverviewController.setDrugManageApp(this);
        
        this.loadTasksByAssignedTo(this.currUser.getId());
        
//        String jsonstr=HttpMethod.getGETString();
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
        this.userList=FXCollections.observableArrayList();
        Map<String,Object> obj=new HashMap();
        obj.put("id", 1);
        obj.put("name","a" );
        this.userList.add(obj);
        obj=new HashMap();
        obj.put("id", 2);
        obj.put("name","b" );
        this.userList.add(obj);
        obj=new HashMap();
        obj.put("id", 3);
        obj.put("name","c" );
        this.userList.add(obj);
        
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
        this.dataProcStatusList=FXCollections.observableArrayList(
            "未分配",
            "已分配（修订）",
            "修订完成",
            "已分配（复核）",
            "复核完成"
        );
        
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
    
    private ObservableList<DrugDict> loadDrugDictData(){
        try {
            String url=IPaddress.MATHOD_GET_TOP10_BY_GENERIC_CNAME+"?name="+URLEncoder.encode("阿莫西林分散片", "UTF-8");
            String jsonstr=HttpMethod.getGETString(url);
            JSONArray jsonArray=JSONArray.fromObject(jsonstr);
            List<DrugDict> drugDictData=(List<DrugDict>)JSONArray.toCollection(jsonArray, DrugDict.class);
            this.drugDictData=FXCollections.observableArrayList(drugDictData);
            
            return this.drugDictData;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DrugManageApp.class.getName()).log(Level.INFO, IPaddress.MATHOD_GET_TOP10_BY_GENERIC_CNAME+"方法调用异常！", ex);
        }
        
        return null;
    }
    
    private ObservableList<DrugCategory> loadDrugCategory() throws Exception{
        String result=HttpMethod.getGETString(IPaddress.MATHOD_GET_DRUG_CATEGORY_LIST);
        JSONArray jsonArray=JSONArray.fromObject(result);
        List<DrugCategory> drugCategoryList=(List<DrugCategory>)JSONArray.toCollection(jsonArray, DrugCategory.class);
        
        this.drugCategoryList=FXCollections.observableArrayList(drugCategoryList);
        
        return this.drugCategoryList;
    }
    
    private ObservableList<Task> loadTaskData(){
        ObservableList<Task> result=FXCollections.observableArrayList();
        result.addAll(
            new Task(Task.TaskType.MODIFY_DICT, 1000, 2),
            new Task(Task.TaskType.CHECK_DICT, 1000, 2),
            new Task(Task.TaskType.MODIFY_DICT, 1000, 3),
            new Task(Task.TaskType.MODIFY_DICT, 1000, 3),
            new Task(Task.TaskType.CHECK_DICT, 1000, 3)
        );
        
        return result;
    }
    
    private ObservableList<Task> loadTasksByAssignedTo(int id){
        String jsonStr=HttpMethod.getGETString(IPaddress.MATHOD_GET_TASKS_BY_ASSIGNED_TO+"?id="+id);
        JSONArray jsonArr=JSONArray.fromObject(jsonStr);
        List<Task> result=(List<Task>)JSONArray.toCollection(jsonArr,Task.class);
        return FXCollections.observableArrayList(result);
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
    
    public void setCurrentUser(User user){
        this.currUser=user;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
