package business_logic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import launch.Main;

public class Repository extends Follow {
    
    private final org.eclipse.egit.github.core.Repository proxy;
        
    private final StringProperty name = new SimpleStringProperty();
        public String getName() {return name.get();}
        public void setName(String name) {this.name.set(name);}
        public StringProperty nameProperty(){return name;}
        
    private final StringProperty description = new SimpleStringProperty();
        public String getDescription() {return description.get();}
        public void setDescription(String description) {this.description.set(description);}
        public StringProperty descriptionProperty(){return description;}
        
    private final StringProperty readme = new SimpleStringProperty();
        public String getReadMe() {return readme.get();}
        public void setReadMe(String readme) {this.readme.set(readme);}
        public StringProperty readmeProperty(){return readme;}

    public Repository(org.eclipse.egit.github.core.Repository proxy) {
        this.proxy = proxy;        
        try {   
            setName(proxy.getName());
            setDescription(proxy.getDescription());                                                          
            setReadMe(APIGateway.getReadMe(proxy));
        } 
        catch (Exception ex) {            
            System.out.println(ex.getMessage());
        }       
    }
 
    public org.eclipse.egit.github.core.Repository getProxy() {
        return proxy;
    }
    
    public Date getCreatedAt(){
        return proxy.getCreatedAt();
    }
    
    public String getCloneUrl(){
        return proxy.getCloneUrl();
    }
    
    public org.eclipse.egit.github.core.User getOwner(){
        return proxy.getOwner();
    }
    
    public Date getUpdatedAt(){
        return proxy.getUpdatedAt();
    }   
    
    @Override
    public String toString(){
        return getName();
    }

    @Override
    public void AddFollow(Follow follow) {
        throw new UnsupportedOperationException("OperationNotSupportedException");
    }

    @Override
    public void DeleteFollow(Follow follow) {
        throw new UnsupportedOperationException("OperationNotSupportedException");
    }
}