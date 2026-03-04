package com.tss.FoodAppV4.test;

import com.tss.FoodAppV4.facade.FoodAppFacade;

public class Main {
    public static void main(String[] args) {
        try {
            FoodAppFacade facade = new FoodAppFacade();
            facade.startApplication();
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Application failed: " + e.getMessage());
        }
    }
}