package com.mycompany.lab10;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SecondaryController {

    @FXML private Button bClose;
    @FXML private Label lbDrink;
    @FXML private Label lbEntree;
    @FXML private Label lbSRequest;
    @FXML private Label lbTotal;
    @FXML private Label lbWater;

    private Order order;

    @FXML
    public void initialize() {
        // Configurar la acción del botón Cerrar
        if (bClose != null) {
            bClose.setOnAction(event -> {
                Stage stage = (Stage) bClose.getScene().getWindow();
                stage.close();
            });
        }
    }

    // Método para recibir los datos del modelo desde la ventana principal e imprimirlos en pantalla
    public void initData(Order order) {
        this.order = order;

        if (order != null) {
            // Mostrar Entrada y precio
            if (order.getSelectedEntree() != null) {
                lbEntree.setText(String.format("%s ($%.2f)", order.getSelectedEntree(), order.getEntreePrice()));
            } else {
                lbEntree.setText("Empty");
            }

            // Mostrar Bebida y precio
            if (order.getSelectedDrink() != null) {
                lbDrink.setText(String.format("%s ($%.2f)", order.getSelectedDrink(), order.getDrinkPrice()));
            } else {
                lbDrink.setText("Empty");
            }

            // Mostrar Agua (Checkbox)
            lbWater.setText(order.isWaterRequested() ? "Yes" : "No");

            // Mostrar Peticiones Especiales
            String sr = order.getSpecialRequests();
            if (sr == null || sr.isEmpty()) {
                lbSRequest.setText("Nothing");
            } else {
                lbSRequest.setText(sr);
            }

            // Mostrar Precio Total
            lbTotal.setText(String.format("$%.2f", order.getTotalPrice()));
        }
    }
}
