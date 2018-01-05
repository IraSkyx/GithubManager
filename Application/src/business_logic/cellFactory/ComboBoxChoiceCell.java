package business_logic.cellFactory;

import javafx.scene.control.ListCell;

/**
 * Business logic class for cellFactory management : ComboBoxChoiceCell : Manage choice of Combobox cell
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class ComboBoxChoiceCell extends ListCell<String> {
    
    /**
     * Update the item in the combobox
     * 
     * @param item the item to update
     * @param isEmpty a boolean to know if we reinit the item
     */
    @Override
    protected void updateItem(String item, boolean isEmpty) {
        super.updateItem(item, isEmpty);
        if(isEmpty){
            textProperty().unbind();
            setText(null);
            setGraphic(null);
            return;
        }
        textProperty().setValue(item);
    }
}
