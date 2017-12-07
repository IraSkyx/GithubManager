package business_logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Repository extends Follow {
    
    private final org.eclipse.egit.github.core.Repository proxy;      
        
    private final StringProperty description = new SimpleStringProperty();
        public String getDescription() {return description.get();}
        public void setDescription(String description) {this.description.set(description);}
        public StringProperty descriptionProperty(){return description;}
        
    private final StringProperty readme = new SimpleStringProperty();
        public String getReadMe() {return readme.get();}
        public void setReadMe(String readme) {this.readme.set(readme);}
        public StringProperty readmeProperty(){return readme;}

    public Repository(org.eclipse.egit.github.core.Repository proxy) {
        super(proxy.getName());
        this.proxy = proxy;        
        try {  
            setName(proxy.getName());
            setDescription(proxy.getDescription());                                                          
            setReadMe(APIGateway.getReadMe(proxy));
        } 
        catch (Exception ex) {            
            System.out.println(ex.getMessage());
        }       
    }
 
    public org.eclipse.egit.github.core.Repository getProxy() {
        return proxy;
    }
    
    @Override
    public String toString(){
        return getName();
    }
}