package Transcoro.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Units extends VBox {
    public Units() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/view/Units.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
