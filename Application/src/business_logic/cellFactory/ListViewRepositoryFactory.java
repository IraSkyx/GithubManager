package business_logic.cellFactory;

import business_logic.repository.Repository;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Business logic class for cellFactory management : ListViewRepositoryFactory : Create ListView with Repositories
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class ListViewRepositoryFactory implements Callback<ListView<Repository>,ListCell<Repository>> {
    /**
     * Create a listView item
     * 
     * @param param
     * @return a ListViewRepositoryCell whitch is a ListView item
     */
    @Override
    public ListCell<Repository> call(ListView<Repository> param) {
       return new ListViewRepositoryCell();
    } 
}
