package business_logic.gateways;

import business_logic.repository.Repository;
import javafx.collections.ObservableList;

/**
 * Business interface for gateways management : APIManager : API for gateways
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public interface APIManager {
    public ObservableList<Repository> getRepositoriesByName(String input);
    public ObservableList<Repository> getRepositoriesByUsername(String input);
    public void cloneRepository(Repository repo);
    public boolean hasNewCommit(Repository repository);
}
