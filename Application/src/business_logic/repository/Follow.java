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

    public void addFollow(Follow follow) { throw new UnsupportedOperationException("OperationNotSupportedException"); }
    public void deleteFollow(Follow follow) { throw new UnsupportedOperationException("OperationNotSupportedException"); }
    
    public Follow getRoot(){
        Follow root = this;
        while(root.getParent() != null){
            root = root.getParent();
        }
        return root;
    }
    
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
