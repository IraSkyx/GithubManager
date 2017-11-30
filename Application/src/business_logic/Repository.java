package business_logic;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Repository extends Follow{
    
    private final StringProperty name = new SimpleStringProperty();
        public String getName() {return proxy.getName();}
        public void setName(String name) {this.proxy.setName(name);}
        public StringProperty nameProperty(){return name;}
    
    private final org.eclipse.egit.github.core.Repository proxy;

    public Repository(org.eclipse.egit.github.core.Repository proxy) {
        this.proxy = proxy;
    }

    public org.eclipse.egit.github.core.Repository getProxy() {
        return proxy;
    }
    
    public Date getCreatedAt(){
        return proxy.getCreatedAt();
    }
    
    public String getCloneUrl(){
        return proxy.getCloneUrl();
    }
    
    public String getDescription(){
        return proxy.getDescription();
    }
    
    public org.eclipse.egit.github.core.User getOwner(){
        return proxy.getOwner();
    }
    
    public Date getUpdatedAt(){
        return proxy.getUpdatedAt();
    }

    @Override
    void AddFollow(Follow follow) {
        throw new UnsupportedOperationException("Operation not allowed.");
    }

    @Override
    void DeleteFollow(Follow follow) {
        throw new UnsupportedOperationException("Operation not allowed.");
    }
}