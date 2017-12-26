package business_logic.cellFactory;

import business_logic.repository.Follow;
import business_logic.repository.Repository;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 *
 * @author Adrien
 */
public class ListViewRepositoryCell extends ListCell<Repository> { 
    
    public ListViewRepositoryCell() {
        setOnDragDetected((MouseEvent event) -> {
            Dragboard db = startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putString(getItem().toString());
            Follow.setSelectedDaD(getItem());
            db.setContent(content);
            event.consume();
        });       
    }
    
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
