/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

/**
 *
 * @author Adrien
 */
public class ModelGateway {
    
    public static void disconnect(){
        UsersManager.disconnect();
    }

    public static boolean connect(String email, String password) {
        return UsersManager.connect(email, password);
    }
    
}