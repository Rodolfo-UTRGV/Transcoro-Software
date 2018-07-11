package Transcoro.Controllers;

import Transcoro.Classes.tabs.Tab;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TabManager extends VBox {

    @FXML
    private VBox sideMenu;

    @FXML
    private HBox userDisplay;

    @FXML
    private ImageView userProfile;

    @FXML
    private Label userWelcome;

    @FXML
    private Label userRankName;

    @FXML
    private VBox tabsMenu;

    private static String[] Tabs;

    public TabManager(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/view/sideMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.userWelcome.setText("Bienvenido, Rodolfo");
        this.userRankName.setText("Administrador");

        this.drawMenu();
    }

    private void drawMenu(){
        this.Tabs = new String[]{
                "Unidades",
                "Empleados",
                "Clientes",
                "Viajes"
        };

        this.tabsMenu.getChildren().clear();

        for (String t : this.Tabs) {
            Tab mTab = new Tab(t.toString());

            tabsMenu.getChildren().add(mTab.getButton());
        }

    }
}
