package business_logic.cellFactory;

import business_logic.gateways.GitHubGateway;
import business_logic.repository.Follow;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

/**
 * Business logic class for cellFactory management : TreeItemFollowFactory : Create TreeItem with Follows
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class TreeItemFollowFactory implements Callback<TreeView<Follow>,TreeCell<Follow>> {
    
    private static transient Follow selectedDaD;
        public static Follow getSelectedDaD() { return selectedDaD; }
        public static void setSelectedDaD(Follow selectedDaD) { TreeItemFollowFactory.selectedDaD = selectedDaD; }
    
    /**
     * Create a TreeItem element
     * 
     * @param param
     * @return a TreeItemFollowCell whitch is a TreeItem
     */
    @Override
    public TreeCell<Follow> call(TreeView<Follow> param) {
       return new TreeItemFollowCell(param, new GitHubGateway());
    }
}
