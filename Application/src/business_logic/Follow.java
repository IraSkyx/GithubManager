/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/*
* Abstract Class Follow : mother class in a composite pattern to make a treelike structure
*/

/**
 *
 * @author Adrien
 */
public abstract class Follow {
    
    private final StringProperty name = new SimpleStringProperty();
        public String getName() {return name.get();}
        public void setName(String name) {this.name.set(name);}
        public StringProperty nameProperty(){return name;}
    
    public Follow(String name){
        setName(name);
    }
        
    public void AddFollow(Follow follow) {
        throw new UnsupportedOperationException("OperationNotSupportedException");
    }

    public void DeleteFollow(Follow follow) {
        throw new UnsupportedOperationException("OperationNotSupportedException");
    }

    public ObservableList<Follow> getChildren() {
        throw new UnsupportedOperationException("OperationNotSupportedException");
    }
}
