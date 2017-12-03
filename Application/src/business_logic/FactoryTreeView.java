/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import javafx.application.Application;
import javafx.scene.control.TreeItem;

/**
 *
 * @author fsimo
 */
public abstract class FactoryTreeView{
    public abstract TreeItem createTreeViewCell(Follow follow);
}
