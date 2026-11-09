/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab10;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author 2538954
 */
public class Order {
    private String selectedEntree;
    private String selectedDrink;
    private String specialRequests;
    private boolean waterRequested;
    
    public static final Map<String, Double> ENTREE_PRICES = new LinkedHashMap<>();
    public static final Map<String, Double> DRINK_PRICES = new LinkedHashMap<>();
    
    static {
        ENTREE_PRICES.put("Chicken Salad", 6.50);
        ENTREE_PRICES.put("Turkey Sandwich", 6.75);
        ENTREE_PRICES.put("Vegetable Wrap", 6.00);
        ENTREE_PRICES.put("Grilled Cheese", 5.00);
        
        DRINK_PRICES.put("Coffee", 1.50);
        DRINK_PRICES.put("Tea", 1.50);
        DRINK_PRICES.put("Soda", 2.00);
        DRINK_PRICES.put("Juice", 1.75);
    }
    
    public Order() {
        clearAll();
    }
    
    public void clearAll() {
        this.selectedEntree = null;
        this.selectedDrink = null;
        this.specialRequests = "";
        this.waterRequested = false;
    }

    public String getSelectedEntree() {
        return selectedEntree;
    }

    public void setSelectedEntree(String selectedEntree) {
        this.selectedEntree = selectedEntree;
    }

    public String getSelectedDrink() {
        return selectedDrink;
    }

    public void setSelectedDrink(String selectedDrink) {
        this.selectedDrink = selectedDrink;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    public boolean isWaterRequested() {
        return waterRequested;
    }

    public void setWaterRequested(boolean waterRequested) {
        this.waterRequested = waterRequested;
    }
    
    public double getEntreePrice() {
        return (selectedEntree != null) ? ENTREE_PRICES.getOrDefault(selectedEntree, 0.0) : 0.0;
    }
    
    public double getDrinkPrice() {
        return (selectedDrink != null) ? DRINK_PRICES.getOrDefault(selectedDrink, 0.0) : 0.0;
    }
    
    public double getTotalPrice() {
        return getEntreePrice() + getDrinkPrice();
    }
}
