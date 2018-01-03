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
    @Override public void setName(String value) {adapted.setName(value);}

    @Override public String getDescription() {return adapted.getDescription();}
    @Override public void setDescription(String value) {adapted.setDescription(value);}

    @Override public Date getCreatedAt() {return adapted.getCreatedAt();}
    @Override public void setCreatedAt(Date value) {adapted.setCreatedAt(value);}

    @Override public Date getUpdatedAt() {return adapted.getUpdatedAt();}
    @Override public void setUpdatedAt(Date value) {adapted.setUpdatedAt(value);}

    @Override public String getId() {return adapted.generateId()== null ? "" : adapted.generateId();}

    public GitHubRepository(){

    }

    public GitHubRepository(org.eclipse.egit.github.core.Repository adapted) {
        this.adapted=adapted;
        setReadMe(GitHubGateway.getReadMe(adapted));
    }

    @Override 
    public boolean equals(Object o){
        if (o == null)
            return false;

        if (this == o)
            return true;

        if (getClass() != o.getClass())
            return false;
        
        return getId().equals(((GitHubRepository)o).getId());
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
