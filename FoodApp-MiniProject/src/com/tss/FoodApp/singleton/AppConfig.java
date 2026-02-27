package com.tss.FoodApp.singleton;

public class AppConfig {

    private static AppConfig instance;

    private double discountThreshold  = 500.0;
    private double flatDiscountAmount = 50.0;
    private double discountPercentage = 10.0;
    private int    maxAgentsPerRestaurant = 5;
    private String appName  = "FoodApp";
    private String version  = "1.0";

     private String activeDiscountType = "FLAT";

    private AppConfig() {}

    public static synchronized AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }

    public double getDiscountThreshold()               { return discountThreshold; }
    public void   setDiscountThreshold(double v)       { this.discountThreshold = v; }

    public double getFlatDiscountAmount()              { return flatDiscountAmount; }
    public void   setFlatDiscountAmount(double v)      { this.flatDiscountAmount = v; }

    public double getDiscountPercentage()              { return discountPercentage; }
    public void   setDiscountPercentage(double v)      { this.discountPercentage = v; }

    public String getActiveDiscountType()              { return activeDiscountType; }
    public void   setActiveDiscountType(String type)   { this.activeDiscountType = type; }

    public int    getMaxAgentsPerRestaurant()           { return maxAgentsPerRestaurant; }
    public void   setMaxAgentsPerRestaurant(int v)     { this.maxAgentsPerRestaurant = v; }

    public String getAppName()  { return appName; }
    public String getVersion()  { return version; }

    public void displayDiscountConfig() {
        System.out.println("\n  ── Current Discount Config ──");
        System.out.println("  Active Type  : " + activeDiscountType);
        System.out.println("  Threshold    : ₹" + discountThreshold);
        System.out.println("  Flat Amount  : ₹" + flatDiscountAmount);
        System.out.println("  Percentage   : "  + discountPercentage + "%");
        System.out.println("  ─────────────────────────────");
    }
}