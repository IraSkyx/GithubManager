package business_logic.cellFactory;

import business_logic.repository.Category;
import business_logic.repository.Follow;
import business_logic.repository.Repository;
import javafx.event.EventHandler;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.DragEvent;

/**
 *
 * @author Adrien
 */
public class TreeItemFollowCell extends TreeCell<Follow> {
    
    private Follow item;
    
    public TreeItemFollowCell(TreeView<Follow> parent){
         
            this.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    System.out.println("Drag dropped on " + item);
                    
                    /*Follow valueToMove = dragEvent;
                    TreeItem<Follow> itemToMove = search(parent.getRoot(), valueToMove);
                    TreeItem<Follow> newParent = search(parent.getRoot(), item);*/
                    
                    // Remove from former parent.
                    /*itemToMove.getParent().getChildren().remove(itemToMove);
                    
                    // Add to new parent.
                    newParent.getChildren().add(itemToMove);
                    newParent.setExpanded(true);*/
                    dragEvent.consume();
                }
            });
    }
    
    @Override
    public void updateItem(Follow item, boolean isEmpty){
        super.updateItem(item, isEmpty);
        this.item=item;
        if(isEmpty){
            textProperty().unbind();
            setText(null);
            setGraphic(null);
            return;
        }

        if(item instanceof Category)
            textProperty().bind(item.nameProperty());

        if(item instanceof Repository){      	
            textProperty().bind(item.nameProperty());
            setGraphic(null);
        }
    }   
}
