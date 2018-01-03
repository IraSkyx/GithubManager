package business_logic.repository;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Predicate;
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
public class Category extends Follow implements CompositeUtils<Follow>, Serializable {

    private transient final StringProperty name = new SimpleStringProperty();
        @Override public String getName() {return name.get();}
        @Override public void setName(String value) {name.set(value);}
        public StringProperty nameProperty() {return name;}

    private final ListProperty<Follow> listOfFollows = new SimpleListProperty<>();
        public final ObservableList<Follow> getListOfFollows() { return listOfFollows.get(); }
        public final void setListOfFollows(ObservableList<Follow> value) { listOfFollows.set(value); }
        public ListProperty listOfFollowsProperty(){return listOfFollows;};

    public Category() {
        setListOfFollows(FXCollections.<Follow>observableArrayList());
    }

    public Category(String name) {
        setName(name);
        setListOfFollows(FXCollections.<Follow>observableArrayList());
    }
    
    @Override
    public boolean contains(Predicate<? super Follow> action) {
        Objects.requireNonNull(action);
        boolean res = false;
        for(Follow follow : listOfFollows){
            if(action.test(follow))
                return true;
            if(follow instanceof Category)
                res&=((Category)follow).contains(action);
        }
        return res;
    }

    @Override
    public void addFollow(Follow follow) {
        if(((Category)getRoot()).contains(x -> x.equals(follow)))
            return;   
        
        follow.setParent(this);
        listOfFollows.add(follow);
        
        if(listOfFollows != null)
            Collections.sort(listOfFollows, (Follow f1, Follow f2) -> {
                if (f1 instanceof Repository && f2 instanceof Category) return 1;
                if (f1 instanceof Category && f2 instanceof Repository) return -1;
                return 0;
            });
    }

    @Override
    public void deleteFollow(Follow follow) {
        listOfFollows.remove(follow);
    }

    @Override public String toString() {
        return getName();
    }  
}
