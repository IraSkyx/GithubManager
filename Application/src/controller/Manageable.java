package controller;

import business_logic.gateways.APIManager;

/**
 * FXML Controller interface : Interface who let class which implements its to set the APIManager
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public interface Manageable {
    /**
     * Set the APIManager
     * 
     * @param apiManager to be set 
     */
    public void setApiManager(APIManager apiManager);
}
