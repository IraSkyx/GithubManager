/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import launch.Main;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

/**
 *
 * @author Adrien
 */
public class APIGateway {
    
    public static final String oAuth2Token = "5492d1456e37ba89cc8985ccdee4a8dc028e916f";
    
    public static ObservableList<Repository> getRepositoriesByName(String input){
        try {          
            ObservableList<Repository> list = FXCollections.observableArrayList();
            
            RepositoryService service = new RepositoryService();
            service.getClient().setOAuth2Token(oAuth2Token);
            
            List<org.eclipse.egit.github.core.SearchRepository> search =  service.searchRepositories(input);
            
            search.parallelStream().forEach(x -> {
                try {
                    org.eclipse.egit.github.core.Repository repo = service.getRepository(RepositoryId.createFromId(x.generateId()));
                    list.add(RepositoryMaker.make(repo));
                } 
                catch (IOException ex) {
                    Logger.getLogger(APIGateway.class.getName()).log(Level.SEVERE, null, ex);
                }           
            });
            
            return list;
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public static ObservableList<Repository> getRepositoriesByUsername(String input){
        try {          
            ObservableList<Repository> list = FXCollections.observableArrayList();
            
            RepositoryService service = new RepositoryService();
            service.getClient().setOAuth2Token(oAuth2Token);
            
            service.getRepositories(input).parallelStream().forEach(x -> {
                list.add(RepositoryMaker.make(x));
            });
          
            return list;
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public static String getReadMe(org.eclipse.egit.github.core.Repository proxy){
        try {
            ContentsService contents = new ContentsService();
            contents.getClient().setOAuth2Token(oAuth2Token);                   
            RepositoryContents readMe = contents.getReadme(RepositoryId.createFromId(proxy.generateId()));
            return decode(readMe.getContent());
        } 
        catch (Exception ex) {
            System.out.println("README.md : " + ex.getMessage());
            return "No README.md"; //No README.md found in this Repository
        }       
    }
    
    private static String decode(String value) {
        try{
            byte[] decodedValue = Base64.getMimeDecoder().decode(value);
            return new String(decodedValue, StandardCharsets.UTF_8);
        }
        catch(Exception e){
          return "Failed to decode README.md";
        }
   }
}
