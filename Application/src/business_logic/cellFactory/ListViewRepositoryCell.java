package business_logic.cellFactory;

import business_logic.repository.Repository;
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
        setOnDragDetected( ( MouseEvent event ) -> {
                System.out.println("listcell setOnDragDetected");
                Dragboard db = startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString(this.getItem().toString());
                db.setContent( content );
                event.consume();
            } );       
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
        textProperty().bind(item.nameProperty());
    }
}
