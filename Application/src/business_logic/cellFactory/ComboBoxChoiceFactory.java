package business_logic.cellFactory;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Business logic class for cellFactory management : ComboBoxChoiceFactory : Create combobox choices 
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class ComboBoxChoiceFactory implements Callback<ListView<String>,ListCell<String>> {
    
    public static ObservableList<String> comboList = FXCollections.observableArrayList(Arrays.asList(
        "By name",
        "By user's name"
    ));
    
    /**
     * Create a ListCell
     * 
     * @param param
     * @return 
     */
    @Override
    public ListCell<String> call(ListView<String> param) {
       return new ComboBoxChoiceCell();
    } 
}
