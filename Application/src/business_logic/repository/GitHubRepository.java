package business_logic.repository;

import business_logic.gateways.GitHubGateway;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Adapter for org.eclipse.egit.github.core.Repository class
 * @author Adrien
 */
public class GitHubRepository extends Repository implements Serializable {
    
    private final org.eclipse.egit.github.core.Repository adapted;
    
    private final transient StringProperty name = new SimpleStringProperty();
    @Override public StringProperty nameProperty() {return name;}
        
    private final transient StringProperty description = new SimpleStringProperty();
    @Override public StringProperty descriptionProperty() {return description;}
        
    private final transient StringProperty createdAt = new SimpleStringProperty();
    @Override public StringProperty createdAtProperty() {return createdAt;}

    private final transient StringProperty cloneUrl = new SimpleStringProperty();
    @Override public StringProperty cloneUrlProperty() {return cloneUrl;}

    private final transient StringProperty id = new SimpleStringProperty();
    @Override public StringProperty idProperty() {return id;}

    private final transient StringProperty owner = new SimpleStringProperty();
    @Override public StringProperty ownerProperty() {return owner;}
    
    private final transient StringProperty readMe = new SimpleStringProperty();   
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
    
    /*private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(nameProperty().getValueSafe());
        s.writeUTF(descriptionProperty().getValueSafe());
        s.writeUTF(createdAtProperty().getValueSafe());
        s.writeUTF(cloneUrlProperty().getValueSafe());
        s.writeUTF(idProperty().getValueSafe());
        s.writeUTF(ownerProperty().getValueSafe());
        s.writeUTF(readMeProperty().getValueSafe());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        name.set(s.readUTF());
        description.set(s.readUTF());
        createdAt.set(s.readUTF());
        cloneUrl.set(s.readUTF());
        id.set(s.readUTF());
        owner.set(s.readUTF());
        readMe.set(s.readUTF());
    }*/
}
