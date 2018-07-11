package Transcoro.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class TopManager extends HBox {

    public TopManager() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/view/upperMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
