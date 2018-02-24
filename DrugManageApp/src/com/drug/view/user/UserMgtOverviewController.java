/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.user;

import com.drug.DrugManageApp;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import com.drug.model.User;
import com.drug.util.HttpMethod;
import com.drug.util.IPaddress;
import com.drug.util.StringListCell;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class UserMgtOverviewController implements Initializable {
    public static final String ADD="add";
    public static final String Edit="edit";
    public static final String VIEW="view";
    
    @FXML
    private ComboBox user_cBox;
    @FXML
    private ComboBox role_cBox;
    @FXML
    private ComboBox authority_cBox;
    @FXML
    private Button queryBtn;
    @FXML
    private Button addBtn;
    
    @FXML
    private TableView userTableView; 
    @FXML
    private TableColumn<User,String> nameColumn;
    @FXML
    private TableColumn<User,String> pwdColumn;
    @FXML
    private TableColumn<User,String> roleColumn;
    @FXML
    private TableColumn<User,String> authorityColumn;
    @FXML
    private TableColumn<User,HBox> optColumn;
    
    private DrugManageApp drugManageApp;
    
    private ObservableList<Map> roleList;
    private ObservableList<Map> authorityList;
    private ObservableList<User> userData;   
    
    public ObservableList<Map> userList;
    
    @FXML
    private ObservableList<User> getUsers(){
        String jsonStr=HttpMethod.getGETString(IPaddress.MATHOD_GET_USER_DATA);
        JSONArray jsonArr=JSONArray.fromObject(jsonStr);
        List<User> userData=(List<User>)JSONArray.toCollection(jsonArr, User.class);
        
        return FXCollections.observableArrayList(userData);
    }
    
    @FXML
    private void addUser() throws Exception{
        this.popUpUserEditDialog("添加用户", ADD, null);
    }
    
    private void popUpUserEditDialog(String title,String type,User user) throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(this.getClass().getResource("UserEditView.fxml"));
        GridPane userEditView=loader.load();
        UserEditViewController userEditViewController=loader.getController();
        if (!type.equals(ADD)){
            userEditViewController.setUser(user);
        }
        userEditViewController.setRoleList(this.roleList);
        Dialog userEditDialog=new Dialog();
        userEditDialog.setTitle(title);
        
        String type_cn=null;
        switch(type){
            case "view":
                type_cn="确定";
                break;
            case "edit":
                type_cn="修改";
                break;
            case "add":
                type_cn="添加";
                break;
                
        }
        ButtonType okBtn=new ButtonType(type_cn, ButtonBar.ButtonData.OK_DONE);
        userEditDialog.getDialogPane().getButtonTypes().addAll(okBtn,ButtonType.CANCEL);
        userEditDialog.getDialogPane().setContent(userEditView);
        if (!type.equals(VIEW)){
            userEditDialog.setResultConverter(btnType->{
                if (btnType==okBtn){
                    User auser=new User();
                    auser.setName(userEditViewController.userField.getText());
                    auser.setPassword(userEditViewController.pwdField.getText());
                    auser.setRoleId((int)userEditViewController.role_cbox.getSelectionModel().getSelectedItem().get("id"));
                    
                    return auser;
                }

                return null;
            });
        }
        
        Optional<User> result=userEditDialog.showAndWait();
        if (result!=null && result.get()!=null){
            if (type.equals(ADD)){
                Map<String,String> map=new HashMap<String,String>();
                JSONObject jsonObj=JSONObject.fromObject(result.get());
                map.put("user",jsonObj.toString());
                
//                if (){
                    String resultStr=(String)HttpMethod.sendPOSTString((HashMap<String, String>)map, "UTF-8");
                    jsonObj=JSONObject.fromObject(resultStr);
                    User newUser=(User)JSONObject.toBean(jsonObj, User.class);
                    
                    this.userData.add(newUser);
//                }
               
            }
            
            if (type.equals(Edit)){
                //todo
                user.setName(result.get().getName());
                user.setPassword(result.get().getPassword());
                user.setRoleId(result.get().getRoleId());
                
                this.userTableView.refresh();
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        this.pwdColumn.setCellValueFactory(new PropertyValueFactory("password"));
        this.optColumn.setCellFactory(col->{
            TableCell<User,HBox> tableCell=new TableCell<User,HBox>(){
                @Override
                protected void updateItem(HBox item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    
                    if (!empty){
                        HBox hbox=new HBox();
                        hbox.setAlignment(Pos.CENTER);
                        hbox.setSpacing(2);
                        Button editBtn=new Button("编辑...");
                        Button delBtn=new Button("删除");
                        editBtn.setOnAction(e->{
                            try {
                                User user=(User)this.getTableRow().getItem();
                                popUpUserEditDialog("编辑用户", "edit",user);
                            } catch (Exception ex) {
                                Logger.getLogger(UserMgtOverviewController.class.getName()).log(Level.INFO, "UserEditView弹出窗口异常！", ex);
                            }
                        });
                        
                        delBtn.setOnAction(e->{
                        //todo 调后台操作，删除数据
                            this.getTableView().getItems().remove(this.getIndex(), this.getIndex()+1);
                        });
                        
                        hbox.getChildren().addAll(editBtn,delBtn);
                        
                        this.setGraphic(hbox);
                    }
                }
            };
            
            return tableCell;
        });
        

//            String jsonstr=HttpMethod.getGETString();
        this.userData=this.getUsers();

        this.user_cBox.setButtonCell(new StringListCell());
        this.role_cBox.setButtonCell(new StringListCell());
        this.authority_cBox.setButtonCell(new StringListCell());

        Callback<ListView,ListCell> cbox_cellFactory=(view)->{
            ListCell listCell=new StringListCell();
            return listCell;
        };
        this.user_cBox.setCellFactory(cbox_cellFactory);
        this.role_cBox.setCellFactory(cbox_cellFactory);
        this.authority_cBox.setCellFactory(cbox_cellFactory);

        this.userTableView.setItems(this.userData);
    } 
    
    public void setDrugManageApp(DrugManageApp drugManageApp) {
        this.drugManageApp = drugManageApp;
    }
    
    public void setUserList(ObservableList<Map> userList){
        this.userList=userList;
        this.user_cBox.setItems(this.userList);  
    }
    public void setRoleList(ObservableList<Map> roleList){
        this.roleList=roleList;
        this.roleColumn.setCellValueFactory(cellData->{
            int roleId=cellData.getValue().getRoleId();
            String roleName=null;
            for (Map role : this.roleList) {
                if ((int)role.get("id")==roleId){
                    roleName=(String)role.get("name");
                    break;
                }
            }
            
            return new SimpleStringProperty(roleName);
        });
        
        this.role_cBox.setItems(this.roleList);
    }
    public void setAuthorityList(ObservableList<Map> authorityList){
//        this.authorityList=authorityList;
//        
//        this.authorityColumn.setCellValueFactory(cellData->{
//            int authorityId=cellData.getValue().getAuthorityId();
//            String authorityName=null;
//            for (Map authority : this.authorityList) {
//                if ((int)authority.get("id")==authorityId){
//                    authorityName=(String)authority.get("name");
//                    break;
//                }
//            }
//            
//            return new SimpleStringProperty(authorityName);
//        });
//        
//         this.authority_cBox.setItems(this.authorityList);
    }
    
}
