/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellFactory;

import business_logic.Follow;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

/**
 *
 * @author Adrien
 */
public class TreeItemFollowFactory implements Callback<TreeView<Follow>,TreeCell<Follow>> {

    @Override
    public TreeCell<Follow> call(TreeView<Follow> param) {
       return new TreeItemFollowCell();
    }    
}
