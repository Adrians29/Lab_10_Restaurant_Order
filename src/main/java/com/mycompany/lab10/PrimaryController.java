package com.mycompany.lab10;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button bClearOrder;

    @FXML
    private Button bPlaceOrder;

    @FXML
    private CheckBox cbRequestWater;

    @FXML
    private ToggleGroup drinksToggle;

    @FXML
    private Menu edit;

    @FXML
    private Menu file;

    @FXML
    private Menu help;

    @FXML
    private Label lbCurrentOrder;

    @FXML
    private ListView<String> listViewEntree;

    @FXML
    private MenuItem mIAbout;

    @FXML
    private MenuItem mIClearOrder;

    @FXML
    private MenuItem mIDrinkC;

    @FXML
    private MenuItem mIEntreeC;

    @FXML
    private MenuItem mIExit;

    @FXML
    private MenuItem mIPlaceOrder;

    @FXML
    private MenuItem mISRC;

    @FXML
    private MenuBar menuBar;

    @FXML
    private RadioButton rCoffee;

    @FXML
    private RadioButton rJuice;

    @FXML
    private RadioButton rSoda;

    @FXML
    private RadioButton rTea;

    @FXML
    private TextField tfSpecialRequest;

    private Order modelOrder = new Order();
    
@FXML
    public void initialize() {
        listViewEntree.setItems(FXCollections.observableArrayList(Order.ENTREE_PRICES.keySet()));

        listViewEntree.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            modelOrder.setSelectedEntree(newVal);
            updateTotalDisplay();
        });

        drinksToggle.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                RadioButton selectedRadio = (RadioButton) newVal;
                modelOrder.setSelectedDrink(selectedRadio.getText());
            } else {
                modelOrder.setSelectedDrink(null);
            }
            updateTotalDisplay();
        });

        cbRequestWater.selectedProperty().addListener((obs, oldVal, newVal) -> {
            modelOrder.setWaterRequested(newVal);
        });

        bPlaceOrder.setOnAction(e -> handlePlaceOrder());
        mIPlaceOrder.setOnAction(e -> handlePlaceOrder());

        bClearOrder.setOnAction(e -> handleClearOrder());
        mIClearOrder.setOnAction(e -> handleClearOrder());

        mIExit.setOnAction(e -> System.exit(0));

        mIEntreeC.setOnAction(e -> clearEntreeSelection());
        mIDrinkC.setOnAction(e -> clearDrinkSelection());
        mISRC.setOnAction(e -> clearSpecialRequests());

        mIAbout.setOnAction(e -> showAboutDialog());
    }

    private void updateTotalDisplay() {
        lbCurrentOrder.setText(String.format("Total: $%.2f", modelOrder.getTotalPrice()));
    }

    private void handlePlaceOrder() {
        if (tfSpecialRequest != null) {
            modelOrder.setSpecialRequests(tfSpecialRequest.getText().trim());
        }

        // Validación requerida: el usuario debe seleccionar una entrada y una bebida
        if (modelOrder.getSelectedEntree() == null || modelOrder.getSelectedDrink() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incomplete Order");
            alert.setHeaderText(null);
            alert.setContentText("Please select both an Entrée and a Drink before placing your order.");
            alert.showAndWait();
            return;
        }

        openSummaryWindow();
    }

    private void openSummaryWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la ventana secundaria para pasarle los datos del pedido
            SecondaryController controller = loader.getController();
            controller.initData(modelOrder);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Order Summary");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClearOrder() {
        clearEntreeSelection();
        clearDrinkSelection();
        clearSpecialRequests();
        cbRequestWater.setSelected(false);
        modelOrder.clearAll();
        updateTotalDisplay();
    }

    private void clearEntreeSelection() {
        listViewEntree.getSelectionModel().clearSelection();
        modelOrder.setSelectedEntree(null);
        updateTotalDisplay();
    }

    private void clearDrinkSelection() {
        if (drinksToggle.getSelectedToggle() != null) {
            drinksToggle.getSelectedToggle().setSelected(false);
        }
        modelOrder.setSelectedDrink(null);
        updateTotalDisplay();
    }

    private void clearSpecialRequests() {
        if (tfSpecialRequest != null) {
            tfSpecialRequest.clear();
        }
        modelOrder.setSpecialRequests("");
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Restaurant Ordering Application");
        alert.setContentText("Version: 1.0\nDeveloper: Student Developer\nLab 10 MVC JavaFX Assignment");
        alert.showAndWait();
    }

}
