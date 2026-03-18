package com.tss.FoodApp.test;

import com.tss.FoodApp.facade.FoodAppFacade;

public class Main {
    public static void main(String[] args) {
        try {
            FoodAppFacade facade = new FoodAppFacade();
            facade.StartApplication();
        } catch (Exception e) {
            System.out.println("❌ Application failed: " + e.getMessage());
        }
    }
}