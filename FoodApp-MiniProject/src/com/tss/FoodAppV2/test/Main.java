package com.tss.FoodAppV2.test;

import com.tss.FoodAppV2.facade.FoodAppFacade;

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