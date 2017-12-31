package business_logic.repository;

/**
 * Specify the instanciation of a Repositorye by its dynamic type 
 * @author Adrien
 */
public class GitHubRepositoryFactory {
    public static GitHubRepository create(){return new GitHubRepository();}
    public static GitHubRepository create(org.eclipse.egit.github.core.Repository adapted){return new GitHubRepository(adapted);}
}
