package business_logic.repository;

import business_logic.gateways.GitHubGateway;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Adapter for org.eclipse.egit.github.core.Repository class
 * @author Adrien
 */
public class GitHubRepository extends Repository {
    
    private final org.eclipse.egit.github.core.Repository adapted;
    
    private final StringProperty name = new SimpleStringProperty();
    @Override public StringProperty nameProperty() {return name;}
        
    private final StringProperty description = new SimpleStringProperty();
    @Override public StringProperty descriptionProperty() {return description;}
        
    private final StringProperty createdAt = new SimpleStringProperty();
    @Override public StringProperty createdAtProperty() {return createdAt;}

    private final StringProperty cloneURL = new SimpleStringProperty();
    @Override public StringProperty cloneUrlProperty() {return cloneURL;}

    private final StringProperty id = new SimpleStringProperty();
    @Override public StringProperty idProperty() {return id;}

    private final StringProperty owner = new SimpleStringProperty();
    @Override public StringProperty ownerProperty() {return owner;}
    
    private final StringProperty readMe = new SimpleStringProperty();   
        public final String getReadMe() { return readMe.get(); }
        public final void setReadMe(String value) { readMe.set(value); }
        public StringProperty readMeProperty(){return readMe;};
    
    GitHubRepository(org.eclipse.egit.github.core.Repository adapted) {  
        setName(adapted.getName());
        setDescription(adapted.getDescription());
        setCreatedAt(adapted.getCreatedAt().toString());
        setCloneUrl(adapted.getCloneUrl());
        setId(adapted.generateId());
        setOwner(adapted.getOwner().toString());
        setReadMe(GitHubGateway.getReadMe(adapted));
        this.adapted=adapted;
    }

    public org.eclipse.egit.github.core.Repository getAdapted() {
        return adapted;
    }       
    
    @Override
    public String toString(){
        return getName();
    }
}
