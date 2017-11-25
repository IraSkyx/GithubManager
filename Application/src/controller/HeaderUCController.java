package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
/**
 *
 * @author fsimo
 */
public class HeaderUCController {
    public HeaderUCController() throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/ihm/HeaderUC.fxml"));
        leLoader.setController(this);
        leLoader.setRoot(this);
        leLoader.load();
    }
}
