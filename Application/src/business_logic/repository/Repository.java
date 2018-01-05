package business_logic.repository;

import java.util.Date;

/**
 * Business class for Repository management : Repository : API for Repository, child class in a composite pattern to make a treelike structure
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public abstract class Repository extends Follow {

    /**
     * Get the Repository description
     * 
     * @return the String which corresponds to the Repository description
     */
    abstract public String getDescription();
    /**
     * Set the Repository description
     * 
     * @param value the String which corresponds to the Repository description
     */
    abstract public void setDescription(String value);

    /**
     * Get the Repository creation date
     * 
     * @return the Date which corresponds to the Repository creation date
     */
    abstract public Date getCreatedAt();
    /**
     * Set the Repository creation date
     * 
     * @param value the Date which corresponds to the Repository creation date
     */
    abstract public void setCreatedAt(Date value);

    /**
     * Get the Repository updating date
     * 
     * @return the Date which corresponds to the Repository updating date
     */
    abstract public Date getUpdatedAt();
    /**
     * Set the Repository updating date
     * 
     * @param value the Date which corresponds to the Repository updating date
     */
    abstract public void setUpdatedAt(Date value);
    
    /**
     * Get the the Repository id
     * 
     * @return the String which corresponds to the Repository id
     */
    abstract public String getId();
}
