package com.tss.Interfaces.model;

public interface Vehicle {
    void start();
    void stop();
    default void horn(){
        System.out.println("the vehicle has the same horn system");
    }
    default void playMusic(){
        System.out.println("the music is same playing for every vehicles");
    }
    static void vehicleInspection(){
        System.out.println("i get vehicle inspection");
    }
    String getFuelStatus();
}
