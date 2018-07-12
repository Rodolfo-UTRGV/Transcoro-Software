/**
 *   _____
 * () | ,_   _,         ,   _   _   ,_   _
 *    |/  | / |  /|/|  / \_/   / \_/  | / \_
 *  (/    |/\/|_/ | |_/ \/ \__/\_/    |/\_/
 *
 *  Transcoro Software 2018 || The power of proyects
 **/
package Transcoro.Controllers;

import Transcoro.Classes.units.Unit;
import Transcoro.Models.Units_Model;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Units extends VBox {

    private Units_Model model;
    private Units root;

    @FXML private Label totalUnits;
    @FXML private Label onServiceUnits;
    @FXML private Label onTrackUnits;

    @FXML private TextField register_unitNumber;
    @FXML private TextField register_unitPlates;
    @FXML private TextField register_unitVIN;
    @FXML private ComboBox register_unitBrand;
    @FXML private ComboBox register_unitModel;
    @FXML private DatePicker register_unitPurchaseDate;

    public Units() {
        this.model = new Units_Model();
        this.root = this;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/view/Units.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.totalUnits.setText(String.valueOf(this.model.totalUnits()));
        this.onServiceUnits.setText(String.valueOf(this.model.onServiceUnits()));
        this.onTrackUnits.setText(String.valueOf(this.model.onTrackUnits()));

        this.fillUnitsTable();
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        String error = null;

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
            error = "Número de unidad inválido. Debe contener solo números.";
        }

        try {
            unitModel = Integer.parseInt(unitModelString);
        } catch(NumberFormatException e){
            error = "Modelo de unidad inválido. Debe contener solo números.";
        }

        if(error != null) {
            this.addMessage(-1, error);
        } else {
            Unit registerUnit = new Unit(unitID, unitBrand, unitModel, unitPlates, unitPurchaseDate, unitVIN);

            if(this.model.addUnit(registerUnit)) {
                this.addUnitRow(registerUnit);
                this.totalUnits.setText(String.valueOf(this.model.totalUnits()));
                this.addMessage(0, "La unidad " + unitID + " ha sido registrada correctamente.");
            } else
                this.addMessage(-1,"¡Oops! Ocurrio un error, no se pudo agregar la unidad a la base de datos.");
        }
    }

    /**
     * Fills the Units Table with Units retrieved from the database.
     */
    private void fillUnitsTable(){
        ArrayList<Unit> _currentUnits = this.model.retrieveUnits();

        for(Unit u : _currentUnits) {
            addUnitRow(u);
        }
    }

    /**
     * Creates a Row into the Units Table
     * @param unit
     */
    private void addUnitRow(Unit unit) {
        VBox n = (VBox) this.lookup("#unitTable");
        VBox b = (VBox) n.lookup("#body");

        HBox row = new HBox();
        row.getStyleClass().add("row");
        Label unitNumber = new Label(String.valueOf(unit.getId()));
        unitNumber.getStyleClass().add("column");
        Label unitPlates = new Label(unit.getPlates());
        unitPlates.getStyleClass().add("column");
        Label unitType = new Label("UNDEFINED");
        unitType.getStyleClass().add("column");

        HBox actionsBox = new HBox();
        Button viewButton = new Button("Ver");
        viewButton.getStyleClass().add("actionButton");
        Label sep1 = new Label("||");
        sep1.getStyleClass().add("actionLabel");
        Button editButton = new Button("Editar");
        editButton.getStyleClass().add("actionButton");
        Label sep2 = new Label("||");
        sep2.getStyleClass().add("actionLabel");
        Button deleteButton = new Button("Borrar");
        deleteButton.getStyleClass().add("actionButton");
        actionsBox.getStyleClass().add("column");

        actionsBox.getChildren().add(viewButton);
        actionsBox.getChildren().add(sep1);
        actionsBox.getChildren().add(editButton);
        actionsBox.getChildren().add(sep2);
        actionsBox.getChildren().add(deleteButton);

        DeleteRowEvent deleteRow = new DeleteRowEvent(unit.getId(), row);

        deleteButton.setOnAction(deleteRow);


        row.getChildren().add(unitNumber);
        row.getChildren().add(unitPlates);
        row.getChildren().add(unitType);
        row.getChildren().add(actionsBox);

        b.getChildren().add(row);
    }

    /**
     * Remove a Row from the Units Table
     */
    private void removeUnitRow(Node row){
        VBox n = (VBox) this.lookup("#unitTable");
        VBox b = (VBox) n.lookup("#body");

        b.getChildren().remove(row);
    }

    /**
     * @description Add a message below the title of the page.
     * @param type
     * @param message
     */
    private void addMessage(int type, String message) {

        String classType;

        switch (type) {
            case 1:
                classType = "warning-message";
                break;
            default:
            case 0:
                classType = "success-message";
                break;
            case -1:
                classType ="error-message";
                break;
        }

        VBox n = (VBox) this.lookup("#content-top");
        Label title = (Label) n.lookup("#content-title");
        int titleIndex = n.getChildren().indexOf(title);

        Label newMessage = new Label(message);
        newMessage.getStyleClass().add(classType);

        FadeTransition appearMessage = new FadeTransition(Duration.seconds(3), newMessage);
        appearMessage.setFromValue(0.0);
        appearMessage.setToValue(1.0);

        FadeTransition hideMessage = new FadeTransition(Duration.seconds(3), newMessage);
        hideMessage.setFromValue(1.0);
        hideMessage.setToValue(0.0);
        hideMessage.setDelay(Duration.seconds(20));
        hideMessage.setOnFinished(e -> n.getChildren().remove(newMessage));

        n.getChildren().add(newMessage);
        appearMessage.play();
        hideMessage.play();
    }

    /**
     * Delete Row Event Class
     */
    class DeleteRowEvent implements EventHandler<ActionEvent> {
        private int unitID;
        private Node parent;

        DeleteRowEvent(int unitID, Node parent){
            this.unitID = unitID;
            this.parent = parent;
        }

        @Override
        public void handle(ActionEvent event) {
            root.removeUnitRow(this.parent);
            model.deleteUnit(this.unitID);
        }
    }
}
