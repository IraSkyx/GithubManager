package business_logic.repository;

/**
 * Business class for Repository management : Abstract Class Follow : mother class in a composite pattern to make a treelike structure
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public abstract class Follow {
    
    private Follow parent;
        public Follow getParent(){return parent;}
        public void setParent(Follow value){parent = value;}
    
    abstract public String getName();
    abstract public void setName(String value);

    /**
     * Add a follow : not implemented
     * 
     * @param follow 
     */
    public void addFollow(Follow follow) { throw new UnsupportedOperationException("OperationNotSupportedException"); }
    
    /**
     * Delete a follow : not implemented
     * 
     * @param follow 
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
     * @param o
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
