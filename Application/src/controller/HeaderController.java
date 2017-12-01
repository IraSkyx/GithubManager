package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
/**
 *
 * @author fsimo
 */
public class HeaderController extends VBox{
    public HeaderController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/Header.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }
}
