package business_logic.cellFactory;

import business_logic.gateways.GitHubGateway;
import business_logic.repository.Follow;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

/**
 *
 * @author Adrien
 */
public class TreeItemFollowFactory implements Callback<TreeView<Follow>,TreeCell<Follow>> {

    @Override
    public TreeCell<Follow> call(TreeView<Follow> param) {
       return new TreeItemFollowCell(param, new GitHubGateway());
    }
}
