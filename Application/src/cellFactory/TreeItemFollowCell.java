/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellFactory;

import business_logic.Category;
import business_logic.Follow;
import business_logic.Repository;
import javafx.scene.control.TreeCell;

/**
 *
 * @author Adrien
 */
public class TreeItemFollowCell extends TreeCell<Follow> {

    @Override
    public void updateItem(Follow item, boolean isEmpty){
        super.updateItem(item, isEmpty);
        if(isEmpty){
            textProperty().unbind();
            setText(null);
            setGraphic(null);
            return;
        }

        if(item instanceof Category)
            textProperty().bind(item.nameProperty());

        if(item instanceof Repository){      	
            textProperty().bind(item.nameProperty());
            setGraphic(null);
        }
    }   
}
