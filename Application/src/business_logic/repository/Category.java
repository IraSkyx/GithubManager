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
 * Business class for Repository management : Category : child class in a composite pattern to make a treelike structure
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class Category extends Follow implements CompositeUtils<Follow>, Serializable {

    /**
     * StringProperty of the name of the Category
     */
    private transient final StringProperty name = new SimpleStringProperty();
        /**
         * Get name of the StringProperty 
         * 
         * @return a String which corresponds to the name in the Stringproperty 
         */
        @Override public String getName() {return name.get();}
        /**
         * Set name in the StringProperty 
         * 
         * @param value the String to set 
         */
        @Override public void setName(String value) {name.set(value);}
        /**
         * Get the StringProperty 
         * 
         * @return a StringProperty which correspond to name
         */
        public StringProperty nameProperty() {return name;}

    /**
     * ListProperty of Follow of the Category
     */
    private final ListProperty<Follow> listOfFollows = new SimpleListProperty<>();
        /**
         * Get the list of Follows
         * 
         * @return an ObservableList which corresponds to a list of Follows
         */
        public final ObservableList<Follow> getListOfFollows() { return listOfFollows.get(); }
        /**
         * Set the list of Follows
         * 
         * @param value the ObservableList to set to the list of Follows
         */
        public final void setListOfFollows(ObservableList<Follow> value) { listOfFollows.set(value); }
        /**
         * Get the ListProperty of the list of Follows
         * 
         * @return a ListProperty listOfFollows 
         */
        public ListProperty listOfFollowsProperty(){return listOfFollows;};

    /**
     * Constructor by default
     */
    public Category() {
        setListOfFollows(FXCollections.<Follow>observableArrayList());
    }

    /**
     * Constructor with a name
     * 
     * @param name the name of the new Category
     */
    public Category(String name) {
        setName(name);
        setListOfFollows(FXCollections.<Follow>observableArrayList());
    }
    
    /**
     * Test if a Follow is in the list of Follows
     * 
     * @param action Predicate to evaluate, here a Follow
     * @return true if the Follow is containing by the list, false otherwise
     */
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

    /**
     * Add a Follow to the list of Follows
     * 
     * @param follow the Follow to add 
     */
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

    /**
     * Delete a follow of the list of Follows
     * 
     * @param follow the Follow to remove
     */
    @Override
    public void deleteFollow(Follow follow) {
        listOfFollows.remove(follow);
    }

    /**
     * toString() method
     * 
     * @return the name of the Category 
     */
    @Override public String toString() {
        return getName();
    }  
}
