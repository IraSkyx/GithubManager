package business_logic.repository;

import java.util.Date;

/**
 * Business class for Repository management : Repository : API for Repository, child class in a composite pattern to make a treelike structure
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public abstract class Repository extends Follow {

    abstract public String getDescription();
    abstract public void setDescription(String value);

    abstract public Date getCreatedAt();
    abstract public void setCreatedAt(Date value);

    abstract public Date getUpdatedAt();
    abstract public void setUpdatedAt(Date value);
    
    abstract public String getId();
}
