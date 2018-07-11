/**
 *   _____
 * () | ,_   _,         ,   _   _   ,_   _
 *    |/  | / |  /|/|  / \_/   / \_/  | / \_
 *  (/    |/\/|_/ | |_/ \/ \__/\_/    |/\_/
 *
 *  Transcoro Software 2018 || The power of proyects
 **/
package Transcoro.Core;

import Transcoro.Controllers.TabManager;
import Transcoro.Controllers.Units;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;

public class MainLayout extends BorderPane {
    @FXML
    private VBox sideMenu;

    @FXML
    private HBox upperMenu;

    @FXML
    private ScrollPane contentScroll;

    @FXML
    private VBox otherPane;

    public MainLayout(){

        TabManager _tabs = new TabManager();
        Units _unitPage = new Units();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/view/Custom.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        this.sideMenu.getChildren().add(_tabs);
        this.contentScroll.setFitToWidth(true);
        this.contentScroll.setContent(_unitPage);
    }

    public VBox getSideMenu() {
        return sideMenu;
    }

    public void setSideMenu(VBox sideMenu) {
        this.sideMenu = sideMenu;
    }

    public HBox getUpperMenu() {
        return upperMenu;
    }

    public void setUpperMenu(HBox upperMenu) {
        this.upperMenu = upperMenu;
    }

    public ScrollPane getContentScroll() {
        return contentScroll;
    }

    public void setContentScroll(ScrollPane contentScroll) {
        this.contentScroll = contentScroll;
    }

    public VBox getOtherPane() {
        return otherPane;
    }

    public void setOtherPane(VBox otherPane) {
        this.otherPane = otherPane;
    }
}
