package com.mycompany.lab10;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    public void inialize() {
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
        });
        private void updateTotalDisplay(){
            lbCurrentOrder.setText(String.format("Total:        $%.2f", modelOrder.getTotalPrice()));
        }
        
        private void handlePlaceOrder() {
            if(tfSpecialRequest != null){
                modelOrder.setSpecialRequests(tfSpecialRequest.getText().trim());
            }
            
            if (modelOrder) {
                
            }
        }
    }

}
