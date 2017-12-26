package business_logic.repository;

import business_logic.gateways.GitHubGateway;
import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Adapter for org.eclipse.egit.github.core.Repository class
 * @author Adrien
 */
public class GitHubRepository extends Repository implements Serializable {
    
    private org.eclipse.egit.github.core.Repository adapted;   
        public org.eclipse.egit.github.core.Repository getAdapted() {return adapted;}     
        public void setAdapted(org.eclipse.egit.github.core.Repository adapted) {this.adapted=adapted;}
    
    private transient final StringProperty readMe = new SimpleStringProperty();
        public String getReadMe() {return readMe.get();}
        public void setReadMe(String value) {readMe.set(value);}
        public StringProperty readMeProperty() {return readMe;}
    
    @Override public String getName() {return adapted.getName();}
    @Override public void setName(String value) {throw new UnsupportedOperationException("Not supported yet.");}
    
    @Override public String getDescription() {return adapted.getDescription();}
    @Override public void setDescription(String value) {throw new UnsupportedOperationException("Not supported yet.");}

    @Override public Date getCreatedAt() {return adapted.getCreatedAt();}
    @Override public void setCreatedAt(String value) {throw new UnsupportedOperationException("Not supported yet.");}

    @Override public String getCloneUrl() {return adapted.getCloneUrl();}
    @Override public void setCloneUrl(String value) {throw new UnsupportedOperationException("Not supported yet."); }

    @Override public String getId() {return adapted.generateId();}
    @Override public void setId(String value) {throw new UnsupportedOperationException("Not supported yet.");}

    @Override public String getOwner() {return adapted.getOwner().toString();}
    @Override public void setOwner(String value) {throw new UnsupportedOperationException("Not supported yet.");}

    @Override public Date getUpdatedAt() {return adapted.getUpdatedAt();}
    @Override public void setUpdatedAt(Date value) {throw new UnsupportedOperationException("Not supported yet.");}   
    
    GitHubRepository(){
        
    }
    
    GitHubRepository(org.eclipse.egit.github.core.Repository adapted) {  
        this.adapted=adapted;
        setReadMe(GitHubGateway.getReadMe(adapted));
    } 
        
    @Override
    public String toString(){
        return getName();
    }
}
