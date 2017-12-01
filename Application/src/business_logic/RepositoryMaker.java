/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

/**
 *
 * @author Adrien
 */
public class RepositoryMaker {
    public static Repository Make(org.eclipse.egit.github.core.Repository repository){
        //TO ADD : Handle difference between org.eclipse.egit.github.core.Repository and Repository
        return new Repository(repository);
    }
}
