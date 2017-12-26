package business_logic.repository;

/**
 * Specify the instanciation of a Repositorye by its dynamic type 
 * @author Adrien
 */
public class GitHubRepositoryFactory {
    public static GitHubRepository make(){return new GitHubRepository();}
    public static GitHubRepository make(org.eclipse.egit.github.core.Repository adapted){return new GitHubRepository(adapted);}
}
