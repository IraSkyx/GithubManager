package business_logic.repository;

import java.io.Serializable;
import javafx.beans.property.StringProperty;

/**
 * Abstract Class Follow : mother class in a composite pattern to make a treelike structure
 * @author Adrien
 */
public abstract class Follow implements Serializable {
        
    private static Follow selectedDaD;
        public static Follow getSelectedDaD() { return selectedDaD; }
        public static void setSelectedDaD(Follow selectedDaD) { Follow.selectedDaD = selectedDaD; }
    
    public String getName(){return nameProperty().get();};
    public void setName(String value){nameProperty().set(value);};
    
    abstract public StringProperty nameProperty();
    
    public void addFollow(Follow follow) {
        throw new UnsupportedOperationException("OperationNotSupportedException");
    }

    public void deleteFollow(Follow follow) {
        throw new UnsupportedOperationException("OperationNotSupportedException");
    }
}
