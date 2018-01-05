package business_logic.gateways;

import business_logic.repository.Repository;
import javafx.collections.ObservableList;

/**
 * Business interface for gateways management : APIManager : API for gateways
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public interface APIManager {
    /**
     * Get a list of Repositories with a research by name
     * 
     * @param input String research
     * @return a list of Repositories whitch correspond to a research by name
     */
    public ObservableList<Repository> getRepositoriesByName(String input);
    
    /**
     * Get a list of Repositories by a research by username
     * 
     * @param input String research
     * @return a list of Repositories whitch correspond ro a research by username
     */
    public ObservableList<Repository> getRepositoriesByUsername(String input);
    
    /**
     * Clone a repository
     * 
     * @param repo the Repository to clone
     */
    public void cloneRepository(Repository repo);
    
    /**
     * Test if a Repository has new commits 
     * 
     * @param repository the Repository that we want to know if it has new commits
     * @return true if this Repository has new commits, false otherwise
     */
    public boolean hasNewCommit(Repository repository);
}
