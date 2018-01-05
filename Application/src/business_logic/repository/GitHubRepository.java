package business_logic.repository;

import business_logic.gateways.GitHubGateway;
import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Business class for Repository management : GitHubRepository : Adapter for org.eclipse.egit.github.core.Repository class
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class GitHubRepository extends Repository implements Serializable {
    
    /**
     * GitHub Repository which as to be adapted at our own Repository
     */
    private org.eclipse.egit.github.core.Repository adapted;
        /**
         * Get the GitHub Repository
         * 
         * @return the GitHub Repository
         */
        public org.eclipse.egit.github.core.Repository getAdapted() {return adapted;}
        /**
         * Set the GitHub Repository
         * 
         * @param adapted the GitHub Repository to be set
         */
        public void setAdapted(org.eclipse.egit.github.core.Repository adapted) {this.adapted=adapted;}

    /**
     * StringProperty which corresponds to the ReadMe which is not serialisable in our application
     */
    private transient final StringProperty readMe = new SimpleStringProperty();
        /**
         * Get the ReadMe
         * 
         * @return a String which corresponds to the ReadMe 
         */
        public String getReadMe() {return readMe.get();}
        /**
         * Set the ReadMe
         * 
         * @param value a String which correspond to the new ReadMe to be set 
         */
        public void setReadMe(String value) {readMe.set(value);}
        /**
         * Get the StringProperty which corresponds to the ReadMe
         * 
         * @return the StringProperty which corresponds to the ReadMe
         */
        public StringProperty readMeProperty() {return readMe;}

    /**
     * Get the GitHub Repository name
     * 
     * @return the String which corresponds to the GitHub Repository name
     */
    @Override public String getName() {return adapted.getName();}
    /**
     * Set the GitHub Repository name
     * 
     * @param value the String which correspond to the new name to be set 
     */
    @Override public void setName(String value) {adapted.setName(value);}

    /**
     * Get the GitHub Repository description
     * 
     * @return the String which corresponds to the GitHub Repository description
     */
    @Override public String getDescription() {return adapted.getDescription();}
    /**
     * Set the GitHub Repository description
     * @param value the String which corresponds to the GitHub Repository description
     */
    @Override public void setDescription(String value) {adapted.setDescription(value);}

    /**
     * Get the GitHub Repository creation date
     * 
     * @return the Date which corresponds to the GitHub Repository creation date
     */
    @Override public Date getCreatedAt() {return adapted.getCreatedAt();}
    /**
     * Set the GitHub Repository creation date
     * 
     * @param value the Date which corresponds to the GitHub Repository creation date
     */
    @Override public void setCreatedAt(Date value) {adapted.setCreatedAt(value);}

    /**
     * Get the GitHub Repository updating date
     * 
     * @return the Date which corresponds to the GitHub Repository updating date
     */
    @Override public Date getUpdatedAt() {return adapted.getUpdatedAt();}
    /**
     * Set the GitHub Repository updating date
     * 
     * @param value the Date which corresponds to the GitHub Repository updating date
     */
    @Override public void setUpdatedAt(Date value) {adapted.setUpdatedAt(value);}

    /**
     * Get the the GitHub Repository id
     * 
     * @return the String which corresponds to the GitHub Repository id
     */
    @Override public String getId() {return adapted.generateId()== null ? "" : adapted.generateId();}

    /**
     * Constructor by default
     */
    public GitHubRepository(){

    }

    /**
     * Constructor with a Repository GitHub
     * 
     * @param adapted the Repository GitHub
     */
    public GitHubRepository(org.eclipse.egit.github.core.Repository adapted) {
        this.adapted=adapted;
        setReadMe(GitHubGateway.getReadMe(adapted));
    }

    /**
     * Test if one Object is equal to a GitHubRepository
     * 
     * @param o the Object o to compare
     * @return true if they are equals, false otherwise
     */
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
    
    /**
     * toString() method
     * 
     * @return GitHubRepository name
     */
    @Override
    public String toString(){
        return getName();
    }
}
