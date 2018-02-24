/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drug.util;

import com.drug.model.IModel;
import java.util.Map;
import javafx.scene.control.ListCell;

/**
 *
 * @author zjj
 * @param <Object>
 */
public class StringListCell<Object> extends ListCell<Object>{
        @Override
        protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty); 
            this.setText(null);
            this.setGraphic(null);

            if (!empty){
                System.out.println(item.getClass());
                if (item instanceof Map){
                    this.setText((String)((Map)item).get("name"));
                }
                else if (item instanceof IModel){
                    this.setText(((IModel) item).getName());
                }
                else{
                    this.setText((String)item);
                }
            }
        }
    }
