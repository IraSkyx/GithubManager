package business_logic.cellFactory;

import business_logic.repository.Repository;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author Adrien
 */
public class ListViewRepositoryFactory implements Callback<ListView<Repository>,ListCell<Repository>> {
    @Override
    public ListCell<Repository> call(ListView<Repository> param) {
       return new ListViewRepositoryCell();
    } 
}
