package business_logic.repository;

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
    
    @Override public void addFollow(Follow follow) {listOfFollows.add(follow);}

    @Override public void deleteFollow(Follow follow) {listOfFollows.remove(follow);}    
    
    @Override public String toString() {
        String toString="";
        for(Follow follow : listOfFollows)
            toString+= (follow instanceof Category ? follow.toString() : follow.getName()) +"\n";
        return toString;
    }
}
