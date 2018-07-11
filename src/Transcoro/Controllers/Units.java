package Transcoro.Controllers;

import Transcoro.Classes.Unit;
import Transcoro.Models.Units_Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Units extends VBox {

    private Units_Model model;

    @FXML private Label error_message;
    @FXML private Label success_message;

    @FXML private TextField register_unitNumber;
    @FXML private TextField register_unitPlates;
    @FXML private TextField register_unitVIN;
    @FXML private ComboBox register_unitBrand;
    @FXML private ComboBox register_unitModel;
    @FXML private DatePicker register_unitPurchaseDate;

    private String error;

    public Units() {
        this.model = new Units_Model();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/view/Units.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        String unitIDString = register_unitNumber.getText();
        int unitID = 0;
        String unitPlates = register_unitPlates.getText();
        String unitVIN = register_unitVIN.getText();
        String unitBrand = (String) register_unitBrand.getValue();
        String unitModelString = (String) register_unitModel.getValue();
        int unitModel = 0;
        LocalDate localPurchaseDate = register_unitPurchaseDate.getValue();
        Date unitPurchaseDate = Date.valueOf(localPurchaseDate);

        try {
            unitID = Integer.parseInt(unitIDString);
        } catch(NumberFormatException e){
            this.error = "Número de unidad inválido. Debe contener solo números.";
        }

        try {
            unitModel = Integer.parseInt(unitModelString);
        } catch(NumberFormatException e){
            this.error = "Modelo de unidad inválido. Debe contener solo números.";
        }

        if(this.error != null) {
            this.error_message.setText(this.error);
        } else {
            Unit registerUnit = new Unit(unitID, unitBrand, unitModel, unitPlates, unitPurchaseDate, unitVIN);

            if(this.model.addUnit(registerUnit))
                this.success_message.setText("La unidad " + unitID + " ha sido registrada correctamente.");
            else
                this.error_message.setText("¡Oops! Ocurrio un error, no se pudo agregar la unidad a la base de datos.");
        }

    }

    /**
     * Add a message below the title of the page.
     * @param type
     * 1 = WARNING
     * 0 = SUCCESS
     * -1 = ERROR
     * @param message
     * The Message
     */
    private void addMessage(int type, String message) {

        String classType;

        switch (type) {
            case 1:
                classType = "";
                break;
            default:
            case 0:
                classType = "";
                break;
            case -1:
                classType ="";
                break;
        }

        VBox n = (VBox) this.lookup("#content-top");
        Label title = (Label) n.lookup("#content-title");
        int titleIndex = n.getChildren().indexOf(title);

        Label newMessage = new Label(message);
        newMessage.getStyleClass().add("error-message");
    }
}
