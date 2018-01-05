package business_logic.cellFactory;

import business_logic.repository.Repository;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * Business logic class for cellFactory management : ListViewRepositoryCell : Manage ListView Repository cells
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class ListViewRepositoryCell extends ListCell<Repository> { 
    
    /**
     * Constructor : implement drag and drop
     */
    public ListViewRepositoryCell() {
        setOnDragDetected((MouseEvent event) -> {
            Dragboard db = startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            if(getItem() != null){
                content.putString(getItem().toString());
                TreeItemFollowFactory.setSelectedDaD(getItem());
                db.setContent(content);
            }
            event.consume();
        });       
    }
    
    /**
     * Update the ListView
     * 
     * @param item an item to update
     * @param isEmpty a boolean to know if the item must be reinit
     */
    @Override
    protected void updateItem(Repository item, boolean isEmpty) {
        super.updateItem(item, isEmpty);
        if(isEmpty){
            textProperty().unbind();
            setText(null);
            setGraphic(null);
            return;
        }
        textProperty().bind(Bindings.format("%s", item.getName()));
    }
}
