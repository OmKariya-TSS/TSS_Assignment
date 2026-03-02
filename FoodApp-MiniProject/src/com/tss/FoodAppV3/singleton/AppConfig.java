package com.tss.FoodAppV3.singleton;

import com.tss.FoodAppV3.serialization.PersistenceManager;

import java.io.Serializable;

public class AppConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    private static AppConfig instance;
    private static final String FILE = "appconfig.dat";

    private double discountThreshold  = 500.0;
    private double flatDiscountAmount = 50.0;
    private double discountPercentage = 10.0;
    private int    maxAgentsPerRestaurant = 5;
    private String appName  = "FoodApp";
    private String version  = "1.0";

     private String activeDiscountType = "FLAT";

    private AppConfig() {}

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            AppConfig loaded = PersistenceManager.load(FILE, null);
            instance = (loaded != null) ? loaded : new AppConfig();
        }
        return instance;
    }
    public static synchronized void resetInstance() {
        instance = null;
    }
    private void persist() {
        PersistenceManager.save(FILE, this);
    }


    public double getDiscountThreshold()               { return discountThreshold; }
    public void   setDiscountThreshold(double v)       { this.discountThreshold = v;
        persist();
    }

    public double getFlatDiscountAmount()              { return flatDiscountAmount; }
    public void   setFlatDiscountAmount(double v)      {
        this.flatDiscountAmount = v;
        persist();
    }

    public double getDiscountPercentage()              { return discountPercentage; }
    public void   setDiscountPercentage(double v)      { this.discountPercentage = v;
        persist();
    }

    public String getActiveDiscountType()              { return activeDiscountType; }
    public void   setActiveDiscountType(String type)   { this.activeDiscountType = type;
        persist();
    }

    public int    getMaxAgentsPerRestaurant()           { return maxAgentsPerRestaurant; }
    public void   setMaxAgentsPerRestaurant(int v)     { this.maxAgentsPerRestaurant = v;
        persist();
    }

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