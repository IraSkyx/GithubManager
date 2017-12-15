package business_logic.repository;

import java.io.Serializable;
import javafx.beans.property.StringProperty;

/**
 * API for Repository
 * @author Adrien
 */
public abstract class Repository extends Follow implements Serializable {   
    
    public String getDescription(){return descriptionProperty().get();};
    public void setDescription(String value){descriptionProperty().set(value);}; 
    
    public String getCreatedAt(){return createdAtProperty().get();}; 
    public void setCreatedAt(String value){createdAtProperty().set(value);}; 
    
    public String getCloneUrl(){return cloneUrlProperty().get();};
    public void setCloneUrl(String value){cloneUrlProperty().set(value);}; 
    
    public String getId(){return idProperty().get();}; 
    public void setId(String value){idProperty().set(value);}; 
    
    public String getOwner(){return ownerProperty().get();};
    public void setOwner(String value){ownerProperty().set(value);}; 
    
    abstract public StringProperty descriptionProperty();
    abstract public StringProperty createdAtProperty();
    abstract public StringProperty cloneUrlProperty();
    abstract public StringProperty idProperty();
    abstract public StringProperty ownerProperty();
}