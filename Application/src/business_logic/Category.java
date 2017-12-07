/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adrien
 */
public class Category extends Follow{
    
    private final ListProperty<Follow> listOfFollows = new SimpleListProperty<>();   
        public final ObservableList<Follow> getListOfFollows() { return listOfFollows.get(); }
        public final void setListOfFollows(ObservableList<Follow> value) { listOfFollows.set(value); }
        
    public Category(String name) {
        super(name);
        setListOfFollows(FXCollections.<Follow>observableArrayList());
    }
    
    @Override
    public void AddFollow(Follow follow) {
        listOfFollows.add(follow);
    }

    @Override
    public void DeleteFollow(Follow follow) {
        listOfFollows.remove(follow);
    }
    
    @Override
    public ObservableList<Follow> getChildren() {
        return FXCollections.<Follow>observableArrayList();
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
