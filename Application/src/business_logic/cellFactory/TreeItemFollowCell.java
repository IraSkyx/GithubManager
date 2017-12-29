package business_logic.cellFactory;

import business_logic.gateways.APIManager;
import business_logic.gateways.GitHubGateway;
import business_logic.repository.Category;
import business_logic.repository.Follow;
import business_logic.repository.Repository;
import javafx.beans.binding.Bindings;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 *
 * @author Adrien
 */
public class TreeItemFollowCell extends TreeCell<Follow> {

    private APIManager apiManager;
    public static boolean firstRender = true;    
    private Follow item;
    
    public TreeItemFollowCell(TreeView<Follow> parent, APIManager apiManager){
              
        this.apiManager=apiManager;
        
        setOnDragDetected((MouseEvent event) -> {
            Dragboard db = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(getItem().toString());
            Follow.setSelectedDaD(getItem());
            db.setContent(content);
            event.consume();
        }); 
        
        setOnDragEntered((DragEvent event) -> {
            setStyle(getItem() instanceof Repository ? "-fx-background-color: grey;" : "-fx-background-color: grey;-fx-font-size:1.5em;");
        });

        setOnDragExited((DragEvent event) -> {
            setStyle(getItem() instanceof Repository ? "" : "-fx-font-size:1.5em;");
        });

        setOnDragOver((DragEvent event) -> {
            if (event.getDragboard().hasString())
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        });

        setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString() && Repository.getSelectedDaD() != null) {
                Repository newItem = (Repository)Follow.getSelectedDaD();
                if(getTreeItem().getValue() instanceof Category){     
                    getTreeItem().getValue().addFollow(newItem);
                    getTreeItem().getChildren().add(new TreeItem<>(newItem));
                    getTreeItem().setExpanded(true);
                }
                else {
                    getTreeItem().getParent().getValue().addFollow(newItem);
                    getTreeItem().getParent().getChildren().add(new TreeItem<>(newItem));   
                    getTreeItem().getParent().setExpanded(true);
                }                  
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });       
    }
    
    @Override
    public void updateItem(Follow item, boolean isEmpty){
        super.updateItem(item, isEmpty);
        this.item=item;
        if(isEmpty){
            textProperty().unbind();
            setText(null);
            setStyle("");
            setGraphic(null);
            return;
        }
        
        if(getTreeView().getRoot().getChildren().isEmpty()){
            textProperty().setValue("You have no favorites yet !\nDrop them here !");
            setStyle("");
            setGraphic(null);
            return;
        }
        
        if(item instanceof Category){
            textProperty().bind(((Category)item).nameProperty());
            setStyle("-fx-font-size:1.5em;");
        }

        if(item instanceof Repository){ 
            if(TreeItemFollowCell.firstRender && apiManager.hasNewCommit((Repository)item)){
                textProperty().bind(Bindings.format("(New !) %s", item.getName()));
                setStyle("-fx-text-fill: rgb(38, 166, 91);");              
            }
            else{    
                textProperty().bind(Bindings.format("%s", item.getName()));
                setStyle("");
            }
            setGraphic(null);
        }
    }   
}
