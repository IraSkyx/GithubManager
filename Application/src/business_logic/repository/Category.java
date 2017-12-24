package business_logic.repository;

import java.util.Collections;
import java.util.Comparator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adrien
 */
public class Category extends Follow {
    
    private final StringProperty name = new SimpleStringProperty();
    @Override public StringProperty nameProperty() {return name;}
    
    private final ListProperty<Follow> listOfFollows = new SimpleListProperty<>();   
        public final ObservableList<Follow> getListOfFollows() { return listOfFollows.get(); }
        public final void setListOfFollows(ObservableList<Follow> value) { listOfFollows.set(value); }

    public Category(String title) {       
        setName(title);
        setListOfFollows(FXCollections.<Follow>observableArrayList());
    }
    
    public boolean contains(String name){
        boolean res=false;
        for(Follow follow : listOfFollows){
            if(follow.getName().equals(name))
                return true;
            if(follow instanceof Category)
                res&=((Category)follow).contains(name);
        }
        return res;
    }
    
    @Override public void addFollow(Follow follow) {
        listOfFollows.add(follow);      
        Collections.sort(listOfFollows, (Follow f1, Follow f2) -> {
            if (f1 instanceof Repository && f2 instanceof Category) return 1;
            if (f1 instanceof Category && f2 instanceof Repository) return -1;
            return 0;
        });
    }

    @Override public void deleteFollow(Follow follow) {listOfFollows.remove(follow);}    
    
    @Override public String toString() {
        return getName();
    }
}
