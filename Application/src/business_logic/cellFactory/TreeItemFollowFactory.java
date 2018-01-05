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
    
    /**
     * Follow selected by drag and drop
     */
    private static transient Follow selectedDaD;
        /**
         * Get the Follow selected by drag and drop
         * 
         * @return the Follow selected by drag and drop 
         */
        public static Follow getSelectedDaD() { return selectedDaD; }
        /**
         * Set the Follow selected by drag and drop
         * 
         * @param selectedDaD the Follow selected by drag and drop 
         */
        public static void setSelectedDaD(Follow selectedDaD) { TreeItemFollowFactory.selectedDaD = selectedDaD; }
    
    /**
     * Create a TreeItem element
     * 
     * @param param a TreeView item of Follows
     * @return a TreeItemFollowCell whitch is a TreeItem
     */
    @Override
    public TreeCell<Follow> call(TreeView<Follow> param) {
       return new TreeItemFollowCell(param, new GitHubGateway());
    }
}
