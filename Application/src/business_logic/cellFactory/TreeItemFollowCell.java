package business_logic.cellFactory;

import business_logic.repository.Category;
import business_logic.repository.Follow;
import business_logic.repository.GitHubRepository;
import business_logic.repository.GitHubRepositoryFactory;
import business_logic.repository.Repository;
import business_logic.user.UsersManager;
import javafx.event.EventHandler;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 *
 * @author Adrien
 */
public class TreeItemFollowCell extends TreeCell<Follow> {
    
    private Follow item;
    
    public TreeItemFollowCell(TreeView<Follow> parent){
        setOnDragEntered((DragEvent event) -> {
            setStyle( "-fx-background-color: grey;" );
        });

        setOnDragExited((DragEvent event) -> {
            setStyle("");
        });

        setOnDragOver((DragEvent event) -> {
            if (event.getDragboard().hasString())
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        });

        setOnDragDropped((DragEvent event) -> {
            System.out.println("treeCell.setOnDragDropped");
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                System.out.println("Dropped: " + db.getString());
                
                if(getTreeItem().getValue() instanceof Category){
                    System.out.println("Je drop sur une category");
                    
                    GitHubRepository newItem = GitHubRepositoryFactory.make((org.eclipse.egit.github.core.Repository)db.getContent(DataFormat.HTML));
                    
                    getTreeItem().getChildren().add(new TreeItem<>(newItem));
                    UsersManager.currentUserProperty().getValue().userFollowProperty().getValue().addFollow(newItem);
                    System.out.println(UsersManager.currentUserProperty().getValue().userFollowProperty().toString());
                    getTreeItem().setExpanded(true);
                }
                else {
                    System.out.println("Je drop sur un repository");
                    getTreeItem().getParent().getChildren().add(new TreeItem<>(GitHubRepositoryFactory.make((org.eclipse.egit.github.core.Repository)db.getContent(DataFormat.HTML))));   
                    getTreeItem().getParent().setExpanded(true);
                }                  
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        } );       
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
