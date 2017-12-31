package business_logic.cellFactory;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author Adrien
 */
public class ComboBoxChoiceFactory implements Callback<ListView<String>,ListCell<String>> {
    
    public static ObservableList<String> comboList = FXCollections.observableArrayList(Arrays.asList(
        "By name",
        "By user's name"
    ));
    
    @Override
    public ListCell<String> call(ListView<String> param) {
       return new ComboBoxChoiceCell();
    } 
}
