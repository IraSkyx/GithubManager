package business_logic.gateways;

import business_logic.repository.GitHubRepository;
import business_logic.repository.Repository;
import controller.CloneStateController;
import controller.FrontController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import launch.Main;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;
import org.apache.commons.io.FileUtils;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

/**
 * Gateway to GitHub Java API
 * @author Adrien
 */
public class GitHubGateway implements APIManager {
    
    private static final String OAUTH2TOKEN = "5492d1456e37ba89cc8985ccdee4a8dc028e916f";

    @Override
    public ObservableList<Repository> getRepositoriesByName(String input){
        ObservableList<Repository> list = FXCollections.observableArrayList();
        
        try {
            RepositoryService service = new RepositoryService();
            service.getClient().setOAuth2Token(OAUTH2TOKEN);
            
            service.searchRepositories(input).stream().forEach(x -> {
                try {
                    list.add(new GitHubRepository(service.getRepository(RepositoryId.createFromId(x.generateId()))));
                } 
                catch (IOException ex) {
                    Logger.getLogger(GitHubGateway.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return list;
    }

    @Override
    public ObservableList<Repository> getRepositoriesByUsername(String input){
        ObservableList<Repository> list = FXCollections.observableArrayList();

        try {
            RepositoryService service = new RepositoryService();
            service.getClient().setOAuth2Token(OAUTH2TOKEN);
            service.getRepositories(input).stream().forEach(x -> list.add(new GitHubRepository(x)));
        }
        catch (IOException ex) {
            Logger.getLogger(GitHubGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public boolean hasNewCommit(Repository repository){
        GitHubRepository gitHubRepo = (GitHubRepository)repository;

        try {
            RepositoryService service = new RepositoryService();
            service.getClient().setOAuth2Token(OAUTH2TOKEN);

            org.eclipse.egit.github.core.Repository gitRepository = service.getRepository(RepositoryId.createFromId(gitHubRepo.getId()));
            return gitRepository.getUpdatedAt().compareTo(gitHubRepo.getUpdatedAt()) > 0;
        }
        catch (IOException ex) {
            return false;
        }
    }

    @Override
    public void cloneRepository(Repository repository) {
        GitHubRepository gitHubRepo = (GitHubRepository)repository;

        try {
            final URL uri = new URL("https://api.github.com/repos/".concat(gitHubRepo.getId()).concat("/zipball"));
            DirectoryChooser dirChooser = new DirectoryChooser();
            dirChooser.setTitle("Choose destination");
            File selectedDir = dirChooser.showDialog(FrontController.getScene().getWindow());
            if(selectedDir != null){               
                try {                  
                    File zipDestination = new File(selectedDir.getAbsolutePath() + "/" + gitHubRepo.getName() + ".zip");
                    FileUtils.copyURLToFile(uri, zipDestination);
                    ZipFile zipFile = new ZipFile(zipDestination);     
                    System.out.println("test");
                    zipFile.setRunInThread(true);

                    ProgressMonitor mon = zipFile.getProgressMonitor();  
                    System.out.println("test2");
                    Stage stage = new Stage(); 
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Cloning ...");                    
                    CloneStateController ctrl = (CloneStateController)FrontController.setContentStage(stage,"/ihm/CloneState.fxml").getController();
                    
                    System.out.println("test3");
                    zipFile.extractAll(selectedDir.getAbsolutePath() + "/" + gitHubRepo.getName());  
                    System.out.println("test4");

                    while(mon.getPercentDone() < 100){
                        System.out.println(mon.getPercentDone());
                        ctrl.setProgress(zipFile.getProgressMonitor().getPercentDone());
                        Thread.sleep(1000);
                    }   
                    
                    FileUtils.forceDelete(zipDestination);
                    stage.close();
                }
                catch (net.lingala.zip4j.exception.ZipException | IOException | InterruptedException ex ) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }              
            }
        }
        catch(IOException e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static String getReadMe(org.eclipse.egit.github.core.Repository repo){
        try {
            ContentsService contents = new ContentsService();
            contents.getClient().setOAuth2Token(OAUTH2TOKEN);
            RepositoryContents readMe = contents.getReadme(RepositoryId.createFromId(repo.generateId()));
            return new String(org.eclipse.egit.github.core.util.EncodingUtils.fromBase64(readMe.getContent()));
        }
        catch (Exception ex) {
            return "No README.md";
        }
    }

}
