/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.view.drugdict;

import com.drug.DrugManageApp;
import com.drug.model.Drug;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author zjj
 */
public class DrugDictOverviewController implements Initializable {

    @FXML
    private TableView<Drug> drugDicTableView;
    @FXML
    private TableColumn<Drug,String> idColumn;
    @FXML
    private TableColumn<Drug,String> cnameColumn;
    @FXML
    private TableColumn<Drug,String> enameColumn;
    @FXML
    private TableColumn<Drug,String> mdseCNameColumn;
    @FXML
    private TableColumn<Drug,String> mdseENameColumn;
    @FXML
    private TableColumn<Drug,String> stdCodeColumn;
    @FXML
    private TableColumn<Drug,String> typeColumn;
    @FXML
    private TableColumn<Drug,String> dosageFormColumn;
    @FXML
    private TableColumn<Drug,String> specColumn;
    @FXML
    private TableColumn<Drug,String> appvNumberColumn;
    @FXML
    private TableColumn<Drug,String> origAppvNumberColumn;
    @FXML
    private TableColumn<Drug,LocalDate> appvDateColumn;
    @FXML
    private TableColumn<Drug,String> mfrNameColumn;
    @FXML
    private TableColumn<Drug,String> mfrAddrColumn;
    @FXML
    private TableColumn<Drug,HBox> optColumn;
    
    @FXML
    private Pagination pagination;
    
    private DrugManageApp drugManageApp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        this.cnameColumn.setCellValueFactory(cellData->cellData.getValue().cnameProperty());
        this.cnameColumn.setCellValueFactory(new PropertyValueFactory<Drug,String>("cname"));
        this.enameColumn.setCellValueFactory(cellData->cellData.getValue().enameProperty());
        this.mdseCNameColumn.setCellValueFactory(cellData->cellData.getValue().mdseCNameProperty());
        this.mdseENameColumn.setCellValueFactory(cellData->cellData.getValue().mdseENameProperty());
        this.stdCodeColumn.setCellValueFactory(cellData->cellData.getValue().stdCodeProperty());
        this.typeColumn.setCellValueFactory(cellData->cellData.getValue().typeProperty());
        this.dosageFormColumn.setCellValueFactory(cellData->cellData.getValue().dosageFormProperty());
        this.specColumn.setCellValueFactory(cellData->cellData.getValue().specProperty());
        this.appvNumberColumn.setCellValueFactory(cellData->cellData.getValue().appvNumberProperty());
        this.origAppvNumberColumn.setCellValueFactory(cellData->cellData.getValue().origAppvNumberProperty());
        this.appvDateColumn.setCellValueFactory(cellData->cellData.getValue().appvDateProperty());
        this.mfrNameColumn.setCellValueFactory(cellData->cellData.getValue().mfrNameProperty());
        this.mfrAddrColumn.setCellValueFactory(cellData->cellData.getValue().mfrAddrProperty());
        
        this.idColumn.setCellFactory(col->{
            TableCell<Drug,String> cell=new TableCell<Drug,String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); 
                    this.setText("");
                    this.setGraphic(null);
                    
                    if (!empty){
                        int rowIndex=this.getIndex()+1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            
            return cell;
        }); 
        
        this.optColumn.setCellFactory(col->{
            TableCell<Drug,HBox> cell=new TableCell<Drug,HBox>(){
                @Override
                protected void updateItem(HBox item, boolean empty) {
                    super.updateItem(item, empty); 
                    this.setText(null);
                    this.setGraphic(null);
                    
                    if (!empty){
                        HBox hbox=new HBox();
                        hbox.setAlignment(Pos.CENTER);
                        Button editBtn=new Button("edit");
                        Button delBtn=new Button("delete");
                        hbox.getChildren().addAll(editBtn,delBtn);
                        
                        this.setGraphic(hbox);
                        editBtn.setOnAction(e->{
//                            Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "内容", new ButtonType("取消",ButtonBar.ButtonData.NO),new ButtonType("确定", ButtonBar.ButtonData.YES));
//                            alert.setTitle("confirm dialog");
////                            alert.setHeaderText("aaaaaa");
//                            alert.setHeaderText(null);
//                            GridPane drugPane=new GridPane();
//                            drugPane.setHgap(5);
//                            drugPane.setVgap(5);
//                            
//                            Label cname=new Label("药品名（中文）");
//                            TextField cname_textField=new TextField();
//                            Label ename=new Label("药品名（英文）");
//                            TextField ename_textField=new TextField();
//                            Label mdseName=new Label("商品名");
//                            TextField mdseName_textField=new TextField();
//                            GridPane.setHgrow(cname_textField, Priority.ALWAYS);
//                            drugPane.add(cname, 0, 0);
//                            drugPane.add(cname_textField, 1, 0);
//                            drugPane.add(ename, 0, 1);
//                            drugPane.add(ename_textField, 1, 1);
//                            drugPane.add(mdseName, 0, 2);
//                            drugPane.add(mdseName_textField, 1,2);
////                            alert.getDialogPane().setExpandableContent(drugPane);
//                            alert.getDialogPane().setContent(drugPane);
//                            alert.initOwner(drugManageApp.getPrimaryStage());
//                            Optional opt=alert.showAndWait();
//                            ButtonType btnType=(ButtonType)opt.get();
//                            if (btnType.getButtonData().equals(ButtonBar.ButtonData.NO)){
//                                
//                            }
//                            else{
//                                
//                            }

                            Dialog drugInfo_dialog=new Dialog();
                            drugInfo_dialog.setTitle("药品详情");
                            drugInfo_dialog.setHeaderText("aaaaa");
                            drugInfo_dialog.initOwner(drugManageApp.getPrimaryStage());
                            ButtonType modifiedBtn=new ButtonType("修订");
                            drugInfo_dialog.getDialogPane().getButtonTypes().addAll(modifiedBtn,ButtonType.CANCEL);
                            Button modifiedBtnNode=(Button)drugInfo_dialog.getDialogPane().lookupButton(modifiedBtn);
                            modifiedBtnNode.setDisable(false);
                            GridPane drugInfo=new GridPane();
                            drugInfo.setPadding(new Insets(2));
                            drugInfo.setHgap(5);
                            drugInfo.setVgap(5);
                            
                            Label cname=new Label("药品名（中文）");
                            TextField cname_textField=new TextField();
                            Label ename=new Label("药品名（英文）");
                            TextField ename_textField=new TextField();
                            Label mdseName=new Label("商品名");
                            TextField mdseName_textField=new TextField();
                            drugInfo.add(cname, 0, 0);
                            drugInfo.add(cname_textField, 1, 0);
                            drugInfo.add(ename, 2, 0);
                            drugInfo.add(ename_textField, 3, 0);
                            
                            drugInfo_dialog.getDialogPane().setContent(drugInfo);
                             Platform.runLater(()->cname_textField.requestFocus());
                            drugInfo_dialog.showAndWait();
//                            cname_textField.requestFocus();
                        });
                        delBtn.setOnAction(e->{
                            //todo  数据库操作--删除数据
                            FilteredList<Drug> filteredData=(FilteredList<Drug>)this.getTableView().getItems();
                            filteredData.getSource().remove(this.getIndex(),this.getIndex()+1);
                        });
                    }
                }
            
            };
            
            return cell;
        });
        
        this.drugDicTableView.setPlaceholder(new Label("无显示数据！"));
        
        this.pagination.getStylesheets().add("com/drug/view/Pagination.css");
//        this.pagination.getStyleClass().add("pagination");
//        this.pagination.setCursor(Cursor.HAND);
        this.pagination.setMaxPageIndicatorCount(8);
        this.pagination.setCurrentPageIndex(0);
        this.pagination.setPageCount(10);
        this.pagination.setPageFactory(pageIndex->{
//            VBox vbox=new VBox(5);
//            int beginIndex=pageIndex*5+1;
//            for (int i=beginIndex;i<beginIndex+5;i++){
//                Label lbl=new Label();
//                lbl.setText("item"+i);
//                vbox.getChildren().add(lbl);
//            }
//            return vbox;
//           
            this.showList(pageIndex);
            return drugDicTableView;
        });
    }    
    
    private void showList(int pageIndex){
        ObservableList<Drug> orgData=this.drugManageApp.getDrugDicList();
        ObservableList<Drug> filteredData=orgData.filtered(drug->{
            boolean flag=orgData.indexOf(drug)==pageIndex;
            return flag;
        });

        this.drugDicTableView.setItems(filteredData);
    }
    
      public void setDrugManageApp(DrugManageApp drugManageApp) {
        this.drugManageApp = drugManageApp;
        this.drugDicTableView.setItems(this.drugManageApp.getDrugDicList());
    }
}
