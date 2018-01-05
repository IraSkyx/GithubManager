package business_logic.repository;

/**
 * Business class for Repository management : Abstract Class Follow : mother class in a composite pattern to make a treelike structure
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public abstract class Follow {
    
    /**
     * Parent Follow
     */
    private Follow parent;
        /**
         * Get the parent
         * 
         * @return parent Follow 
         */
        public Follow getParent(){return parent;}
        /**
         * Set the parent
         * 
         * @param value the Follow to set as a parent
         */
        public void setParent(Follow value){parent = value;}
    
    /**
     * Get the name of the Follow
     * 
     * @return the String which corresponds to the name of the Follow 
     */
    abstract public String getName();
    /**
     * Set the name of the Follow
     * 
     * @param value the String to set as the name of the Follow 
     */
    abstract public void setName(String value);

    /**
     * Add a follow : not implemented
     * 
     * @param follow the Follow to add
     */
    public void addFollow(Follow follow) { throw new UnsupportedOperationException("OperationNotSupportedException"); }
    
    /**
     * Delete a follow : not implemented
     * 
     * @param follow the Follow to delete
     */
    public void deleteFollow(Follow follow) { throw new UnsupportedOperationException("OperationNotSupportedException"); }
    
    /**
     * Get the root node in the tree of Follows
     * 
     * @return the root Follow 
     */
    public Follow getRoot(){
        Follow root = this;
        while(root.getParent() != null){
            root = root.getParent();
        }
        return root;
    }
    
    /**
     * Test if one Object is equal to a Follow
     * 
     * @param o the Object to compare
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

        return getName().equals(((Follow)o).getName());
    }
}
