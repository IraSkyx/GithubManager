package business_logic.gateways;

import business_logic.repository.Repository;
import javafx.collections.ObservableList;

/**
 *
 * @author Adrien
 */
public interface APIManager {
    public ObservableList<Repository> getRepositoriesByName(String input);
    public ObservableList<Repository> getRepositoriesByUsername(String input);
    public void cloneRepository(Repository repo);
    public boolean hasNewCommit(Repository repository);
}
