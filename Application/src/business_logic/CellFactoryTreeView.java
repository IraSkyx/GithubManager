/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import javafx.application.Application;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

/**
 *
 * @author fsimo
 */
public class CellFactoryTreeView extends FactoryTreeView{
    private final Image categoryIcon = new Image(getClass().getResourceAsStream("categIcon.png"));
        private final Image repositoryIcon = new Image(getClass().getResourceAsStream("repoIcon.png"));
    
    public TreeItem createTreeViewCell(Follow follow){
        if(follow instanceof Category){
            TreeItem<Category> TI = new TreeItem<Category>((Category) follow);
            return TI;
        }
        else {
            TreeItem<Repository> TI = new TreeItem<>((Repository) follow); 
            return TI;
        }
    }    
}
