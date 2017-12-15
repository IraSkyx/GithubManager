package business_logic.cellFactory;

import business_logic.repository.Repository;
import javafx.scene.control.ListCell;

/**
 *
 * @author Adrien
 */
public class ListViewRepositoryCell extends ListCell<Repository> {
    
    @Override
    protected void updateItem(Repository item, boolean isEmpty) {
        super.updateItem(item, isEmpty);
        if(isEmpty){
            textProperty().unbind();
            setText(null);
            setGraphic(null);
            return;
        }
        textProperty().bind(item.nameProperty());
    }
}
