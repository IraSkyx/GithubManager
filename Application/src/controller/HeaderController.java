package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class : HeaderController : Controller of Header.fxml
 * 
 * @author Adrien LENOIR and Fabien SIMONET
 */
public class HeaderController extends VBox {
    /**
     * Contructor by default : initialize a FXMLLoader
     * 
     * @throws IOException Exception thrown because of an error in an IO operation
     */
    public HeaderController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/Header.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }
}
